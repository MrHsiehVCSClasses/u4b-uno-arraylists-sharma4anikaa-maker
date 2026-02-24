package u6pp;

import java.util.ArrayList;

public class Uno {

    // List of players
    private ArrayList<Player> players;

    // Deck of cards to draw from
    private CardStack deck;

    // Discard pile
    private CardStack discard;

    // Index of current player
    private int currentPlayerIndex;

    // Direction of play
    private boolean reversed;

    // Full constructor (used by tests)
    public Uno(ArrayList<Player> players, CardStack deck, CardStack discard,
               int currentPlayerIndex, boolean reversed) {

        this.players = players;
        this.deck = deck;
        this.discard = discard;
        this.currentPlayerIndex = currentPlayerIndex;
        this.reversed = reversed;
    }

    // Game setup constructor
    public Uno(int numPlayers) {

        // Create players
        players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++)
            players.add(new Player("Player " + i));

        // Build and shuffle deck
        deck = new CardStack();
        deck.buildFullUnoDeck();
        deck.shuffle();

        // Create discard pile
        discard = new CardStack();

        // Deal 7 cards to each player
        for (int i = 0; i < 7; i++)
            for (Player p : players)
                p.getHand().add(deck.pop());

        // First discard card
        discard.push(deck.pop());

        // Start at player 0
        currentPlayerIndex = 0;

        // Normal direction
        reversed = false;
    }

    // Returns all players
    public ArrayList<Player> getPlayers() {
        return players;
    }

    // Returns current player
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    // Returns next player based on direction
    public Player getNextPlayer() {
        int dir = reversed ? -1 : 1;
        return players.get((currentPlayerIndex + dir + players.size()) % players.size());
    }

    // Returns top discard card
    public Card getTopDiscard() {
        return discard.peek();
    }

    // Returns winner if someone has no cards
    public Player getWinner() {
        for (Player p : players)
            if (p.getHand().isEmpty()) return p;
        return null;
    }

    // Attempts to play a card
    public boolean playCard(Card card, String chosenColor) {

        Player cur = getCurrentPlayer();

        // Draw card if null
        if (card == null) {

            // Shuffle if deck almost empty
            if (deck.getSize() <= 1) deck.shuffle();

            // Draw card
            cur.getHand().add(deck.pop());

            // Move turn
            advance(1);
            return true;
        }

        // Card must be in player's hand
        if (!cur.getHand().contains(card)) return false;

        // Card must be playable
        if (!card.canPlayOn(getTopDiscard())) return false;

        // Set wild color
        if (card.getColor().equals(Card.WILD))
            card.trySetColor(chosenColor);

        // Remove card from hand
        cur.getHand().remove(card);

        // Add to discard pile
        discard.push(card);

        int skip = 1;

        // Reverse direction
        if (card.getValue().equals(Card.REVERSE))
            reversed = !reversed;

        // Skip next player
        if (card.getValue().equals(Card.SKIP))
            skip = 2;

        // Draw 2 card effect
        if (card.getValue().equals(Card.DRAW_2)) {
            draw(getNextPlayer(), 2);
            skip = 2;
        }

        // Draw 4 card effect
        if (card.getValue().equals(Card.WILD_DRAW_4)) {
            draw(getNextPlayer(), 4);
            skip = 2;
        }

        // Move turn forward
        advance(skip);
        return true;
    }

    // Makes a player draw n cards
    private void draw(Player p, int n) {

        for (int i = 0; i < n; i++) {

            // Shuffle if needed
            if (deck.getSize() <= 1) deck.shuffle();

            // Draw card
            p.getHand().add(deck.pop());
        }
    }

    // Moves turn forward n steps
    private void advance(int n) {

        int dir = reversed ? -1 : 1;

        currentPlayerIndex =
            (currentPlayerIndex + dir * n + players.size()) % players.size();
    }
}
