package com.zidongji_Lab1;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class nfa {
    boolean hasEmpty;
    String[] Allstrings;
    String[] Allchars;
    String[] Endstrings;//终结集合
    String starter;//初始状态
    LinkedHashMap<String,Integer> stringmap;//状态集合及编号
    LinkedHashMap<String,Integer> charmap;//字母表及编号

//转移函数：
    ArrayList<String>[][] transfer;
    ArrayList<String>[][] emptyTransfer;
    nfa(){
        this.stringmap=new LinkedHashMap<>();
        this.charmap=new LinkedHashMap<>();
        this.hasEmpty=false;
    }
    public void Autogetmap(String[] Allstrings,String[] Allchars){
        for (int i = 0; i < Allstrings.length; i++) {
            this.stringmap.put(Allstrings[i],i);
        }
        for (int i = 0; i < Allchars.length; i++) {
            this.charmap.put(Allchars[i],i);
        }
        this.Allstrings=Allstrings;
        this.Allchars=Allchars;
        if (this.charmap.containsKey("empty")) {
            this.hasEmpty=true;
        }
    }
}
