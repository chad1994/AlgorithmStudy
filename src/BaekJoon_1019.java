import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_1019 {

    static int[] answer = new int[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        solution(1,n,1);

        for (int i = 0; i < 10; i++) {
            System.out.print(answer[i] + " ");
        }

    }

    public static void solution(int from, int to, int digit) {

        while (from % 10 != 0 && from <= to) {
            calc(from, digit);
            from++;
        }

        if(from > to){
            return;
        }

        while (to % 10 != 9 && from <= to) {
            calc(to, digit);
            to--;
        }

        int times = (to/10)-(from/10)+1; //from ~ to 까지 0~9까지 수가 몇번 나오는지
        for (int i = 0; i < 10; i++) { //자리수 만큼 곱해서 더해준다
            answer[i] += times * digit;
        }

        solution(from/10, to/10, digit*10); //다음자리 수 계산.

    }
    public static void calc(int a, int digit) {
        int n = a;
        while (n > 0) {
            answer[n % 10] += digit;
            n /= 10;
        }
    }

}
