package doudizhu3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class PokerGame {
    static HashMap<Integer, String> hm = new HashMap<>();
    static ArrayList<Integer> list = new ArrayList<>();

    static {
        //准备牌
        String[] color = {"◇", "♣", "♥", "♠"};
        String[] number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
        //牌盒
        //♥3 ♣3
        int serialNumber = 1;//牌的序号
        for (String n : number) {
            for (String c : color) {
                hm.put(serialNumber, c + n);
                list.add(serialNumber);
                serialNumber++;
            }
        }
        hm.put(serialNumber, "小王");
        list.add(serialNumber);
        serialNumber++;
        hm.put(serialNumber, "大王");
        list.add(serialNumber);
        /*System.out.println(hm);*/
    }

    public PokerGame() {
        Collections.shuffle(list);
        TreeSet<Integer> lord = new TreeSet<>();
        TreeSet<Integer> player1 = new TreeSet<>();
        TreeSet<Integer> player2 = new TreeSet<>();
        TreeSet<Integer> player3 = new TreeSet<>();
        for (int i = 0; i < list.size(); i++) {
            int serialNumber = list.get(i);
            if (i <= 2) {
                lord.add(serialNumber);
                continue;
            }
            if (i % 3 == 0) {
                player1.add(serialNumber);
            } else if (i % 3 == 1) {
                player2.add(serialNumber);
            }else {
                player3.add(serialNumber);
            }
        }
        System.out.println(lord);
        System.out.println(player1);
        System.out.println(player2);
        System.out.println(player3);
        //看牌
        lookPoker("底牌",lord);
        lookPoker("玩家1",player1);
        lookPoker("玩家2",player2);
        lookPoker("玩家3",player3);
    }
    public void lookPoker(String name,TreeSet<Integer> ts){
        System.out.print(name+": ");
        for (Integer t : ts) {
            String poker=hm.get(t);
            System.out.print(poker+" ");
        }
        System.out.println();
    }
}
