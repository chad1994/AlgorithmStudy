

// 쇠막대기 //


public class Programmers_StackAndQueue3 {
    public static int solution(String arrangement) {
        int answer=0;
        int count=0;

        for(int i=0;i<arrangement.length();i++){
            if(arrangement.charAt(i)=='('){
                count++;
            }else{
                count--;
                if(arrangement.charAt(i-1)=='('){
                    answer+=count;
                }else{
                    answer+=1;
                }
            }
        }

        return answer;
    }

    public static void main (String args[]){

        String arrangement = "((((())(())))(()))";
        System.out.println(solution(arrangement));

    }

}
