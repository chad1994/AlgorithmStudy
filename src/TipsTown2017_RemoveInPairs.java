import java.util.Stack;

public class TipsTown2017_RemoveInPairs {
    public static void main(String[] args) {
        System.out.println(solution("cdcd"));
    }

    public static int solution(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i=0;i<s.length();i++){
            if(!stack.isEmpty() && stack.peek() == s.charAt(i)){
                stack.pop();
            }else{
                stack.push(s.charAt(i));
            }
        }

        if(stack.isEmpty()){
            return 1;
        }else{
            return 0;
        }
    }
}
