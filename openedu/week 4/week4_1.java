import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder data = new StringBuilder();
        File inputFilePath = new File("input.txt");
        byte[] bytes = Files.readAllBytes(inputFilePath.toPath());
        char singleChar;
        for(byte b : bytes) {
            singleChar = (char) b;
            data.append(singleChar);
        }
        StringBuilder deleted = new StringBuilder();
        String value;
        String[] content = data.toString().split("\\n");
        int counter = 0;
        int actionsValue = Integer.parseInt(content[0].split(" ")[0].trim());
        ArrayList<Integer>  stack = new ArrayList<>();
        for(int i = 1; i < actionsValue+1; i++){
            if(content[i].split(" ")[0].trim().equals("-")){
                counter--;
                deleted.append(stack.get(counter)).append("\n");
                stack.remove(counter);
            }
            else  {
                value = content[i].split(" ")[1].trim();
                stack.add(counter, Integer.valueOf(value));
                counter++;
            }
        }
        Path outputFilePath = Paths.get("output.txt");
        Files.write(outputFilePath, deleted.toString().getBytes());
    }
}