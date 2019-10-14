import java.util.ArrayList;

// 숫자 야구
public class Programmers_BruteForce3 {


    static int numbers[] = {1,2,3,4,5,6,7,8,9};
    static int answer = 0;
    public static void main(String args[]){

        int baseball[][] = {{123, 1, 1}, {356, 1, 0}, {327, 2, 0}, {489, 0, 1}};
        System.out.println(solution(baseball));

    }


    public static int solution(int[][] baseball) {
        int howMany = 3;
        int arr[] = new int[howMany];
        permutation(numbers,arr,0, numbers.length,howMany,baseball);


        return answer;
    }

    public static boolean checkAnswer(int permResult[],int baseball[][]){
        for(int i=0;i<baseball.length;i++){
            int strike = baseball[i][1];
            int ball = baseball[i][2];

            int baseballNum[] = new int[3];
            baseballNum[0] = String.valueOf(baseball[i][0]).charAt(0)-'0';
            baseballNum[1] = String.valueOf(baseball[i][0]).charAt(1)-'0';
            baseballNum[2] = String.valueOf(baseball[i][0]).charAt(2)-'0';

            int s=0,b=0;
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    if(baseballNum[j]!=permResult[k]){
                        continue;
                    }
                    if(j==k){
                        s++;
                    }else{
                        b++;
                    }
                }
            }

            if(s!=strike||ball!=b){
                return false;
            }
        }

        return true;

    }

    public static void permutation(int numbers[],int arr[],int depth,int size,int howMany,int baseball[][]){
        if(depth == howMany){

            if(checkAnswer(arr,baseball)){
                answer+=1;
            }


            return;
        }

        for(int i = depth;i<size;i++){
            swap(numbers,depth,i);
            arr[depth] = numbers[depth];
            permutation(numbers,arr,depth+1,size,howMany,baseball);
            swap(numbers,depth,i);
        }

    }

    static void swap(int[] arr, int depth, int i) {
        int temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }
}
