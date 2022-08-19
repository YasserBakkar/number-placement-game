
package q1;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Q1 {
    
// The Main method to implement the program
    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        int XPoint = (int) (Math.random() * 5);
        int YPoint = (int) (Math.random() * 5);
        matrix[XPoint][YPoint] = 1;
        int k = 0;
        while (k < 10) { //We can put any number
            printMatrix(find(matrix));
            k++;
        }
    }
//A method to direct the matrix
    public static int[][] trends() {
        int[][] trend = new int[][]{
            {3, 0}, {0, 3},
            {-3, 0}, {0, -3},
            {2, 2}, {-2, 2},
            {2, -2}, {-2, -2}};
        List<int[]> all = new ArrayList<>();
        for (int i = 0; i < trend.length; i++) {
            all.add(trend[i]);
        }
        Collections.shuffle(all);
        return all.toArray(trend);
    }
//A method to copyNewMatrix.
    public static int[][] copyNewMatrix(int[][] matrix) {
        int[][] newMatrix = new int[5][5];
        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j < newMatrix[i].length; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        return newMatrix;
    }
//A method to shift matrix .
    public static int[][] shift(int[][] matrix, int x, int y, int[] direction, int score) {
        int[][] newMatrix = copyNewMatrix(matrix);
        int newX = x + direction[0];
        int newY = y + direction[1];
        newMatrix[newX][newY] = score + 1;
        return newMatrix;
    }
//A method to create ArrayList.
    public static List<int[][]> nextMoves(int[][] matrix, int x, int y) {
        List<int[][]> next = new ArrayList<int[][]>();
        int[][] directionState = trends();
        for (int i = 0; i < 8; i++) {
            if (controlDirection(matrix, lastXYLocation(matrix)[0], lastXYLocation(matrix)[1], directionState[i])) {
                next.add(shift(matrix, lastXYLocation(matrix)[0], lastXYLocation(matrix)[1], directionState[i],
                        score(matrix)));
            }
        }
        return next;
    }

    public static int[][] find(int[][] matrix) {
        List<int[][]> next = new ArrayList<>();
        next = nextMoves(matrix, lastXYLocation(matrix)[0], lastXYLocation(matrix)[1]);
        for (int[][] b : next) {
            if (score(b) == 25) {
                return b;
            }
        }

        for (int[][] solution : next) {
            int[][] x = find(solution);
            if (score(x) == 25) {
                return x;
            }
        }
        return matrix;
    }

    public static boolean controlDirection(int[][] matrix, int x, int y, int[] direction) {
        int newX = x + direction[0];
        int newY = y + direction[1];
        if (newX < 0 || newY < 0 || newX > 4 || newY > 4) {
            return false;
        }
        if ((newX >= 0 && newY >= 0 && newX <= 4 && newY <= 4)) {
            if (matrix[newX][newY] != 0) {
                return false;
            }
        }
        return true;
    }
//A method to print the max score.
    public static int score(int[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (max <= matrix[i][j]) {
                    max = matrix[i][j];
                }
            }
        }
        return max;
    }
//A method to print the lastXYLocation.
    public static int[] lastXYLocation(int[][] matrix) {
        int[] arr = new int[2];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (score(matrix) == matrix[i][j]) {
                    arr[0] = i;
                    arr[1] = j;
                }
            }
        }
        return arr;
    }
//A method to print the Matrix.
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                String str = "";
                if ((matrix[i][j] - 1) < 10) {
                    str = "0" + (matrix[i][j] - 1);
                } else {
                    str = "" + (matrix[i][j] - 1);
                }
                System.out.print(str + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
