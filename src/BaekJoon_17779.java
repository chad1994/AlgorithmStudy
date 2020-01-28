import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//게리맨더링2
public class BaekJoon_17779 {

    static int N;
    static int map[][], population[][];
    static int answer=Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1][N + 1];
        map = new int[N + 1][N + 1];

        StringTokenizer st;
        for (int i = 1; i <=N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <=N; j++) {
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divideSection();

        System.out.println(answer);
    }

    public static void divideSection() {
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int d1 = 1; d1 <= N; d1++) {
                    for (int d2 = 1; d2 <= N; d2++) {
                        if (x + d1 + d2 <= N && 1 <= y - d1 && y - d1 < y && y < y + d2 && y + d2 <= N) { //문제에 주어진 조건에 맞게 넣음
                            section1(x, y, d1, d2);
                            section2(x, y, d1, d2);
                            section3(x, y, d1, d2);
                            section4(x, y, d1, d2);
                            section5(x, y, d1, d2);
                            countPopulation();
                        }
                    }
                }
            }
        }
    }


    // 문제에 주어진 조건에 따라 반복하며 1~4 구역을 넣게 되면 가장 긴 행과 열을 기준으로 구역을 가져가게 됨. (== 5번 구역까지 침범하게 됨)
    public static void section1(int x, int y, int d1, int d2) {
        for (int r = 1; r < x + d1; r++) {
            for (int c = 1; c <= y; c++) {
                map[r][c] = 1;
            }
        }
    }

    public static void section2(int x, int y, int d1, int d2) {
        for (int r = 1; r <= x + d2 + d1; r++) {
            for (int c = y + 1; c <= N; c++) {
                map[r][c] = 2;
            }
        }
    }

    public static void section3(int x, int y, int d1, int d2) {
        for (int r = x + d1; r <= N; r++) {
            for (int c = 1; c < y - d1 + d2; c++) {
                map[r][c] = 3;
            }
        }
    }

    public static void section4(int x, int y, int d1, int d2) {
        for (int r = x + d2 + 1; r <= N; r++) {
            for (int c = y - d1 + d2; c <= N; c++) {
                map[r][c] = 4;
            }
        }
    }


    // 마름모 모양이기 때문에 행은 조건에 따라 반복하게 하며, 열은 flag1,2 에 따라 반복의 시작,종료 지점을 증가,감소 시키며 5번 구역을 가져가게 함.
    public static void section5(int x, int y, int d1, int d2) {
        int cStart = y, cEnd = y;
        boolean flag1 = false, flag2 = false;
        for (int r = x; r <= x + d1 + d2; r++) {
            for (int c = cStart; c <= cEnd; c++) {
                map[r][c] = 5;
            }

            if (cStart == y - d1) {
                cStart++;
                flag1 = true;
            } else if (!flag1)
                cStart--;
            else
                cStart++;
            if (cEnd == y + d2) {
                cEnd--;
                flag2 = true;
            } else if (!flag2)
                cEnd++;
            else
                cEnd--;
        }
    }


    public static void printMap() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void countPopulation(){ // 경우별 가장 적은 인구 차이값을 구함.
        int[] sec_popul = new int[6];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                sec_popul[map[i][j]]+=population[i][j];
            }
        }

        Arrays.sort(sec_popul);
        if(answer > (sec_popul[5]-sec_popul[1])){
            answer=sec_popul[5]-sec_popul[1];
        }
    }


}
