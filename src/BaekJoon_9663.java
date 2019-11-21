import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_9663 {

    static int answer = 0;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] cols = new int[n];
        backTracking(cols,0,n);

        System.out.println(answer);

    }

    public static void backTracking(int cols[],int current_col,int n){
        if(current_col == n){
            answer++;
        }else{
            for(int i=0;i<n;i++){
                cols[current_col]=i;
                if(isPossible(cols,current_col)){
                    backTracking(cols,current_col+1,n);
                }
            }

        }
    }

    public static boolean isPossible(int cols[],int col){
        for(int i=0;i<col;i++){
            if(cols[i]==cols[col] || Math.abs(col-i)== Math.abs(cols[col]-cols[i])){
                return false;
            }
        }
        return true;
    }





}
