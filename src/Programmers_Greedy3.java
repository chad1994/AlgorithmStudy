import jdk.nashorn.api.tree.BinaryTree;
import jdk.nashorn.api.tree.ExpressionTree;
import jdk.nashorn.api.tree.TreeVisitor;

public class Programmers_Greedy3 {

    public static void main(String args[]){

        System.out.println(solution("1231234",3));


    }

    public static String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();

        int start_index=0;

        for(int i=0;i<number.length()-k;i++){ //
            int max = 0;
            for(int j=start_index;j<k+i+1;j++){
                if(max<(number.charAt(j)-'0')){
                    max = number.charAt(j)-'0';
                    start_index=j+1;
                }
            }
            answer.append(String.valueOf(max));
        }


        return answer.toString();
    }

}
