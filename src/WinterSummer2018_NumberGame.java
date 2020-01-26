import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class WinterSummer2018_NumberGame {

    public static void main(String args[]){
        int[] A = {5,1,3,7};
        int[] B = {2,2,6,8};
        System.out.println(solution(A,B));
    }

    public static int solution(int[] A, int[] B) {
        int answer = 0;
        Queue<Integer> aQueue = new LinkedList<>();
        Queue<Integer> bQueue = new LinkedList<>();

        Arrays.sort(A);
        Arrays.sort(B);
        for(int i=0;i<A.length;i++){
            aQueue.offer(A[i]);
            bQueue.offer(B[i]);
        }

        while(!bQueue.isEmpty()){
            if(aQueue.peek()<bQueue.peek()){
                aQueue.poll();
                bQueue.poll();
                answer++;
            }else{
                bQueue.poll();
            }
        }


        return answer;
    }
}
