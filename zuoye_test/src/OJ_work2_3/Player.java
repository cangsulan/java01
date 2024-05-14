package OJ_work2_3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class Player {
    //这个类主要执行游戏规则
    static Scanner sc = new Scanner(System.in);
    Hand hand = new Hand();

    static HashMap<String, Integer> cardMap;

    static {
        cardMap = new HashMap<>();
        cardMap.put("A", 1);
        cardMap.put("2", 2);
        cardMap.put("3", 3);
        cardMap.put("4", 4);
        cardMap.put("5", 5);
        cardMap.put("6", 6);
        cardMap.put("7", 7);
        cardMap.put("8", 8);
        cardMap.put("9", 9);
        cardMap.put("10", 10);
        cardMap.put("J", 11);
        cardMap.put("Q", 12);
        cardMap.put("K", 13);
    }

    //计算手中的牌的总点数
    int countNum() {
        int count = 0;
        int ANum = 0;
        for (Card card : this.hand.cards) {
            if (card.ch.equals("A")) {
                ANum++;
                count += Card.cardMap.get(card.ch);//当遇到 A 时，应该先计算为1点
            } else {
                count += Card.cardMap.get(card.ch);
            }
        }
        for (int i = 0; i < ANum; i++) {
            if (count - 1 + 11 <= 21) {
                //如果将其中 1 张 A 牌 算作 11 点，如果不超过21，则可以将其算作11点，如果超过了21点，则不做改变，仍然算作原来的1点
                count += 10;
            } else {
            }
        }
        return count;
    }


    void GameStart() {
        //读入前2张牌，
        for (int i = 1; i <= 2; i++) {
            Card card = new Card();
            card.css = Card.Css.valueOf(sc.next());
            card.ch = sc.next();
            hand.addCard(card);
        }
        while (true) {
            if (this.countNum() < 17) {
                //接着要下一张牌
                System.out.println("Hit");
                Card card = new Card();
                card.css = Card.Css.valueOf(sc.next());
                card.ch = sc.next();
                hand.addCard(card);
                System.out.print(card.css + " ");
                if (card.ch.equals("A")) {
                    System.out.println("1 11");
                } else {
                    System.out.println(Card.cardMap.get(card.ch));
                }
            } else {
                System.out.println("Stand");
                Comparator<Card> comparator = new Comparator<Card>() {
                    @Override
                    public int compare(Card o1, Card o2) {
                        int n = Player.cardMap.get(o1.ch) - Player.cardMap.get(o2.ch);
                        if (n == 0) {
                            n = o1.css.ordinal() - o2.css.ordinal();
                        }
                        return n;
                    }
                };
                this.hand.cards.sort(comparator);
                System.out.print(this.hand.cards.get(0).css + this.hand.cards.get(0).ch);
                for (int i = 1; i < this.hand.cards.size(); i++) {
                    System.out.print(" " + this.hand.cards.get(i).css + this.hand.cards.get(i).ch);
                }
                System.out.println();
                break;
            }
        }
        //最后输出点数
        int count = this.countNum();
        if (count > 21) {
            System.out.println("Bust");
        } else if (this.hand.cards.size() == 2 && count == 21) {
            System.out.println("Blackjack");
        } else {
            System.out.println(count);
        }
    }
}
