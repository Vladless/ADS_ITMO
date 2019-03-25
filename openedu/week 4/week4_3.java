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
		//массив служащий для описания структуры скобочной последовательности
        int[] bracketsStruct = new int[content.length()];
        int marck = 0;
		//если пос-ть сразу начинается с закрывающий скобок, 
		//то она сразу считается неккоректной
        if (content.charAt(0) == ')' || content.charAt(0) == ']') {
            return false;
        }
		//определение типа скобок
        for (int i = 0; i < content.length(); i++) {
			//если исследуемый символ совпададет с одним из типов открывающих скобок, то
			//заносим 1 в bracketsStruct как символ описывающий тип скобки и
			// увеличиваем marck для нахождения кол-ва открывающих скобок стоящих друг за другом
            if (content.charAt(i) == '(') {
                bracketsStruct[marck] = 1;
                marck++;
            }
            if (content.charAt(i) == '[') {
                bracketsStruct[marck] = 0;
                marck++;
            }
			//проверяем на последовательность закрывающих скобок
			//если marck = 0 или bracketsStruct[marck - 1], 
			//то открывающих скобок не найдено, иначе уменьшаем marck 
			//для проверки равновесия структуры последовательности
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
		//marck == 0 то, кол-во закрывающих и открывающих скобок роавно => 
		//последовательность корректна
        return marck == 0;
    }
}
