package chess;

import chess.pieces.Piece;
import static chess.Board.ROW_CNT;
import static chess.Board.COL_CNT;

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

        if (isMovable(sourcePosition, targetPosition)) {
            this.pieceList.set(sourcePosition.getListIdx(), Piece.createBlank());
            this.pieceList.set(targetPosition.getListIdx(), piece);
        }
    }

    public boolean isMovable(Position sourcePosition, Position targetPosition) {
        // 원본 좌표가 벗어나지 않는지 계산
        if(sourcePosition.getXPos() < 0) {
            return false;
        }
        if(sourcePosition.getYPos() < 0) {
            return false;
        }
        // 목표 좌표가 벗어나지 않는지 계산
        if(targetPosition.getXPos() >= COL_CNT) {
            return false;
        }
        if(targetPosition.getYPos() >= ROW_CNT) {
            return false;
        }

        // 이동하려는 위치에 같은 편의 기물이 있는지 확인
        if(pieceList.get(targetPosition.getListIdx()).getType() == pieceList.get(sourcePosition.getListIdx()).getType()) {
            return false;
        }

        // 각 기물이 행할 수 있는 움직임인지 판별
        Piece piece = pieceList.get(sourcePosition.getListIdx());
        int xDegree = targetPosition.getXPos() - sourcePosition.getXPos();
        int yDegree = targetPosition.getYPos() - sourcePosition.getYPos();
        if(!piece.isDirectionAvailable(xDegree, yDegree)) {
            return false;
        }

        return true;
    }

}
