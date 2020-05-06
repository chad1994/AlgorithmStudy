public class Kakao2019Intern_steppingStones {
    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int[] stones2 = stones;
        stones2[2] -= 1;
        int k = 3;
        System.out.println(solution2(stones, k));
    }



    public static int solution(int[] stones, int k) { // 전체 탐색 (막무가내 ver)
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < stones.length - k + 1; i++) {
            int max = 0;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, stones[j]);
            }
            answer = Math.min(answer, max);
        }
        return answer;
    }


    /*
    징검다리의 최대값이 3이라면 최대로 건널수 있는 사람의 수가 3명이라는 뜻이 되며,
    징검다리의 최소값이 1이라면 적어도 1명의 사람은 건널 수 있다는 의미가 된다.
    또한 순차대로 건넌다고 가정할때, 0의 값으로 k값 만큼 연속한 징검다리가 나온다면 더 이상 건널 수 없다는 의미가 된다.
    m(최소값)과 M(최대값)의 중간값을 잡아 mid라고 하고 mid가 건널 수 있는지 없는지를 기준으로 이분탐색을 한다.
    (mid-1)번째 사람이 건너지 못했다면 m~mid로 범위를 좁히고,
    (mid-1)번째 사람이 성공적으로 건넜다면 mid~max로 범위를 좁힌다.
    이 과정을 반복하면 min = 마지막으로 건넌 사람 / max = 건너지 못한사람이 되므로
    mid = (min+max)/2 = 결국 min이 되므로
    mid 를 반환한다.
     */
    public static int solution2(int[] stones, int k) { // 이분 탐색
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stones.length; i++) {
            max = Math.max(max, stones[i]);
            min = Math.min(min, stones[i]);
        }

        int mid = (max + min) / 2;
        while (min!=mid) {
            int[] arr = copyArray(stones);
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                arr[i] -= (mid - 1);
                if (arr[i] <= 0) {
                    count++;
                } else {
                    count = 0;
                }
                if (count >= k) {
                    break;
                }
            }
            if (count >= k) { //1~M-1
                max = mid;
                mid = (max + min) / 2;
            } else { // M~MAX
                min = mid;
                mid = (max + min) / 2;
            }

        }

        return mid;
    }

    public static int[] copyArray(int[] arr) {
        int[] ret = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ret[i] = arr[i];
        }
        return ret;
    }
}
