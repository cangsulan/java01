package zidongji_Lab2_2;

import java.util.ArrayList;
import java.util.HashMap;

public class PDA {
    // 该类封装的是下推自动机，包括状态集合、输入字母表、堆栈字母表、起始状态、起始堆栈符号、接受状态集合、以及状态转换函数
    ArrayList<String> states;              //状态集合
    ArrayList<Character> inputAlphabet;    //输入字母表
    ArrayList<Character> stackAlphabet;    //堆栈的字母表
    String startState;                     //起始状态
    char startStackSymbol;                 //堆栈的起始符号
    ArrayList<String> acceptStates;        //接受状态的集合
    HashMap<String, ArrayList<Transition>> transitions;  //状态转换表

    public PDA() {
        this.states = new ArrayList<>();
        this.inputAlphabet = new ArrayList<>();
        this.stackAlphabet = new ArrayList<>();
        this.startState = "";
        this.startStackSymbol = '\0';
        this.acceptStates = new ArrayList<>();
        this.transitions = new HashMap<>();
    }

    public void addTransition(String fromState, char input, char stackTop, String toState, String newStack) {
        transitions.computeIfAbsent(fromState, k -> new ArrayList<>())
                .add(new Transition(fromState, input, stackTop, toState, newStack));
    }
    static class Transition {
        String fromState; //待转换 的状态
        char input;         //输入的字母
        char stackTop;      //栈顶字母
        String toState;     //转换后的状态
        String newStack;    //新的栈顶

        Transition(String fromState, char input, char stackTop, String toState, String newStack) {
            this.fromState = fromState;
            this.input = input;
            this.stackTop = stackTop;
            this.toState = toState;
            this.newStack = newStack;
        }
    }
}
