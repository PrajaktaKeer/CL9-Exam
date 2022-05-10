import java.rmi.*;
import java.rmi.server.*;

//Define the method which was declared in interface
//The UnicastRemoteObject Class make objects available from remote machines
public class SubstringImp extends UnicastRemoteObject implements Substring {

    // A Default Constructor which is defined in order to handle the Remote
    // Exception using throws keyword from its parent class i.e UnicastRemoteObject
    
    public SubstringImp() throws RemoteException {
        //calling the constructor of UnicastRemoteObject
        super();
    }

    public String Substr(String str1,String str2) throws RemoteException {
        String msg;
        if(str2.contains(str1))
					msg = "String 1 is a substring of String 2";
				else
				    msg = "String 1 is not a substring of String 2";

        return msg;
    }

}