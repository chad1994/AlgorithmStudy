

//RGB거리


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1149 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int paint[][] = new int[n][3]; //0:빨강 1:초록 2:파랑
        int dp[][] = new int[n][3];

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            paint[i][0] = Integer.parseInt(st.nextToken());
            paint[i][1] = Integer.parseInt(st.nextToken());
            paint[i][2] = Integer.parseInt(st.nextToken());
        }

        dp[0][0] = paint[0][0];
        dp[0][1] = paint[0][1];
        dp[0][2] = paint[0][2];

        System.out.println(dp(n,paint,dp));

    }

    public static int dp(int n,int[][] paint,int[][] dp){ //dp배열에 앞에 담긴 다른색의 저장정보중 작은것과 해당 인덱스의 색을 더하여 저장.
        for(int i=1;i<n;i++){
            dp[i][0] = Math.min(dp[i-1][1],dp[i-1][2])+paint[i][0];
            dp[i][1] = Math.min(dp[i-1][0],dp[i-1][2])+paint[i][1];
            dp[i][2] = Math.min(dp[i-1][0],dp[i-1][1])+paint[i][2];
        }

        return Math.min(Math.min(dp[n-1][0],dp[n-1][1]),dp[n-1][2]); // 최종적으로 가장 작은 값을 저장.
    }
}
