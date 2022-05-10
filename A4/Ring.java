
import static java.lang.System.exit;
import java.util.Scanner;
// Ring class
public class Ring 
{
    static int n,f=0,r=0,ch,maxi=0,cord;
    static int[][] cq;
    public static void main(String[] args) 
    {
        int a=1;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the total number of processes : ");//scan number of processes
        cord=n=scanner.nextInt();
        cq=new int[n+1][n+1];
        for(int i=1;i<=n;i++)
        {
          if (r==0 && f==0)
              f=r=1;
          else if(r==n && f!=1)
              r=1;
          else
                r=r+1;
          System.out.println("Enter the  Process ID : ");
          cq[r][0]=scanner.nextInt();
          System.out.println("Enter the Process state : ");
          cq[r][1]=scanner.nextInt();
        }
        display();
        while(a==1)
        {
        	  //menu
              System.out.print("\n1.Election\n2.Quit");
              System.out.print("\nEnter the choice : ");
              ch=scanner.nextInt();
              switch(ch)
              {
              		//Display
                    case 1 :
                            if(cq[cord][1]==1)
                            {
                                System.out.println("\n No need to start election.");
                            }
                            else
                            {
                                System.out.println("Enter the process ID to initiate election : ");
                                int x2=scanner.nextInt();
                                while(cq[x2][1]==0)
                                {
                                    System.out.println("Process "+x2 +" is crash and cannot start election");
                                    System.out.println("Enter another Process :");
                                    x2=scanner.nextInt();
                                }
                                dis(x2);
                            }
                            break;
                    //Quit
                    case 2: 
                            exit(0);
                            break;
              }
              System.out.print("\nDo you want to continue : ");
              a=scanner.nextInt();
         }
    }
    //display function to show Process ID and Status
        public static void display()
    {
           System.out.print("\nProcess    : ");
           for(int i=1;i<=n;i++)
           {
               System.out.print("\tP"+i);
           }
           System.out.print("\nID\t   : ");
           for(int i=1;i<=n;i++)
           {
               System.out.print("\t"+cq[i][0]);
           }
           System.out.print("\nStatus     : ");
           for(int i=1;i<=n;i++)
           {
               System.out.print("\t"+cq[i][1]);
           }
    }
    // To display the ring after conducting an Election
    public static void dis(int x)
    {
        r=x-1;
        f=x;
        for(int i=f;i<=n;i++)
        {
            if(cq[i][1]!=0)
            {
                System.out.print(cq[i][0]);
                if(i!=n-1)
                {
                    System.out.print("->");  
                }
                maxi=Math.max(maxi, cq[i][0]);
            }
        }
        if(r!=0)
            System.out.print("->"); 
        for(int i=1;i<=r;i++)
        {
            if(cq[i][1]!=0)
            {
                System.out.print(cq[i][0]);
                if(i!=r-1)
                {
                    System.out.print("->");  
                }
                maxi=Math.max(maxi, cq[i][0]);
            }
        }
        System.out.println("\nMax Co-ordinator : "+maxi);
    }
}