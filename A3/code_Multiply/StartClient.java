import MultiplyApp.*;
 
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.*;
import java.util.*;
 
public class StartClient {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      try {
      		// orb is created and initialised
	    ORB orb = ORB.init(args, null);
	    
	    //get the object reference
	    org.omg.CORBA.Object objRef =   orb.resolve_initial_references("NameService");
	    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
	    Multiply mulobj = (Multiply) MultiplyHelper.narrow(ncRef.resolve_str("ABC"));
 
            Scanner c=new Scanner(System.in);         		    
		    
		      System.out.println("Enter a:");
		      String aa = c.nextLine();
		      System.out.println("Enter b:");
		      String bb = c.nextLine();
		      int a=Integer.parseInt(aa);
		      int b=Integer.parseInt(bb);
		      
		      //calling of multiplication function
		      int r= mulobj.multiply(a,b);
		      System.out.println("The result for multiplication is : "+r);
		
       }
       catch (Exception e) {
          System.out.println("Hello Client exception: " + e);
	  e.printStackTrace();
       }
 
    }
 
}
