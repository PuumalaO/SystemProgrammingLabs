 
public class PiCalc implements PiThread.PiCalculationInterface{
  
  public static void main(String[] args) {
      PiCalc pi = new PiCalc();
    PiThread thread = new PiThread(pi);
    thread.start();
    System.out.println("Calculating pi, hold on a moment..");
    try {
        Thread.sleep(10000);
    } catch (Exception e) {
        System.out.println("Sleep excpetion"+e);
    }
    thread.interrupt();
  }

  public void piCalculationReady(String pivalue){
      System.out.println(pivalue);
  }
}