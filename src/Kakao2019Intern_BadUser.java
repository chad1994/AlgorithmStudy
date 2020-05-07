import java.util.*;

public class Kakao2019Intern_BadUser {
    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] user_id2 = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "abc1**"};
        String[] banned_id2 = {"fr*d*", "*rodo", "******", "******"};

        System.out.println("answer: "+ solution(user_id2, banned_id2));
    }

    public static Set<String> set = new HashSet<>();
    public static boolean[] visited;

    /*
    풀이중 막혔던 이유 :
    순열이 아닌 조합을 사용함.
    해당 문제에서 결과적으로 순서에 구애 받지 않고 정답을 구해야 한다는 조건에 빠져,
    조합을 사용해서 비교하려 했으나, 그렇게 되면 또 결국 구한 조합에대해 banned_id 를 완전탐색하여 매칭시켜봐야함.
    순열을 사용해서 모든 경우를 비교하여, 일치하는 모든 원소를 정렬하여 set에 저장하여 set의 크기를 반환하면 됨.
    ex) A B C D 와 A C B D -> A B C D 와 A B C D
     */

    public static int solution(String[] user_id, String[] banned_id) {
        int answer = 0;

        for (int i = 0; i < banned_id.length; i++) {
            banned_id[i] = banned_id[i].replace("*", ".");
        }

        String[] banned_list_com = new String[banned_id.length];
        ArrayList<String> banned_list_per = new ArrayList<>();
//        combination(user_id, banned_id, banned_list_com, user_id.length, banned_id.length, 0, 0);
        visited = new boolean[user_id.length];
        permutation(user_id, banned_id, user_id.length,banned_id.length, banned_list_per, visited);
        answer = set.size();

        return answer;
    }

    //순열
    private static void permutation(String[] user_id, String[] banned_id,int n, int r, ArrayList<String> perArr, boolean[] visited) {
        if(perArr.size() == r){
            boolean flag = true;
            for(int i=0;i<r;i++){
                if(!perArr.get(i).matches(banned_id[i])){
                    flag = false;
                    break;
                }
            }

            if(flag){
                StringBuilder sb = new StringBuilder();
                ArrayList<String> sortedList = new ArrayList();
                for(String i :perArr ){
                    sortedList.add(i);
                }
                Collections.sort(sortedList);
                for(String i : sortedList){
                    sb.append(i);
                }
                set.add(sb.toString());
            }
            return;
        }

        for(int i=0; i<n; i++){
            if(!visited[i]){
                perArr.add(user_id[i]);
                visited[i] = true;
                permutation(user_id, banned_id ,n, r, perArr, visited);
                visited[i] = false;
                perArr.remove(perArr.size()-1);
            }
        }

    }

    //조합
//    private static void combination(String[] user_id, String[] banned_id, String[] comArr, int n, int r, int index, int target) {
//        if (r == 0) {
//
//            for (String i : comArr) {
//                System.out.print(i + " ");
//            }
//            System.out.println();
//            visited = new boolean[user_id.length];
//            return;
//        }
//        if (target == n) return;
//
//        comArr[index] = user_id[target];
//
//        if(user_id[target].matches(banned_id[index])){
//            //visited[target] = true; & target, index 증가
//            //visited[target] = false; & target증가 index 그대로
//        }
//        combination(user_id, banned_id, comArr, n, r - 1, index + 1, target + 1);
//        combination(user_id, banned_id, comArr, n, r, index, target + 1);
//
//    }
}
