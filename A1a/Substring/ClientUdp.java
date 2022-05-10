import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
  
public class ClientUdp
{
    public static void main(String args[]) throws IOException
    {
        Scanner sc = new Scanner(System.in);
  
        DatagramSocket ds = new DatagramSocket();
        DatagramSocket ds2 = new DatagramSocket(4321);
  
        InetAddress ip = InetAddress.getLocalHost();
        byte[] receive = new byte[65535];
        byte buf[] = null;

        DatagramPacket DpReceive = null;


        String str1 = "";
        String str2 = "";

        while (true)
        {
            System.out.println("Enter String 1 : ");
            str1 = sc.nextLine();
            buf = str1.getBytes();
  
            DatagramPacket DpSend1 = new DatagramPacket(buf, buf.length, ip, 1234);  
            ds.send(DpSend1);

            String temp = str1.toLowerCase();
            if(temp.equals("exit")) //If client enters exit, close the socket
            {
                System.out.println("Connection Closed");
                ds.close();
                break;
            }

            System.out.println("Enter String 2 : ");
            str2 = sc.nextLine();
            buf = str2.getBytes();

            DatagramPacket DpSend2 = new DatagramPacket(buf, buf.length, ip, 1234);  
            ds.send(DpSend2);


            DpReceive = new DatagramPacket(receive, receive.length);  
            ds2.receive(DpReceive);  
            System.out.println(data(receive)); 

            receive = new byte[65535];

        }
    }

    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}