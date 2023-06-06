import java.util.Arrays;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        // scanner for input
        Scanner input = new Scanner(System.in);

        // structure to hold price and lengths
        int[][] cutPrice;
        int c;
        int v;

        // scan in the N and T inputs
        int N = input.nextInt();
        int T = input.nextInt();

        // add in cut and price values
        cutPrice = new int[N][2];
        for (int i = 0; i < N; i++) {
            c = input.nextInt();
            v = input.nextInt();
            cutPrice[i][0] = c;
            cutPrice[i][1] = v;
        }
        // call function and print results
        System.out.println(pipeCutting(T, cutPrice));

        // input close
        input.close();
    }

    private static int pipeCutting(int t, int[][] cutPrice) {
        // 2D array to store the maximum value for each possible length of pipe and each possible pipe value
        int[][] memo = new int[cutPrice.length][t + 1];

        // iterate over each possible pipe length-value pair
        for (int i = 0; i < cutPrice.length; i++) {
            int length = cutPrice[i][0];
            int value = cutPrice[i][1];

            // iterate over each possible length of pipe up to the target length
            for (int j = length; j <= t; j++) {
                // calculate the maximum value that can be obtained by cutting and selling the pipe
                memo[i][j] = Math.max(memo[i][j], memo[i][j - length] + value);
            }

            // iterate over previous pipe length-value pairs and update memo accordingly
            for (int k = 0; k < i; k++) {
                int prevLength = cutPrice[k][0];
                int prevValue = cutPrice[k][1];

                // iterate over each possible length of pipe up to the target length
                for (int j = prevLength + length; j <= t; j++) {
                    // calculate the maximum value that can be obtained by cutting and selling the current and previous pipes
                    memo[i][j] = Math.max(memo[i][j], memo[k][j - length - prevLength] + value + prevValue);
                }
            }
        }

        // Return the maximum value that can be obtained by cutting pipes up to the target length
        int maxVal = 0;
        for (int i = 0; i < cutPrice.length; i++) {
            for (int j = 0; j <= t; j++) {
                maxVal = Math.max(maxVal, memo[i][j]);
            }
        }
        return maxVal;
    }
}
