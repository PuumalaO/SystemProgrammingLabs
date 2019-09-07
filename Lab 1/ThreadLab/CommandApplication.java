import java.util.Scanner;
import java.util.ArrayList;

class CommandApplication{
  
  
  public static void main(String args[]) {
      ArrayList<PrinterThread> threads = new ArrayList<PrinterThread>();
      String command;
      CommandApplication app = new CommandApplication();
      Scanner scanner = new Scanner(System.in);
      

      while(true){
        System.out.println("Master gimme commands");
        command = scanner.next();

        if(command.equals("START")){
          PrinterThread thread = new PrinterThread();
          threads.add(thread);
          app.runThread(threads.get(threads.size()-1));
        }

        if(command.equals("STOP")){
          app.stopThread(threads.get(threads.size()-1));
          threads.remove(threads.get(threads.size()-1));
        }

        if(command.equals("QUIT")){
          System.exit(0);
        }
      }
        
      }

      public void runThread(PrinterThread threadToStart){
        
        threadToStart.start();
      }

      public void stopThread(PrinterThread threadToStop){
        threadToStop.stopThread();
      }

}