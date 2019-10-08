import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Icpc_Farm {
    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());


        solution(a,b,n,w);
    }

    public static void solution(int a, int b, int n, int w){
        int sheep = 0;
        int goat = 0;
        int count = 0;
        for(int i = 1 ; i<n;i++){ // 1마리 부터 n마리 까지 돌려보는거야 여기서 i는 내가 사진찍어 보낸 x를 뜻해
            if(((a*i)+(b*(n-i))) == w){  //이 방정식이 성립 되는 경우는 답이 있다고 생각하면 돼
                sheep = i; // 양은 i
                goat =n-i; // 염소는 n-i 가 되겠지
                count++; // 해가 있는 경우를 카운트
            }
        }
        if((count!=1)) { // count 가 1 이 아니면 답이 여러개거나 없는 경우이기 때문에
            System.out.println(-1); // -1 출력
        }else{ // 나머지는 해가 1개이기 때문에 답 출력
            System.out.println(sheep+" "+goat);
        }

    }
}


