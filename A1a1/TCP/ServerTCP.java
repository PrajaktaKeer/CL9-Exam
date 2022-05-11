import java.net.*;
import java.io.*;
import java.lang.Math;

public class ServerTCP
{
    //initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    // private DataInputStream in = null;
    // private DataOutputStream out = null;
    // constructor with port
    public ServerTCP(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");
            int i = 1;
            while (true) {
                try {
                    System.out.println("Waiting for a client ...");
                    socket = server.accept();
                    System.out.println("Client " + i + " accepted");
                    ServerThread st = new ServerThread(socket, "Client" + String.valueOf(i));
                    i++;
                    st.start();
                } catch (Exception e) {
                    System.out.println("connetion error");
                }
            }
        }
        catch(IOException i) {
            System.out.println(i);
        }
    }
    public static void main(String args[]) {
        ServerTCP server = new ServerTCP(5000);
    }
}

class ServerThread extends Thread{
    int a =0, b=0;
    DataInputStream in = null;
    DataOutputStream out = null;
    Socket socket = null;
    String clientnum = "";

    public ServerThread(Socket s, String clientnum) {
        socket = s;
        this.clientnum = clientnum;
    }

    public void run() {
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            a = in.readInt();
            b = in.readInt();
            System.out.println("Recieved Base and Power.");
            out.writeDouble(Math.pow(a, b));
            //System.out.println("Recieved Base and Power. Answer is: "+ Math.pow(a,b));
        } catch (IOException ie) {
            System.out.println("socket close error");
        }
    }
}