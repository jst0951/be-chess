package chess.pieces;

import chess.Position;

public class Knight extends Piece {

    protected Knight(Color color) {
        super(color, Type.KNIGHT);
    }

    @Override
    protected void setDirectionList() {
        this.directionList = Direction.knightDirection();
    }

    @Override
    public boolean verifyMovePosition(Position source, Position target) {
        return isDirectionAvailableOneSquare(source, target);
    }
}
