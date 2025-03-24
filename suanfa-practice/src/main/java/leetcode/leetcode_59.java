package leetcode;

public class leetcode_59 {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        //遵循 左闭右开 的原则 来填充矩阵
        int i=0,j=0;
        //不记录 边界，而是记录每一圈的起点
        int starti=0;
        int startj=0;
        int offset=1;//需要拐弯的地方 和边界的距离
        int loop=1;//记录 当前的圈数
        int count=1;//需要填写的数字
        for(;loop<=n/2;){
            //上边界
            for(j=startj;j<n-offset;j++){
                matrix[i][j]=count++;
            }
            //右边界
            for(i=starti;i<n-offset;i++){
                matrix[i][j]=count++;
            }
            //下边界
            for(;j>startj;j--){
                matrix[i][j]=count++;
            }
            //左边界
            for(;i>starti;i--){
                matrix[i][j]=count++;
            }
            //最后 i多减了一下，把 i再加回去
            i++;
            //进入下一圈
            starti++;
            startj++;
            loop++;
            offset++;
        }
        //若n为奇数，则中间一定会剩下单独的一块没有填数字，单独来处理
        if(n%2!=0){
            matrix[starti][startj]=count;
        }
        return matrix;
    }
}
