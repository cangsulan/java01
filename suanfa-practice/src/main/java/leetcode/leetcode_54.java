package leetcode;

import java.util.ArrayList;
import java.util.List;

public class leetcode_54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int up = 0;
        int down = m - 1;
        int left = 0;
        int right = n - 1;
        List<Integer> list = new ArrayList<>();
        int size = 0;
        for (; true; ) {
            for (int i = left; i <= right; i++) {
                list.add(matrix[up][i]);
                size++;
            }
            up++;
            if (size >= m * n) {
                break;
            }
            for (int i = up; i <= down; i++) {
                list.add(matrix[i][right]);
                size++;
            }
            right--;
            if (size >= m * n) {
                break;
            }
            for (int i = right; i >= left; i--) {
                list.add(matrix[down][i]);
                size++;
            }
            down--;
            if (size >= m * n) {
                break;
            }
            for (int i = down; i >= up; i--) {
                list.add(matrix[i][left]);
                size++;
            }
            left++;
            if (size >= m * n) {
                break;
            }
        }
        return list;
    }
}
