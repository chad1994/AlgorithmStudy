import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2580 {

    static boolean rowCheck[][] = new boolean[9][10];
    static boolean colCheck[][] = new boolean[9][10];
    static boolean squareCheck[][] = new boolean[9][10];

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int board[][] = new int[9][9];

        for(int i=0;i<9;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<9;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j]!=0){
                    rowCheck[i][board[i][j]] = true; // == i열의 해당 숫자는 있다!
                    colCheck[j][board[i][j]] = true; // == j열의 해당 숫자는 있다!
                    squareCheck[square(i,j)][board[i][j]] = true; //square(i,j)번째에 해당 숫자가 있다!
                }else{
                    rowCheck[i][board[i][j]] = false; // == i열의 해당 숫자는 있다!
                    colCheck[j][board[i][j]] = false; // == j열의 해당 숫자는 있다!
                    squareCheck[square(i,j)][board[i][j]] = false;
                }
            }
        }

        fillBlanks(0,0,board);

    }



    public static void fillBlanks(int x,int y,int[][] board){
        if(x==board.length && y==0){ //x=8,y=8 다음인, x=9,y=0 일때 9x9짜리 보드판을 다 돈것이므로 이때 출력.
            printBoard(board);
            System.exit(0); //답은 하나만 찾으면 된다고 했으므로, 완성시 효율을 위해 종료.
        }else{
            if(board[x][y]!=0){
                if(y==board.length-1){
                    fillBlanks(x+1,0,board);
                }else{
                    fillBlanks(x,y+1,board);
                }
            }else{
                for(int num=1;num<=9;num++){ //행,열,네모에 1~9까지 비교하여 조건 만족하면 넣어봄!
                    if(!rowCheck[x][num] && !colCheck[y][num] && !squareCheck[square(x,y)][num]){
                        rowCheck[x][num]=true;
                        colCheck[y][num]=true;
                        squareCheck[square(x,y)][num]=true;
                        board[x][y] = num;

                        if(y==board.length-1){
                            fillBlanks(x+1,y-8,board);
                        }else{
                            fillBlanks(x,y+1,board);
                        }

                        // 위 재귀호출을 통해 x,y 가 끝에 도달 못하면 만족하지 못하여 다시 돌아 온것이므로 원래대로 돌림
                        rowCheck[x][num]=false;
                        colCheck[y][num]=false;
                        squareCheck[square(x,y)][num]=false;
                        board[x][y] = 0;

                    }
                }
            }
        }
    }

    public static int square(int x,int y){
        return (x/3) * 3 + (y/3);
    }


    public static void printBoard(int[][] board){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }


}


