package pubsub;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher {
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	public static void main(String[] args) throws JMSException {
		System.out.println("Publisher Ready");
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("StockQuote");
		MessageProducer producer = session.createProducer(topic);

		Stock st = new Stock("Bajaj", 123, 1000, 20, 100);
		
//		ObjectMessage message = session.createObjectMessage(st);
		
		
		 TextMessage message = session.createTextMessage();
		 message.setText(st.toString());
		
		producer.send(message);
		System.out.println("Message sent '" + st + "'");
		connection.close();
	
	}
}

