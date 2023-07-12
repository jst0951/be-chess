package chess.pieces;

import chess.Position;

public class Blank extends Piece {

    protected Blank() {
        super(Color.NOCOLOR, Type.NO_PIECE);
    }

    @Override
    protected void setDirectionList() {
        this.directionList = null;
    }

    @Override
    public boolean verifyMovePosition(Position source, Position target) {
        return false;
    }
}
