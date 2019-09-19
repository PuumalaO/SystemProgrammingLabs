import java.util.Scanner;

public class ScannerThread extends Thread{

    private String command;
    public void run(){
        Scanner scanner = new Scanner(System.in);        try{
            while(true){
                System.out.println("Master gimme commands");
                command = scanner.next();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getCommand(){
        return command;
    }

}