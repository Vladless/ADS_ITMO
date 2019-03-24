import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        String content;
        StringBuilder info = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        br.readLine();
        while ((content = br.readLine()) != null) {
            if(isSeqTrue(content)){
                info.append("YES").append("\n");
            }
            else {
                info.append("NO").append("\n");
            }
        }
        Path outputFilePath = Paths.get("output.txt");
        Files.write(outputFilePath, Collections.singleton(info));
    }
    private static boolean isSeqTrue(String content) {
        int[] bracketsStruct = new int[content.length()];
        int marck = 0;
        if (content.charAt(0) == ')' || content.charAt(0) == ']') {
            return false;
        }
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '(') {
                bracketsStruct[marck] = 1;
                marck++;
            }
            if (content.charAt(i) == '[') {
                bracketsStruct[marck] = 0;
                marck++;
            }
            if (content.charAt(i) == ')') {
                if (marck == 0 || bracketsStruct[marck - 1] == 0) {
                    return false;
                }
                else marck--;
            }
            if (content.charAt(i) == ']') {
                if (marck == 0 || bracketsStruct[marck - 1] == 1){
                    return false;
                }
                else marck--;
            }
        }
        return marck == 0;
    }
}
