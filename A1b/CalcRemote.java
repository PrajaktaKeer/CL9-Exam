import java.rmi.*;
import java.rmi.server.*;

public class CalcRemote extends UnicastRemoteObject implements Calc {
    CalcRemote() throws RemoteException {
        super();
    }

    public double calcpow(int x, int y) {
        return Math.pow(x, y);
    }
}