public class WinterSummer2018_CollectStickers {

    public static void main(String[] args) {
        int sticker[] = {14, 6, 5, 11, 3, 9, 2, 10};

        System.out.println(solution(sticker));
    }

    public static int solution(int sticker[]) {
        if (sticker.length == 1){
            return sticker[0];
        } else if (sticker.length < 3) {
            return Math.max(sticker[0], sticker[1]);
        } else {
            return Math.max(dp_from_zero(sticker), dp_from_one(sticker));
        }
    }

    public static int dp_from_zero(int sticker[]) {
        int[] dp = new int[sticker.length];

        dp[0] = sticker[0];
        dp[1] = dp[0];

        for (int i = 2; i < sticker.length - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + sticker[i], dp[i - 1]);
        }

        return dp[sticker.length - 2];
    }


    public static int dp_from_one(int sticker[]) {
        int[] dp = new int[sticker.length];

        dp[0] = 0;
        dp[1] = sticker[1];

        for (int i = 2; i < sticker.length; i++) {
            dp[i] = Math.max(dp[i - 2] + sticker[i], dp[i - 1]);
        }

        return dp[sticker.length - 1];
    }
}
