import java.util.Observable;
import java.util.Observer;

public class LoadingFollower implements Observer{
  
    @Override
    public void update(Observable o, Object loading) {
        System.out.println(String.valueOf(loading)+"%");
    }

}