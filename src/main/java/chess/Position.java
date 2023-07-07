package chess;

public class Position {
    private final int xPos;
    private final int yPos;
    private final int listIdx;

    public Position(String position) {
        char x = position.charAt(0);
        this.xPos = x - 'a';
        char y = position.charAt(1);
        this.yPos = 8 - Character.getNumericValue(y);

        this.listIdx = yPos * 8 + xPos;
    }

    public int getXPos() {
        return this.xPos;
    }

    public int getYPos() {
        return this.yPos;
    }

    public int getListIdx() {
        return this.listIdx;
    }
}
