public class Programmers_BestSet {
    public static void main(String[] args) {
        int n = 10;
        int s = 10005;
        int answer[] = solution(n,s);

        for(int i=0;i<answer.length;i++){
            System.out.println(answer[i]);
        }
    }

    public static int[] solution(int n, int s) {
        int[] answer = {};

        if(s < n){
            answer = new int[1];
            answer[0] = -1;
            return answer;
        }

        if(s%n==0){
            answer = new int[n];
            for(int i=0;i<n;i++){
                answer[i] = s/n;
            }
        }else{
            int center = s/n;
            int modResult = s%n;
            answer = new int[n];
            for(int i=0;i<n;i++){
                if(i+modResult >= n){
                    answer[i] = center+1;
                }else{
                    answer[i] = center;
                }
            }

        }

        return answer;
    }
}
