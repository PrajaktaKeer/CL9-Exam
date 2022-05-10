package pubsub;

import java.io.IOException;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Subscriber {
	
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;


	public static void main(String[] args) throws JMSException {
		System.out.println("Subscriber Ready");
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("StockQuote");
		MessageConsumer consumer = session.createConsumer(topic);
		
		MessageListener listner = new MessageListener() {
			public void onMessage(Message message) {
				try {
					if(message instanceof TextMessage) {
						TextMessage textMessage = (TextMessage) message;
						System.out.println("Received Message : '" + textMessage.getText() + "'");
					}
				} catch (JMSException e) {
					System.out.println("Caught:" + e);
					e.printStackTrace();
				}
			}
		};
		consumer.setMessageListener(listner);
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connection.close();
	}
}
