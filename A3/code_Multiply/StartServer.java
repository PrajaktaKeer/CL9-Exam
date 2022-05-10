import MultiplyApp.*;
 
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;
 
public class StartServer {
 
  public static void main(String args[]) {
    try{
      // create and initialize the ORB 
      ORB orb = ORB.init(args, null); 
      
      // get reference to rootpoa &amp; activate the POAManager     
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();
 
      // create servant and register it with the ORB
      MultiplyObj mulobj = new MultiplyObj();
      mulobj.setORB(orb); 
 
      // get object reference from the servant
      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(mulobj);
      Multiply href = MultiplyHelper.narrow(ref);
 
 	// initialise the object with a name
      org.omg.CORBA.Object objRef =  orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
 
 	// binding is done
      NameComponent path[] = ncRef.to_name( "ABC" );
      ncRef.rebind(path, href);
 
      
 	System.out.println("Server ready and waiting ...");
      // wait for invocations from clients
    
	  orb.run();
      
    } 
 
      catch (Exception e) {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
      }
 
      System.out.println("HelloServer Exiting ...");
 
  }
}
