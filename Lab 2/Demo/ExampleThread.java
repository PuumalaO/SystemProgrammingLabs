import java.time.*;
import java.time.temporal.ChronoUnit;

public class ExampleThread extends Thread {

  public interface ExampleThreadReporterInterface {
    void soppOpen();
    void soppClosed();
    void interupted();
  }

  public ExampleThread(ExampleThreadReporterInterface cb) {
    callBackInterface = cb;
  }

  ExampleThreadReporterInterface callBackInterface = null;

  public void run() {
    try {
        while (true) {
          LocalDateTime now = LocalDateTime.now();
          LocalDateTime SOPPOpens = LocalDateTime.of(2019, Month.AUGUST, 29, 15,00,00);

          long diff = now.until(SOPPOpens, ChronoUnit.SECONDS);
          if (diff > 0) {
            callBackInterface.soppOpen();
          }
          else {
            callBackInterface.soppClosed();
          }
          sleep(2000);
      }
    } catch(Exception e) {
      e.printStackTrace();
      callBackInterface.interupted();
    }
  }
}
