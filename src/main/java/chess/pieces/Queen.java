package chess.pieces;

import chess.Position;

public class Queen extends Piece {

    protected Queen(Color color) {
        super(color, Type.QUEEN);
        setDirectionList();
    };

    @Override
    protected void setDirectionList() {
        this.directionList = Direction.everyDirection();
    }

    @Override
    public boolean verifyMovePosition(Position source, Position target) {
        return isDirectionAvailableMultiSquare(source, target);
    }
}
