package zidongji_Lab2_2;

import java.util.*;

public class app {
    public static void main(String[] args) {
        //形式语言自动机的 Lab2 的 第 2 部分：
        // 构造与 下推自动机 等价的 上下文无关文法 ，并利用内容 1 中的算法输出变换后的上下文无关文法
        PDA pda = new PDA();
        getPDA(pda);
        CFG cfg = convertPDAtoCFG(pda);
        ConvertCFG.printCFG(cfg);
        //System.out.println("消去 空串产生式、单产生式 和 无用符号 后：");
//        //要对cfg进行一些处理
//        System.out.println("特殊处理后：");
//        CFG tempCfg=specialConvert(cfg);
//        ConvertCFG.printCFG(tempCfg);
        //1. 消去 空串产生式
        System.out.println("消去 空串产生式 后：");
        CFG cfg1=ConvertCFG.delete_epsilon(cfg);
        ConvertCFG.printCFG(cfg1);
        //2. 消去 单生成式
        System.out.println("消去 单产生式 后：");
        CFG cfg2=ConvertCFG.delete_single(cfg1);
        ConvertCFG.printCFG(cfg2);
        //3. 消去 无用符号 和 不可达符号
        System.out.println("消去 无用符号 后：");
        CFG cfg3=ConvertCFG.delete_useless(cfg1);
        ConvertCFG.printCFG(cfg3);
    }

    public static CFG specialConvert(CFG cfg){
        CFG newCfg=new CFG();
        for (String s : cfg.P.keySet()) {
            if(!newCfg.N.contains(s) && cfg.N.contains(s)){
                newCfg.N.add(s);
            }
        }
        ArrayList<String> deletedList=new ArrayList<>();
        for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : cfg.P.entrySet()) {
            for (ArrayList<String> list : entry.getValue()) {
                boolean canAdd=true;
                for (String s : list) {
                    if(!newCfg.N.contains(s) && !cfg.T.contains(s) && !s.equals("empty")){
                        canAdd=false;
                        if(!deletedList.contains(s)){
                            deletedList.add(entry.getKey());
                        }
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
        newCfg.N.removeAll(deletedList);
        CFG tempCfg=new CFG();
        for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : cfg.P.entrySet()) {
            for (ArrayList<String> list : entry.getValue()) {
                boolean canAdd=true;
                for (String s : list) {
                    if(!newCfg.N.contains(s) && !cfg.T.contains(s) && !s.equals("empty")){
                        canAdd=false;
                        break;
                    }
                }
                if(canAdd){
                    if(!tempCfg.P.containsKey(entry.getKey())){
                        tempCfg.P.put(entry.getKey(),new ArrayList<>());
                    }
                    if(!tempCfg.P.get(entry.getKey()).contains(list)){
                        tempCfg.P.get(entry.getKey()).add(list);
                    }
                }
            }
        }
        newCfg.P=new HashMap<>(tempCfg.P);
        newCfg.T.addAll(cfg.T);
        newCfg.S=cfg.S;
        return newCfg;
    }

    public static CFG convertPDAtoCFG(PDA pda) {
        CFG cfg = new CFG();

        // 设置起始符号
        cfg.S = "S";
        cfg.N.add(cfg.S);
        // 为每个状态和堆栈符号添加产生式
        for (String state1 : pda.states) {
            for (String state2 : pda.states) {
                for (char stackSymbol : pda.stackAlphabet) {
                    String nonTerminal = String.format("[%s,%c,%s]", state1, stackSymbol, state2);
                    if (!cfg.N.contains(nonTerminal)) {
                        cfg.N.add(nonTerminal);
                    }
                }
            }
        }

        // 设置起始符号的产生式
        if (pda.acceptStates.size()==1 && pda.acceptStates.get(0).equals("")) {
            for (String state : pda.states) {
                String startProduction = String.format("[%s,%c,%s]", pda.startState, pda.startStackSymbol, state);
                ArrayList<String> production = new ArrayList<>();
                production.add(startProduction);
                if (!cfg.P.containsKey(cfg.S)) {
                    cfg.P.put(cfg.S, new ArrayList<>());
                }
                cfg.P.get(cfg.S).add(production);
            }
        } else {
            for (String acceptState : pda.acceptStates) {
                String startProduction = String.format("[%s,%c,%s]", pda.startState, pda.startStackSymbol, acceptState);
                ArrayList<String> production = new ArrayList<>();
                production.add(startProduction);
                if (!cfg.P.containsKey(cfg.S)) {
                    cfg.P.put(cfg.S, new ArrayList<>());
                }
                cfg.P.get(cfg.S).add(production);
            }
        }

        // 处理转换规则
        for (String fromState : pda.transitions.keySet()) {
            for (PDA.Transition transition : pda.transitions.get(fromState)) {

                if (transition.newStack.length() == 1) {
                    String nonTerminal = String.format("[%s,%c,%s]", transition.fromState, transition.stackTop, transition.toState);
                    ArrayList<String> production = new ArrayList<>();
                    if(transition.input=='\0'){
                        production.add("empty");
                    }else{
                        production.add(String.valueOf(transition.input));
                    }
                    if(transition.newStack.equals("")){

                    }else{
                        production.add(String.format("[%s,%c,%s]", transition.toState, transition.newStack.charAt(0), transition.toState));
                    }
                    if (!cfg.P.containsKey(nonTerminal)) {
                        cfg.P.put(nonTerminal, new ArrayList<>());
                    }
                    cfg.P.get(nonTerminal).add(production);
                } else if (transition.newStack.length() == 2) {
                    for (String tostate : pda.states) {
                        for (String rightState : pda.states) {
                            String nonTerminal = String.format("[%s,%c,%s]", transition.fromState, transition.stackTop, tostate);
                            ArrayList<String> production = new ArrayList<>();
                            production.add(String.valueOf(transition.input));
                            production.add(String.format("[%s,%c,%s]", transition.toState, transition.newStack.charAt(0), rightState));
                            production.add(String.format("[%s,%c,%s]", rightState, transition.newStack.charAt(1), tostate));
                            if (!cfg.P.containsKey(nonTerminal)) {
                                cfg.P.put(nonTerminal, new ArrayList<>());
                            }
                            cfg.P.get(nonTerminal).add(production);
                        }
                    }
                } else if (transition.newStack.length()==0) {
                    String nonTerminal = String.format("[%s,%c,%s]", transition.fromState, transition.stackTop, transition.toState);
                    ArrayList<String> production = new ArrayList<>();
                    if(transition.input=='\0'){
                        production.add("empty");
                    }else{
                        production.add(String.valueOf(transition.input));
                    }
                    if(transition.newStack.equals("")){

                    }else{
                        production.add(String.format("[%s,%c,%s]", transition.toState, transition.newStack.charAt(0), transition.toState));
                    }
                    if (!cfg.P.containsKey(nonTerminal)) {
                        cfg.P.put(nonTerminal, new ArrayList<>());
                    }
                    cfg.P.get(nonTerminal).add(production);
                }
                if (!cfg.T.contains(String.valueOf(transition.input)) && transition.input != '\0') {
                    cfg.T.add(String.valueOf(transition.input));
                }

            }
        }
        return cfg;
    }

    public static void getPDA(PDA pda) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入 状态集合，以空格分隔：");
        pda.states.addAll(List.of(sc.nextLine().split(" ")));
        System.out.println("请输入 字母表 集合，以空格分隔：");
        for (String s : sc.nextLine().split(" ")) {
            pda.inputAlphabet.add(s.charAt(0));
        }
        System.out.println("请输入堆栈的字母表 集合，以空格分隔：");
        for (String s : sc.nextLine().split(" ")) {
            pda.stackAlphabet.add(s.charAt(0));
        }
        System.out.println("请输入 起始状态 ：");
        pda.startState=sc.nextLine();
        System.out.println("请输入 堆栈的起始符号：");
        pda.startStackSymbol=sc.nextLine().charAt(0);
        System.out.println("请输入 终止状态的集合，以空格分隔：");
        pda.acceptStates.addAll(List.of(sc.nextLine().split(" ")));
        System.out.println("请输入转换函数，输入左端的3个参数，和右端的2个参数，用空格分隔，空串则输入empty，输入空行表示输入结束：");
        for(String str=sc.nextLine();!str.equals("");) {
            String[] inputList=str.split(" ");
            if(inputList[1].equals("empty")) {
                if(inputList[4].equals("empty")) {
                    pda.addTransition(inputList[0],'\0',inputList[2].charAt(0),inputList[3],"");
                }else{
                    pda.addTransition(inputList[0],'\0',inputList[2].charAt(0),inputList[3],inputList[4]);
                }
            }else{
                if(inputList[4].equals("empty")) {
                    pda.addTransition(inputList[0],inputList[1].charAt(0),inputList[2].charAt(0),inputList[3],"");
                }else{
                    pda.addTransition(inputList[0],inputList[1].charAt(0),inputList[2].charAt(0),inputList[3],inputList[4]);
                }
            }
            System.out.println("请输入转换函数，输入左端的3个参数，和右端的2个参数，用空格分隔，空串则输入empty，输入空行表示输入结束：");
            str=sc.nextLine();
        }
    }
}
