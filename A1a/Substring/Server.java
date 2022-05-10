import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

// Echo_Server class
public class Server
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket ss = new ServerSocket(8080);// server is listening on port 8080
		System.out.println("ServerSocket: " + ss );
		while (true)// running infinite loop for getting client request
		{
			Socket s = null;
			try
			{
				// socket object to receive incoming client requests
				s = ss.accept();
				System.out.println("\nA new client is connected : " + s);
				// obtaining input and out streams
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				// takes input from the client socket
				DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
				System.out.println("Assigning new thread for this client");
				// create a new thread object
				Thread t = new ClientHandler(s, dis, dos);
				// Invoking the start() method
				t.start();
			}
			catch (Exception e)
			{
				s.close();
				e.printStackTrace();
			}
		}
	}
}
// ClientHandler class
class ClientHandler extends Thread
{
	final DataInputStream dis;
	final DataOutputStream dos;
	final Socket s;
	// Constructor
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
	{
		this.s = s;
		this.dis = dis;
		this.dos = dos;
	}
	@Override
	public void run()
	{
		String toreturn;
		String str1 = "";
        String str2 = "";
		while (true)
		{
			try
			{
			    str1 = dis.readUTF();
		        	System.out.println("Recieved String 1 : " + str1);

				String temp = str1.toLowerCase();
				if(temp.equals("exit"))
                		{
                		    System.out.println("\nClient " + this.s + " sends exit...");
                		    System.out.println("Closing connection...");
                		    this.s.close();
                		    System.out.println("Bye!\nThank You!");
                		    break;
               			 }
				
               			 str2 = dis.readUTF();
		        	System.out.println("Recieved String 2 : " + str2);

				if(str1.contains(str2))
					toreturn = "String 2 is a substring of String 1";
				else
				toreturn = "String 2 is not a substring of String 1";
				dos.writeUTF(toreturn);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		try// closing connection
		{ 
			this.dis.close();
			this.dos.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}