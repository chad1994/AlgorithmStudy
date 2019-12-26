import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_17822 {

    static final int[] directionI = {0,0,1,-1};
    static final int[] directionJ = {1,-1,0,0}; //인접한 위치 구하는 방향 : 같은원의 오른쪽, 같은원의 왼쪽, 바깥원의 같은 위치
    static boolean nothingToRemove = true;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //원판 개수
        int M = Integer.parseInt(st.nextToken()); //원판의 숫자 개수
        int T = Integer.parseInt(st.nextToken()); //총 회전 횟수

        int[][] num = new int[N][M];
        int[] x = new int[T]; //돌릴 원판의 공약수
        int[] d = new int[T]; //돌릴 방향 ,0시계 1반시계
        int[] k = new int[T]; //한번에 돌릴 횟수

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<T;i++){
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
            k[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<T;i++){
            for(int j=x[i]-1;j<N;j+=x[i]){ //x의 배수만큼 증가하며 원판개수 보다 작은 동안 (회전)
                num[j] = rotateCircle(k[i],d[i],num[j]);
            }
            removeAdjacentNum(num); //삭제(+삭제 안될시 평균값 처리)
        }

        System.out.println(total(num));



    }

    //테스트용 출력 함수
    public static void printNums(int[][] num){
        for(int i=0;i<num.length;i++){
            for(int j=0;j<num[i].length;j++){
                System.out.print(num[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }



    //인접한 원소 제거 함수 void
    public static void removeAdjacentNum(int[][] num){
        nothingToRemove = true; //지울것이 있는지 없는지 판별하는 플래그
        for(int i=0;i<num.length;i++){
            for(int j=0;j<num[i].length;j++){
                boolean[][] visited = new boolean[num.length][num[0].length];
                if(num[i][j]>0){
                    int count = 0; // 몇번째로 비교하고 있는것인지 (= 해당 차례에 끝이날 경우 해당 위치까지 같은 부분인지 판별하기 위함)
                    dfs(num,i,j, count,visited);
                }else{
                    continue;
                }
            }
        }

        if(nothingToRemove){ //인접한 수중에 지울것이 없는경우
            plusMinus((double)total(num)/getCountNotZero(num),num);
        }
    }

    //인접한 수중 지울것이 없어 평균값과 비교하여 +-1 해주는 함수
    public static void plusMinus(double avg,int[][] num){
        for(int i=0;i<num.length;i++) {
            for (int j = 0; j < num[i].length; j++) {
                if(num[i][j]!=0 && num[i][j]>avg){
                    num[i][j]-=1;
                }else if (num[i][j]!=0 && num[i][j]<avg){
                    num[i][j]+=1;
                }
            }
        }
    }

    //dfs를 활용하여 0이 아닌 인접한 수들을 재귀탐색하여 제거
    public static void dfs(int[][]num, int i, int j,int count,boolean[][] visited){
        int temp = num[i][j]; //중간에 값이 바뀌어 비교하고자 하는 수가 유실될수 있기 때문에 temp변수에 저장한다.
        for(int x=0;x<directionI.length;x++){
            int di = i+directionI[x];
            int dj = j+directionJ[x];

            if(dj>=num[i].length){ //인덱스 범위 제한 (length-1보다 작도록)
                dj = 0;
            }else if(dj<0){ //인덱스 범위 제한 (0보다 크도록)
                dj = num[i].length-1;
            }


            if(di>=0 && di<num.length && !visited[di][dj] && num[di][dj] == temp){
                count = Integer.valueOf(count + 1);
                num[i][j] = 0;
                visited[i][j] = true;
                dfs(num,di,dj,count,visited);
            }else{
                if(count>0){ //이 뒤에는 없지만 이 위치까지는 같은 수이므로 0으로 초기화. but, 0 이라면 이위치가 처음이기 때문에 넘김.
                    num[i][j] = 0;
                    nothingToRemove = false;
                }
            }
        }
    }


    //원판 회전(원소 변경)함수 , param : (회전 횟수, 방향, 리스트)
    public static int[] rotateCircle(int times,int direction,int[] num){
        if(direction == 0){ //시계 방향
            int[] tempNum = new int[num.length];
            for(int i=0;i<num.length;i++){
                tempNum[(i+times)%num.length] = num[i];
            }
            return tempNum;
        }else{ //반시계 방향
            int[] tempNum = new int[num.length];
            for(int i=0;i<num.length;i++){
                int index = i-times;
                while(index<0){
                    index += num.length;
                }
                tempNum[index] = num[i];
            }
            return tempNum;
        }
    }



    // 남은 원소 합
    public static int total(int[][] num){
        int sum = 0;
        for(int i=0;i<num.length;i++){
            for(int j=0;j<num[i].length;j++){
                sum+=num[i][j];
            }
        }

        return sum;
    }

    //0이 아닌 원소들의 개수
    public static int getCountNotZero(int[][] num){
        int count = 0;
        for(int i=0;i<num.length;i++){
            for(int j=0;j<num[i].length;j++){
                if(num[i][j]!=0) {
                    count ++;
                }
            }
        }

        return count;
    }


}
