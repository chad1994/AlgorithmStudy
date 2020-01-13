import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 - 17471 개리맨더링
public class BaekJoon_17471 {

    static int N;
    static int population[];
    static int node[][]; // 구역간 관계 1=연결 2=비연결
    static boolean[] picked; //true/false로 선거구 구분.
    static int diffAB;
    static int searchSum;
    static int answer = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        population = new int[N+1];
        node = new int[N+1][N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            population[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int each_size = Integer.parseInt(st.nextToken());
            for (int j = 0; j < each_size; j++) {
                int spot = Integer.parseInt(st.nextToken());
                node[i][spot] = 1;  // 연결된 지역은 1로 표기
            }
        }

        for(int i = 1; i<=(N/2); i++) { //a:b의 비율(나눌 크기)를 반복  ex)N=6 -> 1:5,2:4,3:3 까지
            picked = new boolean[N+1];
            comb(1,0,i);
        }

        if(answer == Integer.MAX_VALUE){
            System.out.println(-1);
        }else {
            System.out.println(answer);
        }


    }


    public static void comb(int start, int cnt, int splitSize) { //N개의 수중 a:b로 나누는 경우의 수의 조합을 구한다
        if(cnt == splitSize) {
            diffAB = Integer.MAX_VALUE;

            populationDiff();

//            if(diffAB!=Integer.MAX_VALUE){ // 경우별 결과 출력.
//                for(int i=1;i<=N;i++){
//                    System.out.print(" "+picked[i]+" ");
//                }
//                System.out.println(": "+diffAB);
//            }

            answer = Math.min(answer, diffAB);

            return;
        }
        for(int i = start; i<N+1; i++) {
            picked[i]=true;
            comb(i+1,cnt+1,splitSize);
            picked[i]=false;
        }
    }

    public static void populationDiff(){
        boolean visited[] = new boolean[N+1];
        int aSum=0, bSum=0;
        int ABcount = 0; // a,b지역 탐색 시작시 값 증가, a지역 b지역 탐색이 완료되면 값이 2가 될것. == 탐색을 더할 필요가 없다는 것.
        for(int i=1;i<=N;i++){

            if(picked[i] && !visited[i]){
                ABcount++;
                searchSum = 0;
                search(i,visited);
                aSum+=searchSum;

            }else if(!picked[i] && !visited[i]){
                ABcount++;
                searchSum = 0;
                search(i,visited);
                bSum+=searchSum;
            }

            if (ABcount==2){
                break;
            }
        }

        if(isValidate(visited)){
            diffAB = Math.abs(aSum-bSum);
        }
    }

    public static void search(int index, boolean[] visited){
        visited[index] = true;
        searchSum += population[index];
        for(int i = 1; i<N+1; i++) {
            if(!visited[i] && picked[index] == picked[i] && node[index][i] == 1) { //아직 방문하지 않았고, 최초 노드와 동일한 지역이며, 연결되어있다면
                search(i,visited);
            }
        }
    }

    public static boolean isValidate(boolean[] visited){ //하나라도 방문되지 않았으면 연결되지 않는 지역으로 구분된 것, 이를 위한 유효성을 체크한다.
        for(int i = 1; i<N+1; i++) {
            if(!visited[i]) {
                return false;
            }
        }
        return true;
    }




}


