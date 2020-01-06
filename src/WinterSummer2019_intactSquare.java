public class WinterSummer2019_intactSquare {

    public static void main(String args[]){
        System.out.println(solution(12,20));
    }


    public static long solution(int w,int h) {


        long a = w<h?h:w; //더 긴변 = a
        long b = w>=h?h:w; // 짧은 변 = b
        long sum = a*b;

        if(a==1||b==1){ // 높이나 너비가 1이면 남는 사각형이 없음.
            return 0;

        }else if(a==b){ // 높이와 너비가 같다면 그 높이만큼의 사각형만 빼주면 됨.
            return sum - a;

        }else{ // 그 외 일정 패턴의 블럭으로 반복적으로 겹치는 경우들
            long gcd = gcd(a,b);
            long regularBlock = a/gcd + b/gcd - 1; // 패턴의 블럭 개수 = (최대공약수로 구한) 높이 + 너비 - 1
            long regularBlock_height = a/gcd; // 패턴 블럭 높이 ( 패턴 반복 개수 )

            return sum - regularBlock*(a/regularBlock_height); // 전체 개수에서 (패턴 블럭 개수 * 패턴 반복 개수)를 빼면 정답.

        }


    }

    public static long gcd(long a, long b){ //최대 공약수 구하는 공식, = 큰수에서 작은수 뺴고 그 값에서 또 뺴고 반복하여 작은수가 0이 될때 까지 반복하여 남는수.
        if(b==0){
            return a;
        }else{
            if(a%b>=b){
                return gcd(a%b,b);
            }else{
                return gcd(b,a%b);
            }
        }
    }
};
