import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String[] cmd =
                {
                        "cmd",
                };
        Process p;

        System.out.println("What git command would you like to run?");
        String command = scanner.nextLine();

        if (command.startsWith("please")) {
            System.out.println("happy to help");
            String[] git = command.split("please ");
            for (String g : git) {
                System.out.println(g);
                try {
                    p = Runtime.getRuntime().exec(cmd);
                    new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
                    new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
                    PrintWriter stdin = new PrintWriter(p.getOutputStream());
                    stdin.println(g);

                    stdin.close();
                    p.waitFor();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } else if (command.startsWith("Please")) {
            System.out.println("happy to help");
            String[] git = command.split("Please ");
            for (String g : git) {
                System.out.println(g);
            }
        }
        else {
            System.out.println("That's not nice");
        }
    }
}