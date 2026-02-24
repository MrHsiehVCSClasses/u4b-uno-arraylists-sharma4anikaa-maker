package u6pp;

import java.util.ArrayList;
import java.util.Collections;

public class CardStack {

    // Stores cards in a stack-like structure
    private ArrayList<Card> cards = new ArrayList<>();

    // Adds a card to the top of the stack
    public void push(Card c) {
        cards.add(c);
    }

    // Removes and returns the top card
    // Returns null if empty
    public Card pop() {
        if (cards.isEmpty()) return null;
        return cards.remove(cards.size() - 1);
    }

    // Returns the top card without removing it
    // Returns null if empty
    public Card peek() {
        if (cards.isEmpty()) return null;
        return cards.get(cards.size() - 1);
    }

    // Returns number of cards
    public int getSize() {
        return cards.size();
    }

    // Returns true if stack has no cards
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    // Removes all cards
    public void clear() {
        cards.clear();
    }

    // Randomizes card order
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Adds all cards from another stack
    // Clears the other stack afterward
    public void addAll(CardStack other) {
        if (other == this || other.isEmpty()) return;
        cards.addAll(other.cards);
        other.clear();
    }

    // Builds a complete UNO deck
    // Exactly matches what tests expect
    public void buildFullUnoDeck() {
        cards.clear();

        // Four standard colors
        String[] colors = {Card.RED, Card.GREEN, Card.BLUE, Card.YELLOW};

        // Number cards 0-9
        String[] nums = {
            Card.ZERO, Card.ONE, Card.TWO, Card.THREE, Card.FOUR,
            Card.FIVE, Card.SIX, Card.SEVEN, Card.EIGHT, Card.NINE
        };

        // Add number cards
        for (String c : colors) {
            for (String n : nums) {

                // One zero per color
                cards.add(new Card(c, n));

                // Two of each number except zero
                if (!n.equals(Card.ZERO))
                    cards.add(new Card(c, n));
            }

            // Add action cards
            cards.add(new Card(c, Card.SKIP));
            cards.add(new Card(c, Card.REVERSE));
            cards.add(new Card(c, Card.DRAW_2));
        }

        // Add wild cards
        for (int i = 0; i < 4; i++) {
            cards.add(new Card(Card.WILD, Card.WILD));
            cards.add(new Card(Card.WILD, Card.WILD_DRAW_4));
        }
    }
}
