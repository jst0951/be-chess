package chess.pieces;

import chess.Position;

public class Bishop extends Piece {

    protected Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    @Override
    public void setDirectionList() {
        this.directionList = Direction.diagonalDirection();
    }

    @Override
    public boolean verifyMovePosition(Position source, Position target) {
        return isDirectionAvailableMultiSquare(source, target);
    }
}
