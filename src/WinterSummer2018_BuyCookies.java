public class WinterSummer2018_BuyCookies {

    public static void main(String args[]){

        int[] cookie = {1,1,2,3};

        System.out.println(solution(cookie));
    }

    public static int solution(int[] cookie){
        int answer = -1;
        int sizeOfCookie = cookie.length;

        for(int i=0; i<sizeOfCookie-1 ; i++){
            int son1Index = i;
            int son2Index = i+1;
            int son1 = cookie[son1Index];
            int son2 = cookie[son2Index];

            while(true){
                if(son1 == son2 && son1>answer){
                    answer = son1;
                }

                if(son1Index > 0 && son1 <= son2){
                    son1Index--;
                    son1 += cookie[son1Index];
                }else if(son2Index < sizeOfCookie-1 && son1>=son2){
                    son2Index++;
                    son2 += cookie[son2Index];
                }else{
                    break;
                }
            }

        }

        if(answer==-1){
            return 0;
        }
        return answer;
    }


}
