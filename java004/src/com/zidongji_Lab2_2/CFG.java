package com.zidongji_Lab2_2;

import java.util.ArrayList;
import java.util.HashMap;

public class CFG {
    //该类 封装的是 上下文无关文法，G={N,T,P,S}
    //其中 N 为 非终结符号 集合
    //    T 为 终结符号 集合
    //    P 为 生成式 集合
    //    S 为 起始符号
    ArrayList<String> N;
    ArrayList<String> T;
    HashMap<String,ArrayList<ArrayList<String>>> P;
    String S;
    public CFG(){
        this.N=new ArrayList<>();
        this.T=new ArrayList<>();
        this.P=new HashMap<>();
        this.S="S";
    }

    //拷贝构造函数
    public CFG(CFG cfg){
        this.N=new ArrayList<>(cfg.N);
        this.T=new ArrayList<>(cfg.T);
        this.P=new HashMap<>(cfg.P);
        this.S=cfg.S;
    }

}
