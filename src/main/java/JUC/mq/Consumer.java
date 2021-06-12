package JUC.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 消费者
 */
public class Consumer {
    public static void main(String[] args) throws MQClientException {
        //创建消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");
        //设置namesvr地址
        consumer.setNamesrvAddr("localhost:9876");
        //订阅一个或者多个topic，以及tags用来过滤需要消费的消息
        consumer.subscribe("TopicTest", "");
        //注册回调实现类处理从broker拉取的消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
         @Override
         public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
             System.out.printf("%s receive new message：%s %n",Thread.currentThread().getName(),msgs);
             //标记该消息消费成功
             return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
         }
     });
        //启动消费者实例
        consumer.start();
        System.out.printf("Conusmer started.%n");
    }
}
