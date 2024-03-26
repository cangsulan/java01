package com.zidongji_Lab1;

import java.util.*;

public class app {
    public static void main(String[] args) {
        nfa inNFA = new nfa();
        dfa outDFA = new dfa();
        input(inNFA);
        initiateDFA(inNFA,outDFA);
        convert(inNFA,outDFA);

        System.out.print("状态\t\t\t");
        for (String s : inNFA.Allchars) {
            System.out.print(s+"\t\t\t");
        }
        System.out.println();
        boolean[] visited=new boolean[outDFA.stringList.size()];
        for (boolean b : visited) {
            b=false;
        }
        dfs(outDFA,visited,outDFA.starter);

        System.out.println("-------------------------------------------------");

        //准备工作已经完成，下面开始打印结果：

        for (int i = 0; i < outDFA.transfer.length; i++) {
            if (visited[i]) {
                if (outDFA.stringList.get(i).equals("{}")) {
                    continue;
                }
                if (outDFA.Endstrings.contains(outDFA.stringList.get(i))) {
                    System.out.print("*");
                }else if (outDFA.stringList.get(i).equals(outDFA.starter)){
                    System.out.print("->");
                }
                System.out.print(outDFA.stringList.get(i)+"\t\t\t");
                for (int j = 0; j < outDFA.transfer[i].length; j++) {
                    System.out.print(outDFA.transfer[i][j]+"\t\t\t");
                }
                System.out.println();
            }
        }

    }
    public static void dfs(dfa outDFA,boolean[] visited,String s){
        int index=outDFA.stringList.indexOf(s);
        if (index>=0&&index<outDFA.stringList.size()) {
            visited[index]=true;
            for (String nexts : outDFA.transfer[index]) {
                if(nexts == null){
                    continue;
                }
                int nextIndex=outDFA.stringList.indexOf(nexts);
                if (nextIndex>=0 && nextIndex<outDFA.stringList.size() && !visited[nextIndex]) {
                    dfs(outDFA,visited,nexts);
                }
            }
        }
    }
    public static void convert(nfa inNFA,dfa outDFA){
        for (int i = 0; i < outDFA.transfer.length; i++) {
            for (int j = 0; j < outDFA.transfer[i].length; j++) {
                search(inNFA,outDFA,i,j,inNFA.Allchars[j]);
            }
        }
    }
    public static void search(nfa inNFA,dfa outDFA,int i,int j,String ch){
        LinkedHashSet<String> re=new LinkedHashSet<>();
        String endstring=outDFA.stringList.get(i);
        if (endstring.equals("{}")) {
            outDFA.transfer[i][j]=null;
            return;
        }
        String[] endstrings=endstring.substring(1,endstring.length()-1).split(",");
        for (String s : endstrings) {
            re.addAll(inNFA.transfer[inNFA.stringmap.get(s)][inNFA.charmap.get(ch)]);
        }
        //由于是hashset，所以得到的是乱序，比如：q1 q2 q0
        //下面把result进行拆解排序，再组装好：
        String[] tempString=new String[re.size()];
        re.toArray(tempString);
        Arrays.sort(tempString);
        String result="{";
        for (String s : tempString) {
            if(!s.isEmpty()){
                if(result.equals("{")){
                    result=result+s;
                }else{
                    result=result+","+s;
                }
            }
        }
        result=result+"}";
        if(result.equals("{}")){
            result=null;
        }
        outDFA.transfer[i][j]=result;
    }
    public static void initiateDFA(nfa inNFA,dfa outDFA){
        outDFA.charmap=inNFA.charmap;
        outDFA.starter="{"+inNFA.starter+"}";
        String[] strings=inNFA.stringmap.keySet().toArray(new String[inNFA.stringmap.keySet().size()]);

        getPset(outDFA.stringList,strings);
        for (String s : outDFA.stringList) {
            ArrayList<String> newstring=new ArrayList<>();
            newstring.addAll(List.of(s.substring(1, s.length() - 1).split(",")));
            for (String endstring : inNFA.Endstrings) {
                if(newstring.contains(endstring)){
                    outDFA.Endstrings.add(s);
                }
            }
        }
        outDFA.transfer=new String[outDFA.stringList.size()][outDFA.charmap.size()];
    }
    public static void getPset(ArrayList<String> stringList,String[] strings){
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
        inNFA.transfer=new ArrayList[inNFA.stringmap.entrySet().size()][inNFA.charmap.entrySet().size()];
        for (String s : inNFA.stringmap.keySet()) {
            for (String ch : inNFA.charmap.keySet()) {
                System.out.println("请输入"+s+"沿字符"+ch+"下的转换状态集合，若为空则直接 回车 即可，用空格来分开不同元素：");
                ArrayList<String> arrayList=new ArrayList<>(List.of(sc.nextLine().split(" ")));
                inNFA.transfer[inNFA.stringmap.get(s)][inNFA.charmap.get(ch)]=arrayList;
            }
        }

    }
}
