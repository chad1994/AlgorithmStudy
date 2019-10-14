import java.util.*;

//소수 찾기
public class Programmers_BruteForce2 {

    static List<Integer> list = new ArrayList<>();

    public static void main(String args[]){

        System.out.println(solution("011"));
    }

    public static int solution(String numbers) {
        int answer = 0;

        HashSet<Integer> set = new HashSet<>();

        int nums[] = new int[numbers.length()];
        for(int i=0 ; i<numbers.length();i++){ //숫자 분리
            nums[i] = numbers.charAt(i)-'0';
        }


        for(int i=1;i<=nums.length;i++){
            int arr[] = new int[i];
            permutation(nums,arr,0,nums.length,i); // 순열을 통해 구할 수 있는 모든 조합 list에 추가

            for(int a : list){
                set.add(a); //hashset을 사용하여 중복 제거
            }
        }

        Iterator<Integer> iter = set.iterator();
        while(iter.hasNext()){
            if(checkPrimeNumber(iter.next())){
                answer++;
            }
        }




        return answer;
    }

    public static void permutation(int numbers[],int arr[],int depth,int size,int howMany){
        if(depth == howMany){
            String str = "";
            for(int i = 0 ;i<howMany;i++){
                str+=arr[i];
            }
            list.add(Integer.parseInt(str));
            return;
        }

        for(int i = depth;i<size;i++){
            swap(numbers,depth,i);
            arr[depth] = numbers[depth];
            permutation(numbers,arr,depth+1,size,howMany);
            swap(numbers,depth,i);
        }

    }

    static void swap(int[] arr, int depth, int i) {
        int temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }

    static boolean checkPrimeNumber(int num){

        if(num <= 1) return false;

        boolean[] arr = new boolean[num+1];
        arr[0] = arr[1] = false;
        for(int i=2; i<=num; i+=1) {
            arr[i] = true;
        }

        for(int i=2; i*i<=num; i+=1) { //에라토스테네스의 체를 이용하여 소수를 체크 (소수 인덱스는 true)
            for(int j=i*i; j<=num; j+=i) {
                arr[j] = false;
            }
        }

        if(arr[num]){
            return true;
        }else{
            return false;
        }
    }

}
