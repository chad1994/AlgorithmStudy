public class Kakao_Winter5 {

    public static void main(String args[]){
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;

        System.out.println(solution(stones,k));
    }

    public static int solution(int[] stones, int k) {
        int answer = 0;


        boolean isFinish = false;
        while(!isFinish){
            int count_k = 1;
            for(int i=0;i<stones.length;i++){
                if(stones[i]!=0){
                    stones[i]--;
                    count_k=1;
                }else{
                    count_k++;
                    if(count_k>k){
                        isFinish = true;
                        break;
                    }else{
                        continue;
                    }
                }
            }
            if(!isFinish){
                answer++;
            }

        }

        return answer;
    }



}

