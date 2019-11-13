import java.util.Stack;

public class Kakao_Winter1 {
    public static void main(String args[]){

        int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = {1,5,3,5,1,2,1,4};
        System.out.println(solution(board,moves));
    }

    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> col_stack[] = new Stack[board.length];
        Stack<Integer> basket = new Stack<>();

        for(int i=board.length-1;i>=0;i--){
            col_stack[i] = new Stack<>();
            for(int j=board.length-1;j>=0;j--){
                if(board[j][i]!=0) {
                    col_stack[i].push(board[j][i]);
                }
            }
        }

        for(int i=0;i<moves.length;i++){
            if(col_stack[moves[i]-1].isEmpty()) {
            }else{
                if(!basket.isEmpty() && col_stack[moves[i]-1].peek()==basket.peek()){
                    col_stack[moves[i]-1].pop();
                    basket.pop();
                    answer+=2;
                }else{
                    basket.push(col_stack[moves[i]-1].pop());
                }
            }
        }


        return answer;
    }
}
