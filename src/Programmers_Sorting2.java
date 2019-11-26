import java.util.Arrays;
import java.util.Comparator;

public class Programmers_Sorting2 {

    public static void main(String args[]){

        int[] numbers = {3, 30, 34, 5, 9};
        System.out.println(solution(numbers));
    }

    public static String solution(int[] numbers) {
        String answer = "";
        String number[] = new String[numbers.length];
        for(int i=0;i<numbers.length;i++){
            number[i]= String.valueOf(numbers[i]);
        }

        Comparator<String> comparator = new Comparator<String>(){ // 정렬 기준 정의
            @Override
            public int compare(String a, String b){
                return (b+a).compareTo(a+b);
            }
        };

        Arrays.sort(number,comparator);

        if(number[0].equals("0")){
            return "0";
        }

        for(int i=0;i<number.length;i++){
            answer+=number[i];
        }


        return answer;
    }
}
