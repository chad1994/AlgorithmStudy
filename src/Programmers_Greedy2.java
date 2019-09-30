public class Programmers_Greedy2 {

    public static int solution(String name){
        int answer = 0;
        int mid = 65+13;
        int direction = 0; // 1정 2역
        int notACount=0;

        for(int i=0;i<name.length();i++){
            if(name.charAt(i)!='A'){
                direction = 1;
                break;
            }
            if(name.charAt(name.length()-(i+1))!='A'){
                direction = 2;
                break;
            }
        }

        for(int i=0;i<name.length();i++){
            if(name.charAt(i)!='A'){
                notACount++;
            }
        }


        if(direction == 1) {
            for (int i = 0; i < name.length(); i++) {
                answer += 1;
                if(notACount==0){
                    break;
                }
                if(name.charAt(i)=='A'){
                    continue;
                }else {
                    if (name.charAt(i) > mid) {
                        answer += 91 - (name.charAt(i));
                        notACount--;
                    } else {
                        answer += (name.charAt(i) - 65);
                        notACount--;
                    }
                }
            }
        }else{
            answer+=1;
            for(int i = name.length()-1;i>=0;i--){
                answer+=1;
                if(notACount==0){
                    break;
                }
                if(name.charAt(i)=='A'){
                    continue;
                }else {
                    if (name.charAt(i) > mid) {
                        answer += 91 - (name.charAt(i));
                        notACount--;
                    } else {
                        answer += (name.charAt(i) - 65);
                        notACount--;
                    }
                }
            }
        }
        if(name.charAt(1)=='A'||name.charAt(name.length()-1)=='A'){
            answer-=1; // 두번째나 마지막에 오면 규칙 상 하나 줄일 수 있음
        }
        answer-=1;

        return answer;
    }

    public static void main(String args[]){
        String str = "AABAAAAAAABBB";
        System.out.println(solution(str));
    }
}


