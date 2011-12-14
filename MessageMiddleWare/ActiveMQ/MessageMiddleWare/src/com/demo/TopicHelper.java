package com.demo;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息发布/订阅
 * @author pai.li
 *
 */
public class TopicHelper {

	private final static String topicName = "Event.DataLogicError";
	private final static String clientID = "";
	
	public static void createToic() throws Exception{
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,ActiveMQConnectionFactory.DEFAULT_PASSWORD,ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
		connectionFactory.setUseAsyncSend(true);   //异步发送
		Connection connection = connectionFactory.createConnection();

		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic(topicName);	
		MessageProducer producer = session.createProducer(topic);
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);  //可持久化

		String text = "Hello Pie! From Topic";
		TextMessage message = session.createTextMessage(text);
		producer.send(message);

		session.close();
		connection.close();
	}
	
	public static void consumeTopic() throws Exception {
		
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD, ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        Connection connection = connectionFactory.createConnection(); 
        
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);       
        Topic topic = session.createTopic(topicName);	        
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener(){
			public void onMessage(Message message) {
				
				TextMessage m = (TextMessage) message;
				try {
					System.out.println(m.getText());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	    });
        connection.start();
		
	}
	
}
