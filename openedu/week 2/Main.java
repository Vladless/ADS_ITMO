import java.io.File;;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
    try {


        String content = new Scanner(new File("input.txt")).useDelimiter("\\Z").next();
        System.out.println(content);
        int sizeA = Integer.parseInt(content.split("\\n")[0].split(" ")[0].trim());
        int sizeB = Integer.parseInt(content.split("\\n")[0].split(" ")[1].trim());

        int[] A = new int[sizeA];
        int[] B = new int[sizeB];
        for (int i = 0; i < sizeA; i++) {
            A[i] = Integer.parseInt(content.split("\\n")[1].split(" ")[i]);
        }
        for (int i = 0; i < sizeB; i++) {
            B[i] = Integer.parseInt(content.split("\\n")[2].split(" ")[i]);
        }
        int sizeAB = sizeA * sizeB;

        countingSort(mulArrays(A, B, sizeAB), sizeAB);
    }
    catch (Exception e){
        try(FileWriter writer = new FileWriter("output.txt", false))
        {
            writer.write(e.toString());
            writer.flush();
        }
        catch(IOException ex){

        }
    }
    }

    private static int[] mulArrays(int[] A, int[] B, int sizeAB){
        int[] C = new int[sizeAB];
        int h = 0;
        int k = 0;

        for(int i = 0; i < sizeAB; i ++){
            if(h == A.length) continue;
            for(int j = 0; j < B.length;j++) {
                C[k] = A[h] * B[j];
                k++;
            }
            h++;
        }
        return C;
    }
    private static void countingSort(int[] sourceArray, int size) {

        int[] sortedArray = new int[size];
        int[] infoArrayBigger = new int[size];
        int[] infoArrayValue = new int[size];
        for(int i = 0; i < size; i++) {
            int counterBigger = 0,counterValue = 0;
            for (int j = 0; j < size; j++) {
                if(sourceArray[i] <= sourceArray[j]){
                    counterBigger++;
                }
                if(sourceArray[i] == sourceArray[j]){
                    counterValue++;
                }
            }
            infoArrayBigger[i] = counterBigger;
            infoArrayValue[i] = counterValue;
        }
        for(int i = 0; i < size; i++){
            for(int j = 0; j < infoArrayValue[i]; j++) {
                sortedArray[infoArrayBigger[i]-1] = sourceArray[i];
                infoArrayBigger[i] -= 1;
            }
        }
        int j = 0;
        int[] mirror = new int[size];
        for(int i = size-1; i >= 0; i--){
            mirror[j]  = sortedArray[i];
            j++;
        }
        int sum = 0;
        for(int i = size-1; i >= 0; i--){
            if(i%10 == 0)
                sum += mirror[i];
        }
        try(FileWriter writer = new FileWriter("output.txt", false))
        {
            writer.write(String.valueOf(sum));
            writer.flush();
        }
        catch(IOException ex){

        }
    }
}

