package doudizhu1;

import java.util.ArrayList;
import java.util.Collections;

public class PokerGame {
    static ArrayList<String> list = new ArrayList<>();

    static {
        //准备牌
        String[] color = {"◇", "♣", "♥", "♠"};
        String[] number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
        //牌盒
        //♥3 ♣3

        for (String c : color) {
            //c表示每一种花色
            for (String n : number) {
                list.add(c + n);
            }
        }
        list.add("小王");
        list.add("大王");

    }

    public PokerGame() {
//        System.out.println(list);
        //洗牌
        Collections.shuffle(list);
//        System.out.println(list);
        //发牌
        ArrayList<String> lord = new ArrayList<>();
        ArrayList<String> player1 = new ArrayList<>();
        ArrayList<String> player2 = new ArrayList<>();
        ArrayList<String> player3 = new ArrayList<>();
        //遍历牌盒来发牌
        for (int i = 0; i < list.size(); i++) {
            String poker = list.get(i);
            if (i <= 2) {
                lord.add(poker);
                continue;
            }
            //给三位玩家发牌
            if (i % 3 == 0) {
                player1.add(poker);
            } else if (i % 3 == 1) {
                player2.add(poker);
            } else {
                player3.add(poker);
            }
        }
        //看牌
        lookPoker("底牌",lord);
        lookPoker("玩家1",player1);
        lookPoker("玩家2",player2);
        lookPoker("玩家3",player3);

    }
    public void lookPoker(String name,ArrayList<String> list){
        System.out.print(name+":");
        for (String poker : list) {
            System.out.print(poker+" ");
        }
        System.out.println();
    }
}
