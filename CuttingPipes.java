import java.util.Scanner;

public class CuttingPipes {

    // Calculates the maximum value that can be obtained by cutting pipes of different lengths
    // given a desired pipe length and the corresponding values of each pipe length
    public static int findMaxValue(int[] pipeLengths, int[] pipeValues, int desiredLength) {
        // Array to store the maximum value for each possible length of pipe up to the desired length
        int[] storage = new int[desiredLength + 1];

        // Iterate over each possible length of pipe up to the target length
        for (int i = 0; i <= desiredLength; i++) {
            // Iterate over each pipe length-value pair
            for (int j = 0; j < pipeLengths.length; j++) {
                // If the current pipe length is less than or equal to the remaining length to be cut,
                // calculate the maximum value that can be obtained by cutting and selling the pipe, and update memo[i] accordingly
                if (i >= pipeLengths[j]) {
                    storage[i] = Math.max(storage[i], storage[i - pipeLengths[j]] + pipeValues[j]);
                }
            }
        }

        // Return the maximum value that can be obtained by cutting pipes up to the target length
        return storage[desiredLength];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Read the input user input
            int numPipes = scanner.nextInt();
            int desiredLength = scanner.nextInt();
            if (numPipes == 0 && desiredLength == 0) {
                break;
            }

            // Initialize two 1D arrays to store the lengths and values of the pipes
            int[] pipeLengths = new int[numPipes];
            int[] pipeValues = new int[numPipes];

            // Read the length-value pairs for each pipe and add to the matching arrays
            for (int i = 0; i < numPipes; i++) {
                int length = scanner.nextInt();
                int value = scanner.nextInt();
                pipeLengths[i] = length;
                pipeValues[i] = value;
            }

            // Calculate the maximum value that can be obtained by cutting pipes up to the desired length
            int maxVal = findMaxValue(pipeLengths, pipeValues, desiredLength);

            // Maximum value that can be obtained
            System.out.println(maxVal);
        }

        scanner.close();
    }
}
