public class WinterSummer2018_EditTerrain {

    public static void main(String args[]){
//        int land[][] = {{1,2},{2,3}};
        int land[][] = {{4,4,3},{3,2,2},{2,1,0}};
        int P = 5;
        int Q = 3;

        System.out.println(solution2(land,P,Q));
    }

    /*
    // 최저 높이 ~ 최대 높이까지 완전탐색, 결과 효율성 처참..
    // 소요 시간 : 5분
    public static long solution(int[][] land, int P, int Q){
        long answer = Integer.MAX_VALUE;

        long max_height=Integer.MIN_VALUE;
        long min_height=Integer.MAX_VALUE;

        int pCount,qCount;

        for(int i=0;i<land.length;i++){
            for(int j=0;j<land[i].length;j++){
                max_height = Math.max(max_height,land[i][j]);
                min_height = Math.min(min_height,land[i][j]);
            }
        }

        for(long h=min_height;h<=max_height;h++){
            pCount=0;
            qCount=0;

            for(int i=0;i<land.length;i++){
                for(int j=0;j<land[i].length;j++){
                    if(land[i][j]<h){
                        pCount += (h-land[i][j]);
                    }else if(land[i][j]>h){
                        qCount += (land[i][j]-h);
                    }
                }
            }

            answer = Math.min(answer,(pCount*P+qCount*Q));
        }

        return answer;
    }
    */

    // 파라메트릭 서치 (이분탐색과 유사) 를 활용
    // 소요 시간 : 1시간
    public static long solution2(int[][] land, int P, int Q){
        long answer;

        long max_height=Integer.MIN_VALUE;
        long min_height=Integer.MAX_VALUE;

        for(int i=0;i<land.length;i++){
            for(int j=0;j<land[i].length;j++){
                max_height = Math.max(max_height,land[i][j]);
                min_height = Math.min(min_height,land[i][j]);
            }
        }

        long mid=0;
        while(min_height <= max_height){
             mid = (min_height+max_height)/2;

             if(max_height == min_height)
                 break;

            long left = calc(land,P,Q,mid);
            long right = calc(land,P,Q,mid+1);


            if(left == right)
                break;

            if(left < right){
                max_height = mid;
            }else{
                min_height = mid+1;
            }
        }
        answer = calc(land,P,Q,mid);

        return answer;
    }

    public static long calc(int[][] land, int P, int Q, long height){

        long pCount = 0;
        long qCount = 0;

        for(int i=0;i<land.length;i++){
            for(int j=0;j<land[i].length;j++){
                if(land[i][j] < height){
                    pCount += (height - land[i][j]);
                }else if(land[i][j] > height){
                    qCount += (land[i][j] - height);
                }
            }
        }

        return (pCount*P)+(qCount*Q);
    }


}
