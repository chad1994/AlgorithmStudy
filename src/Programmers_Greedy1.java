public class Programmers_Greedy1 {
        public static int solution(int n, int[] lost, int[] reserve) {

            int count=0;

            for(int i=0;i<lost.length;i++){ // 자신의 체육복 잃어버린 사람 제거
                for(int j=0;j<reserve.length;j++){
                    if(lost[i]==reserve[j]){
                        lost[i]=0;
                        reserve[j]=0;
                        break;
                    }
                }
            }
            for(int i=0;i<lost.length;i++){ // 인근 친구가 체육복을 안가져왔다면 빌려주기 (-1 , +1)
                for(int j=0;j<reserve.length;j++){
                    if(lost[i]+1==reserve[j]){
                        lost[i]=0;
                        reserve[j]=0;
                        break;
                    }
                    else if(lost[i]-1==reserve[j]&&lost[i]!=1){
                        lost[i]=0;
                        reserve[j]=0;
                        break;
                    }
                }
            }

            for(int i=0;i<lost.length;i++){ // 결과적으로 체육복이 없는 학생 카운트 체크
                if(lost[i]!=0){
                    count++;
                }
            }

            return n-count;

        }

        public static void main (String args[]){


        }

}
