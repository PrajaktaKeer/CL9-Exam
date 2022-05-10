import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP1
{
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        // Step 1:Create the socket object for
        // carrying the data.
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        byte buf[] = null;
        // loop while user not enters "bye"
        System.out.println("Enter Base and Power: <base power>");
        while (true)
        {
            String eqn = sc.nextLine();
            if (eqn.equals("bye"))
            break;
            buf = eqn.getBytes();
            DatagramPacket DpSend = new DatagramPacket(buf,
            buf.length, ip, 1234);
            ds.send(DpSend);
        }
    }
}