import java.rmi.*;

public interface Substring extends Remote{
    String Substr(String str1,String str2) throws RemoteException;
    
}