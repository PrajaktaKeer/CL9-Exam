import java.rmi.*;

public interface Calc extends Remote {
    public double calcpow(int x, int y) throws RemoteException;
}