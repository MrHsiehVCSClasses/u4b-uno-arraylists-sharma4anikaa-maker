package u6pp;

import java.util.ArrayList;

public class Player {

    // Player name
    private String name;

    // Cards in player's hand
    private ArrayList<Card> hand = new ArrayList<>();

    // Creates player with name
    public Player(String name) {
        this.name = name;
    }

    // Returns player name
    public String getName() {
        return name;
    }

    // Returns player's hand
    public ArrayList<Card> getHand() {
        return hand;
    }
}
