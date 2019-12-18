import java.util.HashMap;
import java.util.HashSet;

public class WinterSummer2018_wordChain {

    public static void main(String args[]){

        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
//        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
//        hello, observe, effect, take, either, recognize, encourage, ensure, establish, hang, gather, refer, reference, estimate, executive
        int[] answer = solution(3,words);

        System.out.println(answer[0]);
        System.out.println(answer[1]);

    }

    public static int[] solution(int n ,String[] words){
        int[] answer = {0,0};

        HashSet<String> set = new HashSet<>();


        for(int i=0;i<words.length;i++){ //끝말잇기 조건 확인하며 set에 추가 및 검증
            if(i==0){
                set.add(words[i]);
            }
            else if(!set.contains(words[i]) && words[i-1].charAt(words[i-1].length()-1)==words[i].charAt(0)) {
                set.add(words[i]);
            }else{ //이미 있거나 룰에 어긋나면 번호 및 차례입력 후 반환.
                answer = new int[2];
                answer[0] = (i%n)+1; //몇번째 사람인지
                answer[1] = (i/n)+1; //몇번째 차례인지
                break;
            }

        }


        return answer;
    }



}
