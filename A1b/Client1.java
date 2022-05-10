import java.rmi.registry.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class Client1 {
    private Client1() throws RemoteException {
    }

    public static void main(String[] args) {
        try {
            Calc stub = (Calc) Naming.lookup("rmi://localhost/calculate");
            Scanner sc = new Scanner(System.in);
            int a, b, choice;
            while (true) {
                System.out.println("\nEnter your base and power");
                System.out.println("Enter 1st number");
                a = sc.nextInt();
                System.out.println("Enter 2nd number");
                b = sc.nextInt();
                System.out.println("Result of " + a + " and " + b + " is" + stub.calcpow(a, b));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}