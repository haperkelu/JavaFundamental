package com.demo;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 点对点
 * @author pai.li
 *
 */
public class MsgHelper {

	private final static String destinationName = "TEST.PIE";
	public static void sendMsg() throws Exception {		
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,ActiveMQConnectionFactory.DEFAULT_PASSWORD,ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
		connectionFactory.setUseAsyncSend(true);   //异步发送
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(destinationName);
		MessageProducer producer = session.createProducer(destination);
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);  //可持久化

		String text = "Hello Pie!";
		TextMessage message = session.createTextMessage(text);
		producer.send(message);

		session.close();
		connection.close();
	}

	public static void consumeMsg() throws Exception {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD, ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);       
        Destination destination = session.createQueue("TEST.PIE");	        
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener(){
			public void onMessage(Message message) {
				
				TextMessage m = (TextMessage) message;
				System.out.println(m);
				
			}
	    });
	}
	
}
