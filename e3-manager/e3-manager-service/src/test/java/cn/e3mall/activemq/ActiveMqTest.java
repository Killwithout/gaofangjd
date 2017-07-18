package cn.e3mall.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import javax.jms.*;

/**
 *          ActiveMQ
 * Created by cjk on 2017/7/10.
 */
public class ActiveMqTest {
    /**
     * 点到点的形式发送消息（一个生产者（发送），一个消费者（接受））
     */
    @Test
    public void testQueneProducer() throws Exception{
            //1.创建一个连接工厂，指定服务的ip和端口号
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.30.14:61616");
            //2.使用工厂对象创建connection对象
            Connection connection = connectionFactory.createConnection();
            //3.开启连接，调用start方法
            connection.start();
            //4.创建一个session对象，第一个参数，是否开启事物,true开启，false不开启
            // 第二个参数无意义,表示应答模式，分为手动和自动；
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //5.使用session对象创建Queue，String
            Queue queue = session.createQueue("cjk-test-queue");
            //6.使用session对象创建一个Producer，distination(队列类型,先进先出)
            MessageProducer producer = session.createProducer(queue);
            //7.使用session对象创建message，String
            TextMessage textMessage = session.createTextMessage("hello active mq");
            //8.发送消息
            producer.send(textMessage);
            //9.关闭资源
            session.close();
            //10.关闭连接
            connection.close();
    }
    @Test
    public void testQueneConsumer() throws Exception{
          //1.创建一个连接工厂，指定服务的ip和端口号
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.30.14:61616");
        //2.使用工厂对象创建connection对象
            Connection connection = connectionFactory.createConnection();
        //3.开启连接，调用start方法
            connection.start();
        //4.创建一个session对象，第一个参数，是否开启事物,true开启，false不开启
        // 第二个参数无意义,表示应答模式，分为手动和自动；
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.使用session对象创建Queue，String
            Queue queue = session.createQueue("cjk-test-queue");
        //6.使用session对象创建一个Consumer，distination(队列类型,先进先出)
            MessageConsumer consumer = session.createConsumer(queue);
        //7.使用consumer设置监听器，拿到发送的消息并打印
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    TextMessage textMessage=(TextMessage) message;
                    String text;
                    try{
                        text=textMessage.getText();
                        System.out.println(text);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }

    /**
     * 发布者 和 订阅者（生产者产生消息，多个消费者接收）
     **/
    @Test
    public void testTopicProducer() throws Exception{
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.30.14:61616");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("cjk-test-topic");
            //topic
            MessageProducer producer = session.createProducer(topic);
            TextMessage textMessage = session.createTextMessage("hello topic message");
            producer.send(textMessage);
            producer.close();
            session.close();
            connection.close();
    }

    @Test
    public void testTopicConsumer() throws Exception{
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.30.14:61616");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("cjk-test-topic");
            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    TextMessage testMessage = (TextMessage) message;
                    String text;
                    try {
                       text = testMessage.getText();
                       System.out.println(text);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("消费者启动！");
            consumer.close();
            session.close();
            connection.close();
    }
}
