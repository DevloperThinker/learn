package JUC.snowflake;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SnowFlakeDemo02 {

    //最近的时间戳
    private long lastTimestamp = 0;

    //机器ID
    private final String machineId;

    //0，并发控制
    private long sequence = 0L;

    //序列号最大值
    private final int sequenceMax = 9999;

    public  SnowFlakeDemo02(String machineId){
        this.machineId = machineId;
    }

    public synchronized  String nextId(){
        long timestamp = timeGen();
        if(this.lastTimestamp == timestamp){
            this.sequence = this.sequence + 1 % this.sequenceMax;

            if(this.sequence == 0){
                //重新生成时间戳
                timestamp = this.tilNextMillis(lastTimestamp);
            }
        }else{
            this.sequence = 0L;
        }
        this.lastTimestamp = timestamp;
        StringBuilder sb = new StringBuilder(String.valueOf(timestamp)).append(machineId).append(leftPad(sequence,4));

        return sb.toString();
    }

    /**
     * 获取当前时间戳
     * @return
     */
    protected  long timeGen(){
        return System.currentTimeMillis();
    }

    /**
     * 重新获取时间戳
     * @param lastTimestamp
     * @return
     */
    protected long tilNextMillis(long lastTimestamp){
        long timestamp = this.timeGen();
        while(timestamp <= lastTimestamp){
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    protected  String leftPad(long i ,int n){
        String s = String.valueOf(i);
        StringBuilder sb = new StringBuilder();
        int c = n-s.length();
        c = c < 0? 0:c;
        for (int t = 0; t < c; t++) {
            sb.append("0");
        }
        return sb.append(s).toString();
    }

    public static void main(String[] args) {
        SnowFlakeDemo02 sf= new SnowFlakeDemo02("01");
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            String nextId = sf.nextId();
         //   System.out.println(nextId);
        }
        System.out.println("耗时："+(System.currentTimeMillis()-beginTime));
    }

}
