import java.util.Arrays;

public class Programmers_BinarySearch {

    public static void main(String args[]){
        int[] budgets = {120, 110, 140, 150};
        int m = 485;

        System.out.println(solution(budgets,m));
    }

    public static int solution(int[] budgets, int M) {
        int answer = 0;

        Arrays.sort(budgets);

        for(int i=0;i<budgets.length;i++){
            if(budgets[i]>M/(budgets.length-i)){
                answer = M/(budgets.length-i);
                break;
            }
            M -= budgets[i];
        }
        if(answer==0){
            answer=budgets[budgets.length-1];
        }


        return answer;
    }
}
