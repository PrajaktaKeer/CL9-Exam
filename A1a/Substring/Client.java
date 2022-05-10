import java.net.*;
import java.io.*;
import java.util.*;

public class Client
{
	private Socket socket = null;
	private DataInputStream input = null;
	private DataInputStream dis = null;
	private DataOutputStream output = null;
	public Client(String address, int port)
	{
		//Establishing Connection
		try
		{
			socket = new Socket(address, port);
			System.out.println("Connected!");
			input = new DataInputStream(System.in);
			dis = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
		}
		catch(Exception e)//IOException, UnknownHostException
		{
			System.out.println(e);
		}


		//Reading Message From Terminal
		String str1 = "";
        	String str2 = "";
		Scanner sc = new Scanner(System.in);
		//Until Exit is written
		
		while(true)
		{
			try
			{
                		System.out.println("Enter String 1 : ");
				str1 = sc.nextLine();
				output.writeUTF(str1);

				String temp = str1.toLowerCase();
				if(temp.equals("exit")) //If client enters exit, close the socket
				{
					System.out.println("Closing Connection!");
					socket.close();
					System.out.println("Thank You!");
					break;
				}
				
				System.out.println("Enter String 2 : ");
                		str2 = sc.nextLine();
				output.writeUTF(str2);

				String received = dis.readUTF(); // The string of character is decoded from the UTF and returned as String.
				System.out.println(received);
			}
			catch(Exception e) //IOException
			{
				e.printStackTrace();
			}
		}
	    try //close connections
	    {
	        input.close();
	        output.close();
	        socket.close();
	    }
	    catch(Exception e) //IOException
	    {
	        System.out.println(e);
	        e.printStackTrace();
	    }
	}
	public static void main(String args[])
	{
		Client client = new Client("127.0.0.1",8080);
	}
}