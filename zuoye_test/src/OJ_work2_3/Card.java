package OJ_work2_3;

import java.util.HashMap;

public class Card{
    static HashMap<String,Integer> cardMap;
    static {
        cardMap = new HashMap<>();
        cardMap.put("A",1);
        cardMap.put("2",2);
        cardMap.put("3",3);
        cardMap.put("4",4);
        cardMap.put("5",5);
        cardMap.put("6",6);
        cardMap.put("7",7);
        cardMap.put("8",8);
        cardMap.put("9",9);
        cardMap.put("10",10);
        cardMap.put("J",10);
        cardMap.put("Q",10);
        cardMap.put("K",10);
    }
    //单张牌，需要 花色 牌面
    public enum Css{
        Spade, Heart, Diamond, Club
    }
    Css css;//花色
    String ch;//牌面

    Card(String css,String ch){
        this.ch=ch;
        this.css=Css.valueOf(css);
    }
    Card(){}
}
