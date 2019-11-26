import java.util.HashMap;
import java.util.Map;

public class Programmers_Hash3 {

    public static void main(String args[]){

        String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        System.out.println(solution(clothes));
    }

    public static int solution(String[][] clothes) {
        int answer = 1;

        Map<String,Integer> map = new HashMap<>();

        for(int i=0;i<clothes.length;i++){
            if(map.get(clothes[i][1])==null){
                map.put(clothes[i][1],1);
            }
            else{
                map.put(clothes[i][1],map.get(clothes[i][1])+1);
            }
        }

        for(int i : map.values()){
            answer *= (i+1);
        }

        answer-=1;

        return answer;
    }
}
