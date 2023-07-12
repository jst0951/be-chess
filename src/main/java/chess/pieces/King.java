package chess.pieces;

import chess.Position;

public class King extends Piece {

    protected King(Color color) {
        super(color, Type.KING);
    }
    @Override
    protected void setDirectionList() {
        this.directionList = Direction.everyDirection();
    }

    @Override
    public boolean verifyMovePosition(Position source, Position target) {
        return isDirectionAvailableOneSquare(source, target);
    }
}
