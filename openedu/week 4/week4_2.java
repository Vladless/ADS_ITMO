import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        String content;
        int tail = 0;
        int head = 0;
        StringBuilder deleted = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int len = Integer.valueOf(br.readLine());
        int[] queue = new int[len];
        while ((content = br.readLine()) != null) {
            if(content.split(" ")[0].equals("+")){
                if (++tail == len)
                    tail = 0;
                queue[tail] = Integer.valueOf(content.split(" ")[1]);
            }
            else{
                if (++head == len) {
                    head = 0;
                }
                deleted.append(queue[head]).append("\n");
            }
        }
        Path outputFilePath = Paths.get("output.txt");
        Files.write(outputFilePath, Collections.singleton(deleted));
    }
}