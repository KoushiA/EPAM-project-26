import java.util.*;

public class MaxLearningPoints {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        long[] points = new long[N];

        for (int i = 0; i < N; i++) {
            points[i] = sc.nextLong();
        }

        if (N == 1) {
            System.out.println(points[0]);
            return;
        }

        long[] dp = new long[N];

        dp[0] = points[0];
        dp[1] = Math.max(points[0], points[1]);

        for (int i = 2; i < N; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + points[i]);
        }

        System.out.println(dp[N - 1]);

        sc.close();
    }
}
