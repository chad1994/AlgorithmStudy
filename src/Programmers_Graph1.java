import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Programmers_Graph1 {

    public static void main(String args[]){
        int n = 6;
        int arr[][] = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        System.out.println(solution(n,arr));
    }

    public static int solution(int n, int[][] edge) {
        int answer = 0;
        ArrayList<ArrayList<Integer>> list = new <ArrayList<Integer>> ArrayList();
        int[] visitCount = new int[n+1];
        Arrays.fill(visitCount, 0);

        for(int i = 0; i < edge.length; i++) {
            list.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < edge.length; i++) {
            int m = edge[i][0];
            int h = edge[i][1];
            list.get(m).add(h);
            list.get(h).add(m);
        }

        bfs(1,visitCount,list);

        int max = visitCount[visitCount.length-1];
        for(int i : visitCount){
            if(i==max){
                answer++;
            }
        }

        return answer;

    }


    public static void bfs(int start,int[] visitCount,ArrayList<ArrayList<Integer>> list){

        LinkedList<Integer> queue = new LinkedList<>();

        visitCount[start] = 1;
        queue.add(start);

        while(!queue.isEmpty()){
            int node = queue.poll();

            for(int i : list.get(node)){
                if(visitCount[i] == 0){
                    visitCount[i] = visitCount[node]+1;
                    queue.add(i);
                }
            }
        }

        Arrays.sort(visitCount);

    }
}
