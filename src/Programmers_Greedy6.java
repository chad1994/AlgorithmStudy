import java.util.Arrays;
import java.util.Comparator;

public class Programmers_Greedy6 {

    public static void main(String args[]){

        int[][] routes = {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}};
        System.out.println(solution(routes));

    }


    public static int solution(int[][] routes) {
        int answer = 0;

        Arrays.sort(routes, new Comparator<int[]>() { // 시작 구간 순으로 정렬
            @Override
            public int compare(int[] o1, int[] o2) {
                final int r1 = o1[0];
                final int r2 = o2[0];

                return Integer.compare(r1,r2);
            }
        });

        int start_max = routes[0][0]; //시작시점중 가장 뒤에있는
        int end_min = routes[0][1]; //끝시점중 가장 앞에있는
        answer=1; //카운트 초기값

        for(int i=1;i<routes.length;i++){
            if(end_min<routes[i][0]){ // 최소끝 지점보다 시작점이 더 뒤에 있다면,
                answer++; // 카메라가 하나 더 필요함
                start_max = routes[i][0]; // 다시 초기화
                end_min = routes[i][1];   //    ,,

            }else{ // 시작이점이 끝지점을 초과하는 경우가 아니면 카메라가 하나더 불필요.

                if(start_max<routes[i][0]){ // 더 큰 시작값과 더 작은 끝값으로 반복 저장.
                    start_max = routes[i][0];
                }
                if(end_min>routes[i][1]){
                    end_min = routes[i][1];
                }
            }
        }



        return answer;
    }

}
