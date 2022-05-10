import java.rmi.*;
import java.util.*;

public class ClientRmi {
    public static void main(String[] args) {
        Scanner s = null;
        try {
            // Server url from where we want to call the method remotely
            String serverurl = "rmi://127.0.0.1/AddServer";

            // lookup method finds the reference of remote object
            Substring remoteObject = (Substring)Naming.lookup(serverurl);

            s = new Scanner(System.in);


            // calling the remote method
            while (true) {
                
            System.out.println("Enter first string: ");
            String str1 = s.nextLine();
            String temp = str1.toLowerCase();
            if(temp.equals("exit")) //If client enters exit, close the socket
            {
                break;
            }
            System.out.println("Enter second string: ");
            String str2 = s.nextLine();

                System.out.println(remoteObject.Substr(str1, str2));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            s.close();
        }
    }
}