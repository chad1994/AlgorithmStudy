public class Programmers_124country {

    public static void main(String args[]){

        System.out.println(solution(10));
    }

    public static String solution(int n) {
        StringBuilder sb = new StringBuilder();
        int restNum=0;

        for(int i=n;i>0;){
            restNum = i%3;
            i = i/3;
            if (restNum == 0) {
                restNum = 4;
                i-=1;
            }
            sb.insert(0,restNum);
        }

        return sb.toString();
    }


}
