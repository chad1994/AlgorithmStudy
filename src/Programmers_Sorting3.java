import java.util.Arrays;

public class Programmers_Sorting3 {

    public static void main(String args[]){


        int[] citations= {22,42};

        System.out.println(solution(citations));

    }

    public static int solution(int[] citations) {
        int answer = 0;
        int n = citations.length;

        //n-i = 해당 논문보다 더 많이 인용된 논문의 개수
        //i = 나머지 논문의 개수.
        Arrays.sort(citations);

        for(int i=0;i<=citations.length;i++){
            int largeCount = 0;
            int lessCount = 0;
            for(int j=0;j<citations.length;j++){
                if(i<=citations[j]) {
                    largeCount++;
                }
            }
            if (largeCount >= i) {
                answer = i;
            }
        }

        return answer;
    }

}
