
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Kakao2018_NewsClustering {

    static HashMap<String, Integer> set1 = new HashMap<>();
    static HashMap<String, Integer> set2 = new HashMap<>();
    static HashSet<String> allSet = new HashSet<>();
    static HashMap<String, Integer> unionSet = new HashMap<>();
    static HashMap<String, Integer> intersectionSet = new HashMap<>();

    public static void main(String[] args) {
        int answer = solution("E=M*C^2", "e=m*c^2");
        System.out.println(answer);

    }

    public static int solution(String str1, String str2) {
        int answer = 0;

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        for (int i = 0; i < str1.length() - 1; i++) {
            if (str1.charAt(i) >= 'a' && str1.charAt(i) <= 'z' && str1.charAt(i + 1) >= 'a' && str1.charAt(i + 1) <= 'z') {
                String s = "" + str1.charAt(i) + str1.charAt(i + 1);
                if (set1.containsKey(s)) {
                    set1.put(s, set1.get(s) + 1);
                } else {
                    set1.put(s, 1);
                    allSet.add(s);
                }
            }
        }
        for (int i = 0; i < str2.length() - 1; i++) {
            if (str2.charAt(i) >= 'a' && str2.charAt(i) <= 'z' && str2.charAt(i + 1) >= 'a' && str2.charAt(i + 1) <= 'z') {
                String s = "" + str2.charAt(i) + str2.charAt(i + 1);
                if (set2.containsKey(s)) {
                    set2.put(s, set2.get(s) + 1);
                } else {
                    set2.put(s, 1);
                    allSet.add(s);
                }
            }
        }

        Iterator<String> iterator = allSet.iterator();

        while (iterator.hasNext()) {
            String s = iterator.next();
            if (set1.get(s) == null) {
                unionSet.put(s, set2.get(s));
            } else if (set2.get(s) == null) {
                unionSet.put(s, set1.get(s));
            } else {
                unionSet.put(s, Math.max(set1.get(s), set2.get(s)));
                intersectionSet.put(s, Math.min(set1.get(s), set2.get(s)));
            }
        }

        if (unionSet.size() == 0) {
            answer = 65536;
        } else {
            int unionCount = 0;
            int interCount = 0;

            Iterator<Integer> iter1 = intersectionSet.values().iterator();
            Iterator<Integer> iter2 = unionSet.values().iterator();

            while (iter1.hasNext()) {
                interCount += iter1.next();
            }

            while (iter2.hasNext()) {
                unionCount += iter2.next();
            }

            answer = (int) (((double) interCount / unionCount) * 65536);
        }

        return answer;
    }
}
