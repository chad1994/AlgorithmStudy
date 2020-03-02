public class WinterSummer2018_JumpAndTeleport {

    public static void main(String[] args) {
        System.out.println(solution(5000));
    }

    public static int solution(int n) {
        int ans = 0;

        while (n != 0) {
            if (n%2 == 0) {
                n/=2;
            } else {
                n-=1;
                ans++;
            }
        }
        return ans;
    }
}

/*
===============
n   |   ans
===============
5000
2500
1250
625
624     +1
312
156
78
39
38      +1
19
18      +1
9
8       +1
4
2
1
0       +1
 */
