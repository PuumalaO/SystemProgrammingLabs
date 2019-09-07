import java.time.*;
import java.time.temporal.ChronoUnit;

public class ExampleThread extends Thread {

  public void run() {
    try {
        while (true) {
          LocalDateTime now = LocalDateTime.now();
          LocalDateTime SOPPOpens = LocalDateTime.of(2019, Month.AUGUST, 29, 15,00,00);

          long diff = now.until(SOPPOpens, ChronoUnit.SECONDS);
          System.out.println("SOPP WILL BE OPEN IN:" + diff + " seconds."); 
          sleep(2000);
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
