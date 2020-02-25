import java.util.Stack;

public class Kakao2018_Friends4Block {
    public static void main(String[] args) {
        String[] board = {
                "AAAAA",
                "AAAAA",
                "AAABC",
                "BERTY"};
        int m = 4;
        int n = 5;
//        String[] board = {
//                "CBB",
//                "AAB",
//                "AAC"};
//        int m = 3;
//        int n = 3;

        System.out.println(solution(m, n, board));
    }

    static int map[][];
    static boolean checkPop[][];
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 0, 1};
    static boolean isEnd = false;

    public static int solution(int m, int n, String[] board) {
        int answer = 0;

        initMap(m, n, board);
        while (!isEnd) {
            checkPop(m, n);
            pop(m, n);
            moveMap(m,n);
        }
        printMap(m, n);

        return getTotalCount(m, n);
    }

    public static void initMap(int m, int n, String[] board) {
        map = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
    }

    public static void checkPop(int m, int n) {
        checkPop = new boolean[m][n];
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (map[i][j] != 0) {
                    boolean is2x2 = true;
                    for (int k = 0; k < 3; k++) {
                        if (map[i][j] != map[i + dx[k]][j + dy[k]]) {
                            is2x2 = false;
                            break;
                        }
                    }
                    if (is2x2) {
                        checkPop[i][j] = true;
                        for (int k = 0; k < 3; k++) {
                            checkPop[i + dx[k]][j + dy[k]] = true;
                        }
                    }
                }
            }
        }
    }

    public static void pop(int m, int n) {
        isEnd = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (checkPop[i][j]) {
                    map[i][j] = 0;
                    isEnd = false;
                }
            }
        }
    }

    public static void moveMap(int m, int n) {
        Stack<Integer>[] stacks = new Stack[n];
        for (int j = 0; j < n; j++) {
            stacks[j] = new Stack<>();
            for (int i = 0; i < m; i++) {
                if (map[i][j] != 0) {
                    stacks[j].push(map[i][j]);
                }
            }
        }
        for(int j=0;j<n;j++){
            for(int i=m-1;i>=0;i--){
                if(!stacks[j].empty()){
                    map[i][j] = stacks[j].pop();
                }else{
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void printMap(int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int getTotalCount(int m, int n) {
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }


}
