import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        String content;
        int counter = 0;
        StringBuilder deleted = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int[] stack = new int[Integer.valueOf(br.readLine())];
        while ((content = br.readLine()) != null) {
            if(content.split(" ")[0].equals("-")){
                counter--;
                deleted.append(stack[counter]).append("\n");
            }
            if(content.split(" ")[0].equals("+")){
                stack[counter] =  Integer.valueOf(content.split(" ")[1].trim());
                counter++;
            }
        }
        Path outputFilePath = Paths.get("output.txt");
        Files.write(outputFilePath, deleted.toString().getBytes());
    }
}