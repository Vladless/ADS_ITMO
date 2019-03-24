import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        String content;
        int fl = 0;
        StringBuilder deleted = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        LinkedList<Integer> queue = new LinkedList<>();
        while ((content = br.readLine()) != null) {
            if(content.split(" ")[0].equals("+")){
                queue.addFirst(Integer.valueOf(content.split(" ")[1].trim()));
            }
            else{
                if(fl == 0) {
                    fl++;
                    continue;
                }
                deleted.append(queue.removeLast()).append("\n");
            }
        }
        Path outputFilePath = Paths.get("output.txt");
        Files.write(outputFilePath, Collections.singleton(deleted));
    }
}