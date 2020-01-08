import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_17142 {

    static int n,m,result;
    static int[][] map;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { -1, 1, 0, 0 };
    static ArrayList<Point> ableVirusList, virusList; //바이러스가 될수 있는 좌표 리스트, m값에 따라 나올수 있는 경우의 수의 바이러스 리스트

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        result = -1;
        ableVirusList = new ArrayList<>();

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==2){
                    ableVirusList.add(new Point(i,j));
                }
            }
        }

        for (int i = 0; i < ableVirusList.size(); i++) {
            virusList = new ArrayList<>();
            virusList.add(ableVirusList.get(i));
            findCase(i, 1, m);
        }


        System.out.println(result);
    }

    public static void printMap(int[][] map){
        System.out.println("-------printMap");
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("--------------");
    }

    public static void findCase(int v, int cnt, int limit) { // DFS로 모든 경우의 수 탐색
        if (cnt == limit) {
            int bfs_result = spreadVirus();
            if (bfs_result >= 0 && (result > bfs_result || result==-1)) {
                result = bfs_result;
            }
        }else {
            for (int i = v + 1; i < ableVirusList.size(); i++) {
                    virusList.add(ableVirusList.get(i));
                    findCase(i, cnt + 1, limit);
            }

        }

        virusList.remove(virusList.size()-1);

    }

    public static int spreadVirus(){ // BFS로 바이러스 퍼지며 시간증가 하여 최종 시간 구하기

        Queue<Point> q = new LinkedList<>();
        int[][] copyMap = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        int time = 0;
        int temp = 0;
        q.addAll(virusList);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        while(!q.isEmpty()){
            boolean isBlank = false;
            int size = q.size();
            for(int i=0;i<size;i++){
                Point p = q.poll();
                visited[p.x][p.y] = true;


                for (int k = 0; k < 4; k++) {
                    int nx = p.x + dx[k];
                    int ny = p.y + dy[k];

                    if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                        if (!visited[nx][ny]) {
                            visited[nx][ny] = true;
                            if (copyMap[nx][ny] == 0) {
                                isBlank = true;
                                copyMap[nx][ny] = 3;
                                q.add(new Point(nx,ny));
                            }
                            if (copyMap[nx][ny] == 2) {
                                q.add(new Point(nx,ny));
                            }
                        }
                    }
                }
            }

            if (!isBlank) {
                temp++;
            } else {
                time += ++temp;
                temp = 0;
            }

        }

        if (hasBlank(copyMap)) {
            return -1;
        }

        return time;
    }

    public static boolean hasBlank(int[][] map) { // 빈칸 있는지 검사
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }


}
