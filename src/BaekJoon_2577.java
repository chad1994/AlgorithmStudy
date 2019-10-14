import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_2577 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        int sum = a*b*c;
        String strSum = String.valueOf(sum);

        int indexCounter[] = new int[10];
        for(int i=0;i<indexCounter.length;i++){
            indexCounter[i]=0;
        }
        for(int i=0;i<strSum.length();i++){
            indexCounter[strSum.charAt(i)-'0']++;
        }

        for(int i=0;i<indexCounter.length;i++){
            System.out.println(indexCounter[i]);
        }
    }

}
