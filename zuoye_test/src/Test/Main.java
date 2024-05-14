package Test;

import java.io.*;
public class Main {
    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int step = 1; step < n; step++) {
            int key = arr[step];
            int j = step - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;

            // 输出当前排序结果
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    out.print(" ");
                }
                out.print(arr[i]);
            }
            out.println();
            out.flush();
        }
    }
    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        static int nextInt() throws IOException {
            in.nextToken();
            return (int) in.nval;
        }

        static double nextDouble() throws IOException {
            in.nextToken();
            return in.nval;
        }

        static long nextLong() throws IOException {
            in.nextToken();
            return (long) in.nval;
        }

        static String next() throws IOException {
            in.nextToken();
            return in.sval;
        }

//        public static void main(String[] args) throws IOException {
//            int a = nextInt();
//            out.print("接收："+a);
//            out.flush();  //加这行代码，输出才会显示
//        }
    public static void main(String[] args) throws IOException {

//        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(cin.readLine());
//        String[] s = cin.readLine().split(" ");
//        int[] arr = new int[n];
//        int i=0;
//        for (String string : s) {
//            arr[i]=Integer.parseInt(string);
//            i++;
//        }
        int n=nextInt();
        int[] arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=nextInt();
        }
        insertionSort(arr);
    }
}
