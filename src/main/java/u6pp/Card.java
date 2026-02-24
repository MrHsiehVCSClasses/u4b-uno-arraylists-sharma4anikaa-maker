package u6pp;

public class Card {

    // ===== Card Colors =====
    // Standard UNO colors plus WILD
    public static final String RED = "RED";
    public static final String GREEN = "GREEN";
    public static final String BLUE = "BLUE";
    public static final String YELLOW = "YELLOW";
    public static final String WILD = "WILD";

    // ===== Number Values =====
    // Number cards 0–9
    public static final String ZERO = "0";
    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String THREE = "3";
    public static final String FOUR = "4";
    public static final String FIVE = "5";
    public static final String SIX = "6";
    public static final String SEVEN = "7";
    public static final String EIGHT = "8";
    public static final String NINE = "9";

    // ===== Special Action Cards =====
    public static final String SKIP = "SKIP";
    public static final String REVERSE = "REVERSE";
    public static final String DRAW_2 = "DRAW_2";
    public static final String WILD_DRAW_4 = "WILD_DRAW_4";

    // Stores card color and value
    private String color;
    private String value;

    // Constructor creates a card with a color and value
    public Card(String color, String value) {
        this.color = color;
        this.value = value;
    }

    // Returns the card color
    public String getColor() {
        return color;
    }

    // Returns the card value
    public String getValue() {
        return value;
    }

    // Determines if this card can be played on another card
    // Rules:
    // - Wild cards can always be played
    // - Matching color OR value is allowed
    // - If no card exists (start of game), allow play
    public boolean canPlayOn(Card other) {
        if (other == null) return true;

        // wild always playable
        if (this.color.equals(WILD)) return true;

        // match color OR value
        return this.color.equals(other.color)
            || this.value.equals(other.value);
    }

    // Changes the color of a wild card
    // Only works if:
    // - Card is WILD
    // - New color is RED/GREEN/BLUE/YELLOW
    // Returns true if successful
    public boolean trySetColor(String newColor) {
        if (!color.equals(WILD)) return false;
        if (newColor == null) return false;

        // Check if color is valid
        if (!newColor.equals(RED) &&
            !newColor.equals(GREEN) &&
            !newColor.equals(BLUE) &&
            !newColor.equals(YELLOW)) {
            return false;
        }

        // Set new color
        color = newColor;
        return true;
    }
}
