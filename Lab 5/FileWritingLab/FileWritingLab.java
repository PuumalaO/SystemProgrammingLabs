import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FileWritingLab {

    public static void main(String[] args) {

        String fileContent = "";
        String fileToRead = "filetoread.txt";

        

        try(FileReader fileReader = new FileReader(fileToRead)) {
            int nextCharacter = fileReader.read();
            while(nextCharacter != -1) {
                fileContent +=(char)nextCharacter;
                nextCharacter = fileReader.read();
            }
            System.out.println("Text already written in the file:\n"+fileContent);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {

        }
        
        System.out.println("Give new text to append to file:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String newText = null;
            try{
                newText = reader.readLine();
                fileContent+=newText;

                System.out.println(fileContent);
            }catch(Exception e){
                e.printStackTrace();
            }
        try(FileWriter fileWriter = new FileWriter(fileToRead)) {
            fileWriter.write(fileContent);

        } catch (IOException e) {
            
        }        
    }
}