package OJ_work2_3;

import java.util.ArrayList;

public class Hand {
    //需要一手牌，即有很多的牌
    ArrayList<Card> cards;
    public Hand() {
        cards = new ArrayList<>();
    }
    public void addCard(Card card){
        this.cards.add(card);
    }

}
