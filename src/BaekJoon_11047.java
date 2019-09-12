import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11047 {

    public static void main (String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int money = Integer.parseInt(st.nextToken());
        int answer = 0;
        int unit[] = new int[n];


        for(int i=0;i<n;i++){
            unit[i] = Integer.parseInt(br.readLine());
        }


        int i=n-1;

        while(money!=0){
            if(unit[i]<=money){
                answer+=money/unit[i];
                money%=unit[i];
            }else{
                i--;
            }
        }

        System.out.println(answer);


    }
}
