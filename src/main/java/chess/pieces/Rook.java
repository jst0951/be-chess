package chess.pieces;

import chess.Position;

public class Rook extends Piece {

    protected Rook(Color color) {
        super(color, Type.ROOK);
    }

    @Override
    protected void setDirectionList() {
        this.directionList = Direction.linearDirection();
    }

    @Override
    public boolean verifyMovePosition(Position source, Position target) {
        return isDirectionAvailableMultiSquare(source, target);
    }
}
