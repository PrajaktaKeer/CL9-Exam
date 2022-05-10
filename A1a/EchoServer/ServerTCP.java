import java.net.*;
import java.io.*;

public class ServerTCP
{
    //initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

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
    DataInputStream in = null;
    DataOutputStream out = null;
    Socket socket = null;
    String clientnum = "";
    String message = "";

    public ServerThread(Socket s, String clientnum) {
        socket = s;
        this.clientnum = clientnum;
    }

    public void run() {
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            message = in.readUTF();

            System.out.println("Message recieved from " + clientnum + " : " + message);
        } catch (IOException ie) {
            System.out.println("socket close error");
        }
    }
}