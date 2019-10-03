import java.util.Scanner;

public class HTTPLab implements FetcherThread.FetcherInterface{
    public static void main(String[] args){
        Scanner command = new Scanner(System.in);
        String address = "https://www.oamk.fi/fi/";
        HTTPLab lab = new HTTPLab();
        System.out.print("Give an url address: ");
        address = command.nextLine();

        FetcherThread fetch =  new FetcherThread(address, lab);
        fetch.start();
    }

    public void printResult(String result){
        System.out.println(result);
    }
}