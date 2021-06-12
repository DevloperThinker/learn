package JUC.mq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * 同步发送消息
 */
public class SyncProducer {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        //实例化producer
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        //设置NameSvr地址
        producer.setNamesrvAddr("localhost:9876");
        //启动producer
        producer.start();
        for (int i = 0; i < 100; i++) {
            //创建消息
            Message msg = new Message("TopicTest","TagA",("Hello RocketMQ "+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            //发送消息到Broker
            SendResult sendResult = producer.send(msg);
            //通过sendResutl获取发送结果
            System.out.println(sendResult);
        }
        //如果不在产生消息，关闭producer
        producer.shutdown();

    }
}
