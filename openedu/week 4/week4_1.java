import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Main {

    private static LinkedList<Integer> stack = new LinkedList<>();
    public static void main(String[] args) throws IOException {

        String data = readFile("input.txt");
        String deleted = "";
        int actionsValue = Integer.parseInt(data.split("\\n")[0].split(" ")[0].trim());
        String action ;
        for(int i = 1; i < actionsValue+1; i++){
            action = data.split("\\n")[i].split(" ")[0];
            if(action.equals("-")){
                deleted += pop() + "\n";
            }else  if(action.equals("+")) {
                String value = "";
                value += data.split("\\n")[i].split(" ")[1].trim();
                push(Integer.parseInt(value));
            }
        }
        writeFile(deleted);
    }
    private static String readFile(String fileName) throws IOException {
        String data = "";
        FileReader reader = new FileReader(fileName);
        int c;
        while((c=reader.read())!=-1){

            data += ((char)c);
        }
        return data;
    }
    private static void writeFile(String data) throws IOException {
        FileWriter writer = new FileWriter("output.txt", false);
        writer.write(String.valueOf(data));
        writer.flush();
    }
    private static void push(int value){
        stack.push(value);
    }
    private static String pop(){
        String lastValue = stack.getFirst().toString();
        stack.pop();
        return lastValue;
    }
}
