import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kakao2019Intern_BadUser {
    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "abc1**"};


        solution(user_id, banned_id);
    }

    static ArrayList<String> bannedList = new ArrayList<>();

    public static int solution(String[] user_id, String[] banned_id) {
        int answer = 0;

        for(String str : banned_id){
            bannedList.add(str.replace("*","."));
        }

        String[] banned_list = new String[banned_id.length];
        combination(user_id, banned_id, banned_list, user_id.length, banned_id.length, 0, 0);

        return answer;
    }

    private static void combination(String[] user_id, String[] banned_id, String[] comArr, int n, int r, int index, int target) {
        if (r == 0) {

            for (String i : comArr) {

            }
            System.out.println();
            return;
        }
        if (target == n) return;

        comArr[index] = user_id[target];

        combination(user_id, banned_id, comArr, n, r - 1, index + 1, target + 1); // 선택된 경우
        combination(user_id, banned_id, comArr, n, r, index, target + 1);// 선택되지 않은 경우
    }
}
