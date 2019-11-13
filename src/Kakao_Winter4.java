import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Kakao_Winter4 {

    public static void main(String args[]){

        long[] room_number = {30,40,30,41,100,1};
        long k = 100;
        long[] answer = solution2(k,room_number);

        for(int i=0;i<answer.length;i++){
            System.out.println(answer[i]);
        }

    }

    public static long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        Set<Long> set = new HashSet<>();


        for(int i=0;i<room_number.length;i++){
            if (!set.contains(room_number[i])) {
                answer[i] = room_number[i];
                set.add(room_number[i]);
            }else{
                int count=1;
                while(true){
                    if(!set.contains(room_number[i]+count)){
                        answer[i] = room_number[i]+count;
                        set.add(room_number[i]+count);
                        break;
                    }else{
                        count++;
                    }
                }
            }
        }

        return answer;
    }

    public static long[] solution2(long k, long[] room_number) {
        Set<Long> set = new HashSet<>();

        for(int i=0;i<room_number.length;i++)
            room_number[i] = recursive(set,room_number[i]);


        return room_number;
    }



    public static long recursive(Set<Long> set,long room_number){
        long answer;
        if(!set.contains(room_number)){
            answer = room_number;
            set.add(room_number);
            return answer;
        }else{
            return recursive(set,room_number+1);
        }
    }





}
