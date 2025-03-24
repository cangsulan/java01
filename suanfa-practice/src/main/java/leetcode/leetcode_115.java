package leetcode;

/**
 * @author 30241
 * @version 1.0
 * @description: TODO
 * @date 2025/3/13 下午10:15
 */
public class leetcode_115 {

    public static int numDistinct(String s, String t) {
        // 动态规划
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        int len1 = s.length();
        int len2 = t.length();
        //为了处理方便，使用len+1的空间，让递推的时候不会卡第一个元素
        int[][] dp =new int[len1+1][len2+1];
        // dp[i][j]表示 t中0到j-1的部分 在 s中0到i-1的部分 的出现个数
        // 所以想要得到最终结果就要 从最开始的每一个小子串 推出，从左到右

        //初始化，默认初始的0就挺合适的

        //开始递推
        for(int j=1;j<len2+1;j++){
            for(int i=1;i<len1+1;i++){
                if(ss[i-1]==tt[j-1]){
                    if(j-1==0){
                        dp[i][j]=dp[i-1][j]+1;
                    }else {
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    }
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        //打印dp数组
        for (int i = 1; i < dp.length; i++) {
            for (int i1 = 1; i1 < dp[i].length; i1++) {
                System.out.print(" " + dp[i][i1]);
            }
            System.out.println();
        }
        return  dp[len1][len2];
    }

    public static void main(String[] args) {
        String s ="rabbbit";
        String t ="rabbit";
        String test1 ="aabb";
        String test2 ="ab";
        int result = numDistinct(test1, test2);
        System.out.println(result);
    }
}
