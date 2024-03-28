package com.zidongji_Lab1;

import java.util.*;

public class app {
    public static void main(String[] args) {
        nfa inNFA = new nfa();
        dfa outDFA = new dfa();
        input(inNFA);
        initiateDFA(inNFA,outDFA);
        if (inNFA.hasEmpty) {
            getEclosure(inNFA);
            inNFA.transfer= inNFA.emptyTransfer;
        }
        convert(inNFA,outDFA);

        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("转换完成后的dfa如下表所示；");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.printf("%12s","状态");
        for (String s : inNFA.Allchars) {
            if(s.equals("empty")){
                continue;
            }
            System.out.printf("\t%16s\t",s);
        }
        System.out.println();
        boolean[] visited=new boolean[outDFA.stringList.size()];
        for (boolean b : visited) {
            b=false;
        }
        dfs(inNFA,outDFA,visited,outDFA.starter);

        System.out.println("--------------------------------------------------------------------------------------------------------");
        //准备工作已经完成，下面开始打印结果：
        for (int i = 0; i < outDFA.transfer.length; i++) {
            if (visited[i]) {
                if (outDFA.stringList.get(i).equals("{}")) {
                    continue;
                }
                if (outDFA.Endstrings.contains(outDFA.stringList.get(i))) {
                    System.out.printf("%16s","*"+outDFA.stringList.get(i));
                }else if (outDFA.stringList.get(i).equals(outDFA.starter)){
                    System.out.printf("%16s","->"+outDFA.stringList.get(i));
                }else {
                    System.out.printf("%16s",outDFA.stringList.get(i));
                }
                for (int j = 0; j < outDFA.transfer[i].length; j++) {
                    if(inNFA.hasEmpty && outDFA.charmap.get("empty")==j){
                        continue;
                    }
                    System.out.printf("\t\t%8s\t\t",outDFA.transfer[i][j]);
                }
                System.out.println();
            }
        }

        System.out.println("--------------------------------------------------------------------------------------------------------");        System.out.println("输入任意内容，即可退出");
        Scanner in=new Scanner(System.in);
        in.next();
    }
    public static void getEclosure(nfa inNFA){
        //有空转换，要进行 空闭包 处理
        int emptyIndex=inNFA.charmap.get("empty");
        for (int i = 0; i < inNFA.transfer.length; i++) {
            String leftString =inNFA.Allstrings[i];
            ArrayList<String> aim=new ArrayList<>();
            aim.add(inNFA.Allstrings[i]);
            emptySearch(inNFA,emptyIndex,aim);
            for (int j = 0; j < inNFA.transfer[i].length; j++) {
                String ch=inNFA.Allchars[j];
                if (ch.equals("empty")) {
                    continue;
                }
                HashSet<String> tempSet= new HashSet<>();
                for (String s : aim) {
                    if(s==null || s.equals("")){
                        continue;
                    }
                    tempSet.addAll(inNFA.transfer[inNFA.stringmap.get(s)][j]);
                }
                ArrayList<String> newArraylist=new ArrayList<>();
                ArrayList<String> newAim=new ArrayList<>();
                newAim.addAll(tempSet);
                emptySearch(inNFA,emptyIndex,newAim);
                inNFA.emptyTransfer[i][j].clear();
                inNFA.emptyTransfer[i][j].addAll(newAim);
            }
        }

    }
    public static void emptySearch(nfa inNFA,int emptyIndex,ArrayList<String> aim){
        HashSet<String> tempSet=new HashSet<>();
        tempSet.addAll(aim);
        while (true){
            for (String s : aim) {
                if(s==null||s.equals("")){
                    continue;
                }
                tempSet.addAll(inNFA.transfer[inNFA.stringmap.get(s)][emptyIndex]);
            }
            if(tempSet.size()==aim.size()){
                boolean isEqual=true;
                for (String string : aim) {
                    if(!tempSet.contains(string)){
                        isEqual=false;
                    }
                }
                if (isEqual) {
                    return;
                }
            }
            aim.clear();
            aim.addAll(tempSet);
        }
    }

    public static void dfs(nfa inNFA,dfa outDFA,boolean[] visited,String s){
        int index=outDFA.stringList.indexOf(s);
        if (index>=0&&index<outDFA.stringList.size()) {
            visited[index]=true;
            for (int i = 0; i < outDFA.transfer[index].length; i++) {
                if(outDFA.transfer[index][i] == null){
                    continue;
                }
                if(inNFA.hasEmpty && outDFA.charmap.get("empty")==i){
                    continue;
                }
                int nextIndex=outDFA.stringList.indexOf(outDFA.transfer[index][i]);
                if (nextIndex>=0 && nextIndex<outDFA.stringList.size() && !visited[nextIndex]) {
                    dfs(inNFA,outDFA,visited,outDFA.transfer[index][i]);
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
        System.out.println("请输入字母表，用空格来分开不同元素：(若有空转换则额外输入empty)");
        String[] Allchars=sc.nextLine().split(" ");
        inNFA.Autogetmap(Allstrings,Allchars);
        System.out.println("请输入初始状态：");
        inNFA.starter=sc.nextLine();
        System.out.println("请输入终止状态集合，用空格来分开不同元素：");
        inNFA.Endstrings=sc.nextLine().split(" ");
        System.out.println("接下来输入状态转换函数，请按照提示输入：");
        inNFA.transfer=new ArrayList[inNFA.stringmap.entrySet().size()][inNFA.charmap.entrySet().size()];
        if(inNFA.hasEmpty){
            inNFA.emptyTransfer=new ArrayList[inNFA.stringmap.entrySet().size()][inNFA.charmap.entrySet().size()];
        }
        for (String s : inNFA.stringmap.keySet()) {
            for (String ch : inNFA.charmap.keySet()) {
                System.out.println("请输入"+s+"沿字符"+ch+"下的转换状态集合，若为空则直接 回车 即可，用空格来分开不同元素：");
                ArrayList<String> arrayList=new ArrayList<>(List.of(sc.nextLine().split(" ")));
                inNFA.transfer[inNFA.stringmap.get(s)][inNFA.charmap.get(ch)]=arrayList;
                if(inNFA.hasEmpty){
                    inNFA.emptyTransfer[inNFA.stringmap.get(s)][inNFA.charmap.get(ch)]=new ArrayList<>(arrayList);
                }
            }
        }

    }
}
