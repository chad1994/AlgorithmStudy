import java.util.Stack;

public class Programmers_LongestPalindrome {


    public static void main(String[] args) {
        String str = "abcdcba";


        System.out.println(solution(str));
    }

    public static int solution(String s) {
        int answer = 0;
        boolean findAnswer = false;

        for (int i = s.length(); i > 0; i--) {
            for (int j = 0; j < s.length() - i + 1; j++) {
                if (isPalindrome(s,j,j+i-1)) {
                    answer = i;
                    findAnswer = true;
                    break;
                }
            }
            if (findAnswer) {
                break;
            }
        }
        return answer;
    }

    private static boolean isPalindrome(String str,int start,int end) {
        for (int i = 0; i < (end-start+1)/2; i++) {
            System.out.println(str.charAt(start+i)+"/"+str.charAt(end-i));
            if (str.charAt(start+i) != str.charAt(end-i)) {
                return false;
            }
        }
        return true;
    }
}
