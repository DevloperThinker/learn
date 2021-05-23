package JUC.snowflake;

/**
 * ID自增（雪花算法）
 */
public class SnowFlakeDemo {

    //开始时间戳
    private final long twepoch = 1565020800000L;

    //机器标识位数
    private final  long workIdBits = 5L;

    //数据中心标识位数
    private  final  long datacenterIdBits = 5L;

    //支持的最大机器ID，结果是31
    private final long maxWorkerId = -1L ^ (-1L << workIdBits);

    //支持的最大数据中心标识ID，结果是31
    private final long maxDataCenterId = -1L ^ (-1L << datacenterIdBits);

    //毫秒内自增位数
    private final   long sequenceBits = 12L;

    //机器ID左移12位
    private final   long workIdShift = sequenceBits;

    //数据中心标识左移17位
    private final  long dataCenterIdShift = sequenceBits+workIdBits;

    //时间戳左移22位
    private final  long timestampLeftShift = sequenceBits+workIdBits+datacenterIdBits;

    //上次时间戳
    private static long lastTimestamp = -1L;

    //序列
    private  long sequence = 0L;

    //服务器ID 0-31
    private  long workId;

    //数据中心ID 0-31
    private  long dataCenterId;
    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private  long sequenceMask = -1L ^ (-1L << sequenceBits);

    public SnowFlakeDemo(long workId,long dataCenterId){
        if(workId > maxWorkerId || workId < 0){
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if(dataCenterId > maxDataCenterId || dataCenterId < 0){
            throw new IllegalArgumentException(String.format("dataCenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }

        this.workId = workId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 获得下一个ID
     */

    public synchronized  long nextId(){
        long timestamp = timeGen();

        //如果当期时间小于上次ID生成的时间戳，说明系统时钟回退过这个时间，应当抛出异常
        if(timestamp < lastTimestamp){
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp)
            );
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if(timestamp == lastTimestamp){
            sequence = (sequence+1) & sequenceMask;
            //毫秒内溢出
            if(sequence == 0){
                //阻塞到下一个毫秒，获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }else{
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        //移位并通过或运算拼接一起组成64位的ID
        return ((timestamp-twepoch) << timestampLeftShift)
                | (dataCenterId << dataCenterIdShift)
                | (workId << workIdShift)
                | sequence;
    }

    /**
     * 返回以毫秒数为单位的当前时间
     * @return
     */
    protected long timeGen(){
        return System.currentTimeMillis();
    }

    /**
     * 阻塞到下一个毫秒，直至获得新的时间戳
     * @param lastTimestamp
     * @return
     */
    protected long tilNextMillis(long lastTimestamp){
        long timestamp = timeGen();
        while(timestamp <= lastTimestamp){
            timestamp = timeGen();
        }
        return timestamp;
    }

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        SnowFlakeDemo snowFlakeDemo = new SnowFlakeDemo(0,0);
        for (int i = 0; i < 1000000; i++) {
            long id = snowFlakeDemo.nextId();
          //  System.out.println(Long.toBinaryString(id));
          //  System.out.println(id);
        }
        System.out.println("耗时："+(System.currentTimeMillis()-beginTime));
    }

}
