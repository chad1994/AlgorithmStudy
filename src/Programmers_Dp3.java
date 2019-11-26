public class Programmers_Dp3 {
    public static void main(String args[]){
        int[][] triangle  ={{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println(solution(triangle));
    }

    public static int solution(int[][] triangle) {

        /*
        첫번째 행부터 마지막 행까지 반복하며
        그 인덱스에 올 수 있는 최대값을 구해가며 내려감.
         */

        for(int level=1;level<triangle.length;level++) { // 행반복,
            for (int i = 0; i < triangle[level].length; i++) {
                if (i == 0) { //맨 왼쪽 열일 때.
                    triangle[level][i] += triangle[level - 1][i];
                } else if(i == triangle[level].length-1){ //맨 오른쪽 열일 때.
                    triangle[level][i] += triangle[level - 1][i-1];
                } else { // 나머지
                    triangle[level][i] += Integer.max(triangle[level - 1][i - 1], triangle[level - 1][i]);
                }
            }
        }

        int max = 0;
        for(int i=0;i<triangle[triangle.length-1].length;i++){ // 구한 마지막 행중에서 가장 큰 값 뽑아냄
            if(max < triangle[triangle.length-1][i]){
                max = triangle[triangle.length-1][i];
            }
        }

        return max;
    }


}
