

//카펫
public class Programmers_BruteForce4 {

    public static void main(String args[]){

        int brown = 10;
        int red = 2;

        int[] answer = solution(brown,red);


    }

    public static int[] solution(int brown, int red) { //
        int[] answer = new int[2];


        int total = brown + red;
        for(int x=3;;x++){
            int y= total/x;
            if(x*y==total && (x*2+y*2)==brown+4){
                System.out.println(y+"/"+x);
                answer[0]=y;
                answer[1]=x;
                break;
            }
        }

        return answer;
    }
}
