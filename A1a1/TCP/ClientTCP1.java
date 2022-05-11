// A Java program for a Client
import java.net.*;
import java.io.*;
import java.math.*;
import java.util.*;

public class ClientTCP1 {
    // initialize socket and input output streams
    private Socket socket = null;
    // private DataInputStream input = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    // constructor to put ip address and port

    public ClientTCP1(String address, int port)
    {
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");
            // takes input from terminal
            // input = new DataInputStream(System.in);
            in = new DataInputStream(socket.getInputStream());
            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u) {
            System.out.println(u);
        }
        catch(IOException i) {
            System.out.println(i);
        }

        Scanner sc= new Scanner(System.in);
        int a=0,b=0;
        while (a != -1) {
            System.out.print("Enter base: ");
            a= sc.nextInt();
            System.out.print("Enter power: ");
            b= sc.nextInt();
            System.out.println("Sending Base and Power to Server");
            try
            {
                out.writeInt(a);
                out.writeInt(b);

                double ans = in.readDouble();
                System.out.println("Ans is : " + ans);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
        // close the connection
        try
        {
            System.out.println("Closing connection");
            // input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
    public static void main(String args[])
    {
        ClientTCP1 client = new ClientTCP1("127.0.0.1", 5000);
    }
}