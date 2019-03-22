<<<<<<< HEAD
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder data = new StringBuilder();
        FileReader reader = new FileReader("input.txt");
        int c;
        while((c=reader.read())!=-1){
            data.append((char) c);
=======
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        final StringBuilder data = new StringBuilder();
        final File inputFilePath = new File("input.txt");
        final byte[] bytes = Files.readAllBytes(inputFilePath.toPath());
        char singleChar;
        for(byte b : bytes) {
            singleChar = (char) b;
            data.append(singleChar);
>>>>>>> d79c9ebeb0d55803db4fd46e08d253ef95eb40f9
        }
        StringBuilder deleted = new StringBuilder();
        String value;
        int counter = 0;
<<<<<<< HEAD
        int actionsValue = Integer.parseInt(data.toString().split("\\n")[0].split(" ")[0].trim());
        ArrayList<Integer>  stack = new ArrayList<Integer>();
        for(int i = 1; i < actionsValue+1; i++){
            if(data.toString().split("\\n")[i].split(" ")[0].trim().equals("-")){
                counter--;
                deleted.append(stack.get(counter)).append("\n");
                stack.remove(counter);
            }
            else  {
                value = data.toString().split("\\n")[i].split(" ")[1].trim();
                stack.add(counter, Integer.valueOf(value));
                counter++;
            }
        }
        FileWriter writer = new FileWriter("output.txt", false);
        writer.write(deleted.toString());
        writer.flush();
=======
        String[] content = data.toString().split("\\n");
        int actionsValue = Integer.parseInt(content[0].split(" ")[0].trim());
        String[] stack = new String[actionsValue];
        for(int i = 1; i < actionsValue+1; i++){
            if(content[i].split(" ")[0].trim().equals("-")){
                counter--;
                deleted.append(stack[counter]).append("\n");
            }
            else  {
                value = content[i].split(" ")[1].trim();
                stack[counter] = value;
                counter++;
            }
        }
        final Path outputFilePath = Paths.get("output.txt");
        Files.write(outputFilePath, deleted.toString().getBytes());
>>>>>>> d79c9ebeb0d55803db4fd46e08d253ef95eb40f9
    }
}