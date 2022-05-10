import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
  
public class ServerUdp
{
    public static void main(String[] args) throws IOException
    {
        DatagramSocket ds = new DatagramSocket(1234);
        DatagramSocket ds2 = new DatagramSocket();

        InetAddress ip = InetAddress.getLocalHost();

        byte[] receive = new byte[65535];
        byte buf[] = null;
  
        DatagramPacket DpRecv1 = null;
        DatagramPacket DpRecv2 = null;



        while (true)
        {
  
            String str1 = "";
            String str2 = "";
            String op = "";

            DpRecv1 = new DatagramPacket(receive, receive.length);  
            ds.receive(DpRecv1);  
            System.out.println("Recieved String 1 : " + data(receive)); 
            str1 = data(receive).toString();

            String temp = data(receive).toString().toLowerCase();
            if (temp.equals("exit"))
            {
                System.out.println("Connection Closed from Server");
                ds.close();
                break;
            }

            DpRecv2 = new DatagramPacket(receive, receive.length);  
            ds.receive(DpRecv2);  
            System.out.println("Recieved String 2 : " + data(receive)); 
            str2 = data(receive).toString();

            if(str2.contains(str1))
                op = "String 1 is a substring of String 2";
            else
                op = "String 1 is not a substring of String 2";
            buf = op.getBytes();
  
            DatagramPacket DpSend1 = new DatagramPacket(buf, buf.length, ip, 4321);  
            ds2.send(DpSend1);

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