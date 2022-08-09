package utils;

/**
 * Collector of symbols that are used to draw a grid in the console and sign players moves.
 */

public enum Symbols {

    X ("X"),
    O ("O"),
    EMPTY ("_"),
    HORIZONTAL_BORDER ("_"),
    DOUBLE_HORIZONTAL_BORDER ("__"),
    VERTICAL_BORDER ("|");

    private final String symbol;

    Symbols(String symbol) {
        this.symbol = symbol;
    }

    public String getValue() {
        return symbol;
    }
}
