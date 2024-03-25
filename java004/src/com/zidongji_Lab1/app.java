package com.zidongji_Lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        nfa inNFA = new nfa();
        dfa outDFA = new dfa();
        input(inNFA);
        initiateDFA(inNFA,outDFA);
        convert(inNFA,outDFA);
    }
    public static void convert(nfa inNFA,dfa outDFA){

    }
    public static void initiateDFA(nfa inNFA,dfa outDFA){
        outDFA.charmap=inNFA.charmap;
        outDFA.starter="{"+inNFA.starter+"}";
        String[] strings=inNFA.stringmap.keySet().toArray(new String[inNFA.stringmap.keySet().size()]);
//        int ii=0;
//        outDFA.stringList[ii]=null;
//        ii++;
//        for (int i=0; i <= strings.length; i++) {
//            outDFA.stringList[ii]="{"+strings[i]+"}";
//            ii++;
//        }
        getPset(outDFA.stringList,strings);
//        for (String s : outDFA.stringList) {
//            System.out.println(s);
//        }
        for (String s : outDFA.stringList) {
            ArrayList<String> newstring=new ArrayList<>();
            newstring.addAll(List.of(s.substring(1, s.length() - 1).split(",")));
            for (String endstring : inNFA.Endstrings) {
                if(newstring.contains(endstring)){
                    outDFA.Endstrings.add(s);
                }
            }
        }
//        for (String endstring : outDFA.Endstrings) {
//            System.out.println(endstring);
//        }

    }
    public static void getPset(ArrayList<String> stringList,String[] strings){
        List<List<String>> res = new ArrayList<>();
        int n=strings.length;
        for(int i =0 ; i < 1<<n ; i++){
            String newString="{";
            for(int j = 0 ; j < n ; j ++){
                if(((i >> j)&1) > 0){
                    if(newString.equals("{")){
                        newString=newString+strings[j];
                    }else {
                        newString=newString+","+strings[j];
                    }
                }
            }
            newString=newString+"}";
            stringList.add(newString);
        }
    }
    public static void input(nfa inNFA){
        System.out.println("请输入状态集合，用空格来分开不同元素：");
        Scanner sc=new Scanner(System.in);
        String[] Allstrings=sc.nextLine().split(" ");//切割得到状态的String集合
        System.out.println("请输入字母表，用空格来分开不同元素：");
        String[] Allchars=sc.nextLine().split(" ");
        inNFA.Autogetmap(Allstrings,Allchars);
        System.out.println("请输入初始状态：");
        inNFA.starter=sc.nextLine();
        System.out.println("请输入终止状态集合，用空格来分开不同元素：");
        inNFA.Endstrings=sc.nextLine().split(" ");
        System.out.println("接下来输入状态转换函数，请按照提示输入：");
        //System.out.println("请输入"++"沿字符"++"下的转换状态集合，用空格来分开不同元素：");
        inNFA.transfer=new ArrayList[inNFA.stringmap.entrySet().size()][inNFA.charmap.entrySet().size()];
        for (String s : inNFA.stringmap.keySet()) {
            for (String ch : inNFA.charmap.keySet()) {
                System.out.println("请输入"+s+"沿字符"+ch+"下的转换状态集合，若为空则直接 回车 即可，用空格来分开不同元素：");
                ArrayList<String> arrayList=new ArrayList<>(List.of(sc.nextLine().split(" ")));
//                if(arrayList.size()==1&&arrayList.get(0).equals("null")){
//                    inNFA.transfer[inNFA.stringmap.get(s)][inNFA.charmap.get(ch)]=null;
//                }else {
//                    inNFA.transfer[inNFA.stringmap.get(s)][inNFA.charmap.get(ch)]=arrayList;
//                }
                inNFA.transfer[inNFA.stringmap.get(s)][inNFA.charmap.get(ch)]=arrayList;
            }
        }
        /*for (ArrayList<String>[] Testarray : inNFA.transfer) {
            for (ArrayList<String> list : Testarray) {
                System.out.println(list);
            }
        }*/

    }
}
