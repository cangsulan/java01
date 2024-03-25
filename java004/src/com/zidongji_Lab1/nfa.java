package com.zidongji_Lab1;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class nfa {
    String[] Endstrings;//终结集合
    String starter;//初始状态
    LinkedHashMap<String,Integer> stringmap;//状态集合及编号
    LinkedHashMap<String,Integer> charmap;//字母表及编号

//转移函数：
    ArrayList<String>[][] transfer;
    nfa(){
        this.stringmap=new LinkedHashMap<>();
        this.charmap=new LinkedHashMap<>();

    }
    public void Autogetmap(String[] Allstrings,String[] Allchars){
        for (int i = 0; i < Allstrings.length; i++) {
            this.stringmap.put(Allstrings[i],i);
        }
        for (int i = 0; i < Allchars.length; i++) {
            this.charmap.put(Allchars[i],i);
        }

    }
}
