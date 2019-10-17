import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ObserverLab{
  
    public static void main(String[] args){

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command = "";
        while(true){
            try {
                command = reader.readLine();
                int i = Integer.valueOf(command);
                if(i == 1){
                    Loading loading = new Loading();
                    LoadingFollower observer = new LoadingFollower();
                    loading.addObserver(observer);
                    Thread thread = new Thread(loading);
                    thread.start();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
            
        }
    }

}

