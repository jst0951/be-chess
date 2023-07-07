package chess;

import chess.pieces.Piece;

import java.util.List;

public class Game {
    private final List<Piece> pieceList;

    public Game(List<Piece> pieceList) {
        this.pieceList = pieceList;
    }

    public void move(Position position, Piece piece) {
        this.pieceList.set(position.getListIdx(), piece);
    }
    public void move(Position sourcePosition, Position targetPosition) {
        Piece piece = pieceList.get(sourcePosition.getListIdx());

        this.pieceList.set(sourcePosition.getListIdx(), Piece.createBlank());
        this.pieceList.set(targetPosition.getListIdx(), piece);
    }
}
