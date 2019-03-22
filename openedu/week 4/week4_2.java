import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder data = new StringBuilder();
        FileReader reader = new FileReader("input.txt");
        int c;
        while((c=reader.read())!=-1){
            data.append((char) c);
        }
        StringBuilder deleted = new StringBuilder();
        String value;
        int actionsValue = Integer.parseInt(data.toString().split("\\n")[0].split(" ")[0].trim());
        Queue<String> queue = new PriorityQueue<String>();
        for(int i = 1; i < actionsValue+1; i++){
            if(data.toString().split("\\n")[i].split(" ")[0].trim().equals("-")){
                deleted.append(queue.remove()).append("\n");
            }
            else  {
                value = data.toString().split("\\n")[i].split(" ")[1].trim();
                queue.add(value);
            }
        }
        FileWriter writer = new FileWriter("output.txt", false);
        writer.write(deleted.toString().trim());
        writer.flush();
    }
}