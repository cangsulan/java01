package zidongji_Lab2_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConvertCFG {
    public static CFG delete_epsilon(CFG cfg) {
        CFG newCfg = new CFG();
        ArrayList<String> N0 = new ArrayList<>();
        //先找出 N1 :
        ArrayList<String> tempN = new ArrayList<>();
        for (String s : cfg.N) {
            if (is_A_to_B(cfg, s, "empty")) {
                if(!tempN.contains(s)){
                    tempN.add(s);
                }
            }
        }
        while (!isListSame(tempN, N0)) {
            N0=new ArrayList<>(tempN);
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : cfg.P.entrySet()) {
                for (ArrayList<String> list : entry.getValue()) {
                    boolean in=true;
                    for (String s : list) {
                        if(!tempN.contains(s)){
                            in=false;
                            break;
                        }
                    }
                    if(in){
                        if(!tempN.contains(entry.getKey())){
                            tempN.add(entry.getKey());
                        }
                        break;
                    }
                }
            }
        }
        //然后处理 P1 :
        for (Map.Entry<String, ArrayList<ArrayList<String>>> stringArrayListEntry : cfg.P.entrySet()) {

            for (ArrayList<String> list : stringArrayListEntry.getValue()) {
                //要得到每个 生成式 的右端中 属于 N0 的符号的个数 total_in_N0
                int total_in_N0=0;
                for (String s : list) {
                    if(N0.contains(s)){
                        total_in_N0++;
                    }
                }
                if(total_in_N0==0 && !(list.size()==1 && list.get(0).equals("empty"))){
                    //如果新cfg中没有这个生成式的左端，那么创建一个空的
                    if(!newCfg.P.containsKey(stringArrayListEntry.getKey())) {
                        ArrayList<ArrayList<String>> newRight=new ArrayList<>();
                        newCfg.P.put(stringArrayListEntry.getKey(), newRight);
                    }
                    newCfg.P.get(stringArrayListEntry.getKey()).add(list);
                    continue;
                }
                //得到了total_in_N0，然后 有 2^n 种可能
                for (int num = 0; num < Math.pow(2,total_in_N0); num++) {
                    ArrayList<String> newlist=new ArrayList<>();
                    int num_in_N0=0;
                    for (String s : list) {
                        if(N0.contains(s)){
                            if(((num >> num_in_N0) & 0x1) !=0){
                                newlist.add(s);
                            }
                            num_in_N0++;
                        }else {
                            newlist.add(s);
                        }
                    }
                    if(newlist.size()!=0 && !newlist.get(0).equals("empty")){
                        //如果新cfg中没有这个生成式的左端，那么创建一个空的
                        if(!newCfg.P.containsKey(stringArrayListEntry.getKey())) {
                            ArrayList<ArrayList<String>> newRight=new ArrayList<>();
                            newCfg.P.put(stringArrayListEntry.getKey(), newRight);
                        }
                        //先判断 newlist 是否已经存在
                        if (!newCfg.P.get(stringArrayListEntry.getKey()).contains(newlist)) {
                            newCfg.P.get(stringArrayListEntry.getKey()).add(newlist);
                        }
                    }
                }

            }
        }
        //接下来处理剩下的：
        newCfg.N.addAll(cfg.N);
        newCfg.S=cfg.S;
        newCfg.T.addAll(cfg.T);

        if(N0.contains(cfg.S)){
            ArrayList<ArrayList<String>> newRight=new ArrayList<>();
            ArrayList<String> templist1=new ArrayList<>();
            templist1.add("empty");
            ArrayList<String> templist2=new ArrayList<>();
            templist2.add(cfg.S);
            newRight.add(templist1);
            newRight.add(templist2);
            newCfg.P.put("S1",newRight);
            newCfg.N.add("S1");
            newCfg.S="S1";
        }
        return newCfg;
    }

    public static CFG delete_single(CFG cfg) {
        CFG newCfg = new CFG();
        for (String string : cfg.N) {
            ArrayList<String> N0=new ArrayList<>();
            N0.add(string);
            ArrayList<String> N1=new ArrayList<>(N0);
            for (String s : N0) {
                for (String str2 : cfg.N) {
                    if(is_A_to_B(cfg,s,str2)){
                        if(!N1.contains(str2)){
                            N1.add(str2);
                        }
                    }
                }
            }
            while(!isListSame(N0, N1)) {
                N0=new ArrayList<>(N1);
                for (String s : N0) {
                    for (String str2 : cfg.N) {
                        if(is_A_to_B(cfg,s,str2)){
                            if(!N1.contains(str2)){
                                N1.add(str2);
                            }
                        }
                    }
                }
            }
            //此时 N0 和 N1 完全一致
            //构造 P1
            for (String B : N0) {
                if(cfg.P.get(B)==null){

                }else{
                    for (ArrayList<String> list : cfg.P.get(B)) {
                        if(!newCfg.P.keySet().contains(string)){
                            newCfg.P.put(string,new ArrayList<>());
                        }
                        if(!(list.size()==1 && cfg.N.contains(list.get(0)))){
                            newCfg.P.get(string).add(list);
                        }
                    }
                }
            }
        }
        newCfg.N.addAll(cfg.N);
        newCfg.S=cfg.S;
        newCfg.T.addAll(cfg.T);
        return newCfg;
    }

    public static CFG delete_useless(CFG cfg) {
        CFG newCfg = new CFG();
        //分为两步：
        //1. 找出 有用的非终结符
        ArrayList<String> N0=new ArrayList<>();
        ArrayList<String> N1=new ArrayList<>();
        for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : cfg.P.entrySet()) {
            for (ArrayList<String> list : entry.getValue()) {
                boolean canAdd=true;
                //注意！！！这一段我认为应该这样写：如果存在一个list中都是终结符号，那么这个左端符号就是有用的
                for (String s : list) {
                    if(!cfg.T.contains(s)){
                        canAdd=false;
                        break;
                    }
                }
                if(canAdd){
                    if(!N1.contains(entry.getKey())){
                        N1.add(entry.getKey());
                    }
                    break;
                }
            }
        }
        while(!isListSame(N0, N1)) {
            N0=new ArrayList<>(N1);
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : cfg.P.entrySet()) {
                for (ArrayList<String> list : entry.getValue()) {
                    boolean canAdd=true;
                    //注意！！！这一段我认为应该这样写：如果存在一个list中都是终结符号，那么这个左端符号就是有用的
                    for (String s : list) {
                        if(!N1.contains(s) && !cfg.T.contains(s)){
                            canAdd=false;
                            break;
                        }
                    }
                    if(canAdd){
                        if(!N1.contains(entry.getKey())){
                            N1.add(entry.getKey());
                        }
                        break;
                    }
                }
            }
        }
        newCfg.N.addAll(N0);
        //newCfg.N.add(cfg.S);
        //接下来处理 P1：
        //这里要把所有包含不属于N0的符号的生成式删去
        for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : cfg.P.entrySet()) {
            if(N0.contains(entry.getKey())){
                for (ArrayList<String> list : entry.getValue()) {
                    boolean canAdd=true;
                    for (String s : list) {
                        if(!cfg.T.contains(s) && !N0.contains(s)){
                            canAdd=false;
                            break;
                        }
                    }
                    if(canAdd){
                        if(!newCfg.P.containsKey(entry.getKey())){
                            newCfg.P.put(entry.getKey(),new ArrayList<>());
                        }
                        if(!newCfg.P.get(entry.getKey()).contains(list)){
                            newCfg.P.get(entry.getKey()).add(list);
                        }
                    }
                }
            }
        }
        newCfg.S=cfg.S;
        newCfg.T.addAll(cfg.T);
        //2. 找出有用符号
        //在第1步的基础上
        cfg=new CFG(newCfg);
        newCfg=new CFG();

        N0=new ArrayList<>();
        N0.add(cfg.S);
        // N1为 有用符号集合
        N1=new ArrayList<>(N0);
        for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : cfg.P.entrySet()) {
            if(N0.contains(entry.getKey())){
                for (ArrayList<String> list : entry.getValue()) {
                    for (String s : list) {
                        if(!N1.contains(s)){
                            N1.add(s);
                        }
                    }
                }
            }
        }
        while(!isListSame(N0, N1)) {
            N0=new ArrayList<>(N1);
            for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : cfg.P.entrySet()) {
                if(N0.contains(entry.getKey())){
                    for (ArrayList<String> list : entry.getValue()) {
                        for (String s : list) {
                            if(!N1.contains(s)){
                                N1.add(s);
                            }
                        }
                    }
                }
            }
        }
        //此时 N0 和 N1 相同，已经找到了所有的有用符号
        //把 N 和 T 分别与 N1 取 交集 即可
        for (String s : cfg.N) {
            if(N1.contains(s)){
                newCfg.N.add(s);
            }
        }
        for (String s : cfg.T) {
            if(N1.contains(s)){
                newCfg.T.add(s);
            }
        }
        newCfg.S=cfg.S;
        //最后处理 P1：
        for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : cfg.P.entrySet()) {
            if(N1.contains(entry.getKey())){
                for (ArrayList<String> list : entry.getValue()) {
                    boolean canAdd=true;
                    for (String s : list) {
                        if(!N1.contains(s) && !s.equals("empty")){
                            canAdd=false;
                            break;
                        }
                    }
                    if(canAdd){
                        if(!newCfg.P.containsKey(entry.getKey())){
                            newCfg.P.put(entry.getKey(),new ArrayList<>());
                        }
                        newCfg.P.get(entry.getKey()).add(list);
                    }
                }
            }
        }
        return newCfg;
    }

    public static boolean is_A_to_B(CFG cfg, String a, String b) {
        ArrayList<ArrayList<String>> lists = cfg.P.get(a);
        if (lists == null) {
            return false;
        }
        if (lists.size() == 0) {
            return false;
        }
        for (ArrayList<String> list : lists) {
            if (list.size() == 1 && list.get(0).equals(b)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isListSame(ArrayList<String> a, ArrayList<String> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void printCFG(CFG cfg) {
        //打印 N
        System.out.print("N: {" + cfg.N.get(0));
        for (int i = 1; i < cfg.N.size(); i++) {
            System.out.print(", " + cfg.N.get(i));
        }
        System.out.println("}");
        //打印 T
        System.out.print("T: {" + cfg.T.get(0));
        for (int i = 1; i < cfg.T.size(); i++) {
            System.out.print(", " + cfg.T.get(i));
        }
        System.out.println("}");
        //打印 P
        System.out.println("P: ");
        for (String key : cfg.P.keySet()) {
            System.out.print("\t" + key + " -> ");
            if(cfg.P.get(key).size() == 0){

            }
            for (String s : cfg.P.get(key).get(0)) {
                System.out.print(s);
            }
            for (int i = 1; i < cfg.P.get(key).size(); i++) {
                System.out.print(" | ");
                for (String s : cfg.P.get(key).get(i)) {
                    System.out.print(s);
                }
            }
            System.out.println();
        }
        //打印 S
        System.out.println("S: " + cfg.S);
    }

    public static void getCFG(CFG cfg) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入 非终结符 集合N，以空格分隔：");
        cfg.N.addAll(List.of(sc.nextLine().split(" ")));
        System.out.println("请输入 终结符 集合T，以空格分隔：");
        cfg.T.addAll(List.of(sc.nextLine().split(" ")));
        System.out.println("请输入 起始符 S：");
        cfg.S = sc.nextLine();
        System.out.println("请输入生成式，用空格分开每个符号，第1个符号是生成式的左端，用empty表示空串，输入空行表示该生成式输入结束：");
        for (String str = sc.nextLine(); !str.equals(""); ) {
            String[] chars = str.split(" ");
            String leftchar = chars[0];
            ArrayList<String> rightChars = new ArrayList<>();
            if (!cfg.P.containsKey(leftchar)) {
                ArrayList<ArrayList<String>> rights = new ArrayList<>();
                cfg.P.put(leftchar, rights);
            }
            for (int i = 1; i < chars.length; i++) {
                rightChars.add(chars[i]);
            }
            cfg.P.get(leftchar).add(rightChars);
            System.out.println("请输入生成式，用空格分开每个符号，第1个符号是生成式的左端，用empty表示空串，输入空行表示该生成式输入结束：");
            str = sc.nextLine();
        }
    }
}
