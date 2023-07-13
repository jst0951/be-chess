package chess;

public class Position {
    public static final String ERROR_OUT_OF_BOUNDARY = "주어진 좌표가 체스 판의 범위를 벗어납니다.";
    private final int xPos;
    private final int yPos;
    private final int listIdx;

    public Position(String position) throws IllegalArgumentException {
        verifyPosition(position);

        char x = position.charAt(0);
        this.xPos = x - 'a';
        char y = position.charAt(1);
        this.yPos = 8 - Character.getNumericValue(y);

        this.listIdx = yPos * 8 + xPos;
    }

    private void verifyPosition(String position) throws IllegalArgumentException {
        char x = position.charAt(0);
        char y = position.charAt(1);
        if(x < 'a' || x > 'h' || y < '1' || y > '8') {
            throw new IllegalArgumentException(ERROR_OUT_OF_BOUNDARY);
        }
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
