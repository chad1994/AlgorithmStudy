public class Programmers_BfsDfs1 {

    public static void main(String args[]){
        int[] numbers = {};
        int target = 0;

        solution(numbers,target);
    }

    public static int solution(int[] numbers, int target) {
        int answer = 0;

        answer = dfs(numbers,target,0,0);

        return answer;
    }

    public static int dfs(int[] numbers,int target,int index,int num){
        if(index == numbers.length) {
            return num == target ? 1 : 0;
        } else {
            return dfs(numbers, target, index + 1, num + numbers[index])
                    + dfs(numbers, target, index + 1, num - numbers[index]);
        }
    }
}
