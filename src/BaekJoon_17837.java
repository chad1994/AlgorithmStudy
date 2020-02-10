import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 새로운 게임2
// 소요시간 : 4시간..
public class BaekJoon_17837 {


    static int N;
    static int K;
    static int[][] map; //0:흰 , 1:빨 , 2:파
    static ArrayList<Integer>[][] stackMap; // 맵의 각 인덱스별 말의 누적 상황을 가질 리스트배열.
    static Horse[] horse; // 0:행 1:열 2:방향(1우 2좌 3상 4하)
    static int count = 1;
    static int directionChange[] = {1, 0, 3, 2};
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        stackMap = new ArrayList[N][N];
        horse = new Horse[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                stackMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken()) - 1;
            horse[i] = new Horse(x, y, direction);
            stackMap[x][y].add(i);
        }


        simulate();

    }

    public static void simulate() {
        while (count <= 1000) {
            for (int i = 0; i < K; i++) {
                int stack_size = moveHorse(i);

                if (stack_size >= 4) {
                    System.out.println(count);
                    return;
                }
            }
            count++;
        }
        System.out.println(-1);
    }

    public static int moveHorse(int i) {
        int nx = horse[i].x + dx[horse[i].direction];
        int ny = horse[i].y + dy[horse[i].direction];

        if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2) {  //범위를 벗어나거나 파랑색일때,
            horse[i].direction = directionChange[horse[i].direction]; // 방향 전환

            nx = horse[i].x + dx[horse[i].direction];
            ny = horse[i].y + dy[horse[i].direction];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2)// 반대 방향도 범위를 벗어나거나 파랑이면 움직이지 않고 바로 종료.
                return 0;
        }

        if (map[nx][ny] == 0) { //흰색일 때
            ArrayList currentPointList = stackMap[horse[i].x][horse[i].y];
            int index = currentPointList.indexOf(i); //해당 위치 스택list에서 해당 말의 위치를 찾는다
            int listSize = currentPointList.size(); // 반복 횟수를 고정하기 위해 리스트 크기를 저장 해놓는다.
            for(int loop=index ;loop<listSize;loop++){ // 말의 위치 ~ 끝 까지 횟수만큼 반복하여 지우고 다음칸에 누적하여 넣는다
                int h = (int)currentPointList.remove(index);
                horse[h].x = nx;
                horse[h].y = ny;
                stackMap[nx][ny].add(h);
            }
        } else { //빨강일 때
            ArrayList currentPointList = stackMap[horse[i].x][horse[i].y];
            int index = currentPointList.indexOf(i);
            int listSize = currentPointList.size();
            for(int cur = listSize-1; cur >= index ; cur--){ // 흰색과 동일하지만 반대로 뒤에서 부터 넣는다.
                int h = (int)currentPointList.remove(currentPointList.size()-1);
                horse[h].x = nx;
                horse[h].y = ny;
                stackMap[nx][ny].add(h);
            }
        }

        return stackMap[nx][ny].size();

    }


    static class Horse {
        int x;
        int y;
        int direction;

        public Horse(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}

