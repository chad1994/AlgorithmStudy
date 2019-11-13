import java.util.*;

public class Kakao_Winter2 {


    public static void main(String args[]){
        String s= "{{20,111},{111}}";
        int[] a = solution(s);

    }

    public static int[] solution(String s) {
        int[] answer = {};
        HashMap<Integer,Integer> map = new HashMap();

        int number = 0;
        for(int i=0;i<s.length();i++){
            if (s.charAt(i) =='{' || s.charAt(i)=='}' || s.charAt(i)==',') {
                continue;
            }else{
                if(s.charAt(i+1) =='{' || s.charAt(i+1)=='}' || s.charAt(i+1)==','){
                    number+=s.charAt(i)-'0';
                    map.put(number,map.getOrDefault(number,0)+1);
                    number = 0;
                }else{
                    number= (number+(s.charAt(i)-'0'))*10;
                }
            }
        }

        List<Integer> keyList;
        keyList = sortByMapValue(map);

        answer = new int[keyList.size()];

        for(int i=0;i<keyList.size();i++){
            answer[i] = keyList.get(i);
        }

        return answer;
    }

    public static List sortByMapValue(Map map){
        ArrayList list = new ArrayList();
        list.addAll(map.keySet());

        Collections.sort(list, (Comparator) (o1, o2) -> {
            Object v1 = map.get(o1);
            Object v2 = map.get(o2);

            return ((Comparable) v2).compareTo(v1);
        });

        return list;

    }

}
