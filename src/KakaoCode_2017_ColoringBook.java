public class KakaoCode_2017_ColoringBook {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {-1, 1, 0, 0};
    static int notSpaceCount;
    static boolean[][] visited;
    static int numberOfArea;
    static int maxSizeOfOneArea;
    static int everyCount;


    public static void main(String args[]) {
        //TestCase1 : 기대값 4,5
//        int m = 6;
//        int n = 4;
//        int picture[][] =
//                {{1, 1, 1, 0},
//                 {1, 2, 2, 0},
//                 {1, 0, 0, 1},
//                 {0, 0, 0, 1},
//                 {0, 0, 0, 3},
//                 {0, 0, 0, 3}};

        //TestCase2 : 기대값 12,120
        int m = 13;
        int n = 16;
        int picture[][] = {{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {0, 1, 3, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 0}, {0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0}, {0, 0, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}};

        int[] answer = solution(m, n, picture);

        System.out.println(answer[0]);
        System.out.println(answer[1]);

    }

    public static int[] solution(int m, int n, int[][] picture) {
        initAllVariable();
        initCountAndVisited(m, n, picture);

        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    everyCount = 0; //매 영역, 구성 개수 카운트할 변수.
                    dfs(i, j, m, n, picture);
                    numberOfArea++; //초기 dfs호출 종료 = 동일 영역 탐색 종료 이므로, 영역 수 카운트 증가.
                }
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, everyCount); // 누적 최대값 갱신.

                if (notSpaceCount == 0) { // == 더 이상 탐색할 이유가 없음 for문 종료.
                    break;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public static void dfs(int x, int y, int m, int n, int[][] picture) { //dfs 탐색을 진행하여 4방향으로 나아가, 같은 영역 카운트.
        visited[x][y] = true;
        everyCount++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && picture[nx][ny] == picture[x][y]) {
                dfs(nx, ny, m, n, picture);
            }
        }
    }

    public static void initAllVariable() { //프로그래머스 전역변수 설정 문제로 초기화를 solution 함수에서 해주어야 함.
        notSpaceCount = 0;
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        everyCount = 0;
    }

    public static void initCountAndVisited(int m, int n, int[][] picture) { // 반복문을 조금이라도 더 빨리 끝내기 위한 0이 아닌 공간을 카운트할 변수와, visited변수를 초기화.
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] != 0) {
                    notSpaceCount++;
                    visited[i][j] = false;
                } else {
                    visited[i][j] = true;
                }
            }
        }
    }
}
