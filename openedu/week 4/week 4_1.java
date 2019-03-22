import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    //
    public static void main(String[] args) throws IOException {
        final StringBuilder data = new StringBuilder();
        final File inputFilePath = new File("input.txt");
        final byte[] bytes = Files.readAllBytes(inputFilePath.toPath());
        char singleChar;
        for(byte b : bytes) {
            singleChar = (char) b;
            data.append(singleChar);
        }
        StringBuilder deleted = new StringBuilder();
        String value;
        int counter = 0;
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
    }
}