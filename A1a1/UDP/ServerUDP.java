import java.io.*;
import java.net.*;

public class ServerUDP {
    public static void main(String[] args) throws IOException
    {
        // Step 1 : Create a socket to listen at port 1234
        DatagramSocket ds = new DatagramSocket(1234);
        byte[] receive = new byte[65535];
        DatagramPacket DpReceive = null;
        while (true) {
            if (data(receive).equals("bye")) {
                System.out.println("Client sent bye.....EXITING");
                break;
            }

        // Step 2 : create a DatgramPacket to receive the data.
            DpReceive = new DatagramPacket(receive, receive.length);

        // Step 3 : receive the data in byte buffer.
            ds.receive(DpReceive);
            String[] s = data(receive).split("\\s+");
            System.out.println("Base:-" + Integer.valueOf(s[0]));
            System.out.println("Power:-" + Integer.valueOf(s[1]));
            double c = Math.pow(Integer.valueOf(s[0]),Integer.valueOf(s[1]));
            System.out.println("Answer:-" + c);
            // Exit the server if the client sends "bye"
            // Clear the buffer after every message.
            receive = new byte[65535];
        }
    }
    // A utility method to convert the byte array
    // data into a string representation.
    public static String data(byte[] a)
    {
        if (a == null)
        return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret.toString();
    }
}