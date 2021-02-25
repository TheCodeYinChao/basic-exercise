import org.junit.Test;

/**
 * dsc: Solution
 * date: 2021/2/25 17:20
 * author: zyc
 */
public class Solution {
    
    private int[][] matrix ={{1,2,3},{4,5,6}};

    @Test
    public void init(){

        transpose(matrix);
    }


    public int[][] transpose(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        if(row==col){
            for (int i = 0; i < col; i++) {
                for (int j = i; j < row; j++) {
                        int i1 = matrix[i][j];
                        int i2 = matrix[j][i];
                        matrix[i][j] = i2;
                        matrix[j][i] = i1;
                }
            }
            return matrix;
        }else {
            if(col > row){
                int[][] matrix1 = new int[col][row];
                for (int i = 0; i < col; i++) {
                    for (int j = 0; j < row; j++) {
                        matrix1[i][j] = matrix[j][i];
                    }
                }

                return matrix1;
            }else {
                int[][] matrix1 = new int[row][col];
                for (int i = 0; i < col; i++) {
                    for (int j = 0; j < row; j++) {
                        matrix1[i][j] = matrix[j][i];
                    }
                }
                return matrix1;
            }
        }

    }
}
