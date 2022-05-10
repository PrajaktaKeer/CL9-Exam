
import MultiplyApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;
 
class MultiplyObj extends MultiplyPOA {
  private ORB orb;
 
  public void setORB(ORB orb_val) {
    orb = orb_val; 
  }
 
  // implement Multiply() method
  public int multiply(int a, int b) {
    int r=a*b;
    return r;
  }
 
  // implement shutdown() method
  public void shutdown() {
    orb.shutdown(false);
  }
}
