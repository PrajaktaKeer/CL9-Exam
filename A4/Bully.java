import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Bully {
    static boolean[] state = new boolean[7];
    int coordinator;

    public static void up(int up) {
        if (state[up - 1]) {
            System.out.println("Process" + up + "is already up");
        } else {
            int i;
            Bully.state[up - 1] = true;
            System.out.println("process " + up + "held election");
            for (i = up; i < 7; ++i) {
                System.out.println("election message sent from process" + up + "to process" + (i + 1));
            }
            for (i = up + 1; i <= 7; ++i) {
                if (!state[i - 1])
                    continue;
                System.out.println("alive message send from process" + i + "to process" + up);
                break;
            }
        }
    }

    public static void down(int down) {
        if (!state[down - 1]) {
            System.out.println("process " + down + "is already dowm.");
        } else {
            Bully.state[down - 1] = false;
        }
    }

    public static void mess(int mess) {
        if (state[mess - 1]) {
            if (state[6]) {
                System.out.println("0K");
            } else if (!state[6]) {
                int i;
                System.out.println("process" + mess + "election");
                for (i = mess; i < 7; ++i) {
                    System.out.println("election send from process" + mess + "to process " + (i + 1));
                }
                for (i = 7; i >= mess; --i) {
                    if (!state[i - 1])
                        continue;
                    System.out.println("Coordinator message send from process" + i + "to all");
                    break;
                }
            }
        } else {
            System.out.println("Prccess" + mess + "is down");
        }
    }

    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 7; ++i) {
            Bully.state[i] = true;
        }

        System.out.println("Initially All Processes are activated !");
        System.out.println(".........");
        System.out.print("\nProcess    : ");
        for (int i = 1; i <= 7; i++) {
            System.out.print("\tP" + i);
        }
        System.out.print("\nID\t   : ");
        for (int i = 1; i <= 7; i++) {
            System.out.print("\t" + i);
        }
        System.out.print("\nStatus     : ");
        for (int i = 1; i <= 7; i++) {
            System.out.print("\t" + "1");
        }
        System.out.print("\n");
        do {
            System.out.println(".........");
            System.out.println("Menu");
            System.out.println("1 Activate.");
            System.out.println("2.Deactivate");
            System.out.println("3 Election");
            System.out.println("4.Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Activation ..");
                    int up = sc.nextInt();
                    if (up == 7) {
                        System.out.println("Process 5 is co-ordinator");
                        Bully.state[6] = true;
                        break;
                    }
                    Bully.up(up);
                    break;
                }
                case 2: {
                    System.out.println("Deactivation ..");
                    int down = sc.nextInt();
                    Bully.down(down);
                    break;
                }
                case 3: {
                    System.out.println("Enter Process to be elected. ");
                    int mess = sc.nextInt();
                    Bully.mess(mess);
                }
            }
        } while (choice != 4);
    }
}
