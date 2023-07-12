package chess.pieces;

import chess.Position;

public class Pawn extends Piece {

    protected Pawn(Color color) {
        super(color, Type.PAWN);
    }

    @Override
    protected void setDirectionList() {
        if(this.getColor() == Color.WHITE) {
            this.directionList = Direction.whitePawnDirection();
        }
        else{
            this.directionList = Direction.blackPawnDirection();
        }
    }

    @Override
    public boolean verifyMovePosition(Position source, Position target) {
        return isDirectionAvailableOneSquare(source, target);
    }
}
