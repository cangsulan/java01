package leetcode;

import org.junit.jupiter.api.Test;

import java.util.*;

public class test69 {
    private leetcode_202 leetcode202 = new leetcode_202();
    @Test
    public void test1() {
        String s="  hello world  ";
        StringBuilder sb= new StringBuilder();
        ArrayDeque a=new ArrayDeque();
        //PriorityQueue
        PriorityQueue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1]-o1[1];
            }
        });

    }
    @Test
    public void test2() {
        leetcode_1047 leetcode_1047 = new leetcode_1047();
        System.out.println(leetcode_1047.removeDuplicates("abbbabaaa"));
        List<Integer> list = new ArrayList<>();
    }
    @Test
    public void test135() {
        leetcode_135 leetcode_135 = new leetcode_135();
        int[] rating={1,3,4,5,2};
        //System.out.println(leetcode_135.candy(rating));
        LinkedList<int[]> list=new LinkedList<>();
        list.add(new int[]{1,2});
        for (Object o : list.toArray(new int[0][])) {
            System.out.println(o);
        }
    }

    @Test
    public void test0() {
        String s="ababccc";
        List<Integer> result=new ArrayList<>();
        int n=356;
        String n1="234";
        String ns=String.valueOf(n);
        char[] cList=new char[]{'0','2','4'};
        String c=String.valueOf(cList);
        System.out.println(Integer.parseInt(c));

        StringBuilder sb=new StringBuilder();
        HashMap<String,String> map=new HashMap<>();
        HashSet<String> set=new HashSet<>();
        List<String> stringList =new ArrayList<>();
        stringList.sort((a,b)->{
            return a.compareToIgnoreCase(b);
        });
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
    }
}
