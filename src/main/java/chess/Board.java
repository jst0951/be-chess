package chess;

import chess.pieces.Piece;

import java.util.*;

import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;

public class Board {
    public static final int ROW_CNT = 8;
    public static final int COL_CNT = 8;
    public static final double PAWN_SAMEROW_SCORE = 0.5;

    private final List<Piece> pieceList;

    public Board() {
        this.pieceList = new ArrayList<>();
    }

    public void initialize() {
        int i;
        // 흑색 기물(폰 제외) 셋팅
        addPiece(Piece.createBlackRook());
        addPiece(Piece.createBlackKnight());
        addPiece(Piece.createBlackBishop());
        addPiece(Piece.createBlackQueen());
        addPiece(Piece.createBlackKing());
        addPiece(Piece.createBlackBishop());
        addPiece(Piece.createBlackKnight());
        addPiece(Piece.createBlackRook());
        // 검은색 폰 셋팅
        for (i = 0; i < COL_CNT; i++) {
            addPiece(Piece.createBlackPawn());
        }
        // 빈 칸 4줄 셋팅
        addBlankRow();
        addBlankRow();
        addBlankRow();
        addBlankRow();
        // 흰색 폰 셋팅
        for (i = 0; i < COL_CNT; i++) {
            addPiece(Piece.createWhitePawn());
        }
        // 백색 기물(폰 제외) 셋팅
        addPiece(Piece.createWhiteRook());
        addPiece(Piece.createWhiteKnight());
        addPiece(Piece.createWhiteBishop());
        addPiece(Piece.createWhiteQueen());
        addPiece(Piece.createWhiteKing());
        addPiece(Piece.createWhiteBishop());
        addPiece(Piece.createWhiteKnight());
        addPiece(Piece.createWhiteRook());
    }
    public void initializeEmpty() {
        for(int i = 0; i < ROW_CNT; i++) {
            addBlankRow();
        }
    }
    public void addBlankRow() {
        for(int i = 0; i < COL_CNT; i++) {
            addPiece(Piece.createBlank());
        }
    }

    public List<Piece> getPieceList() {
        return this.pieceList;
    }

    public void addPiece(Piece piece) {
        this.pieceList.add(piece);
    }

    public Piece findPiece(int idx) {
        return this.pieceList.get(idx);
    }
    public Piece findPiece(Position position) {
        return pieceList.get(position.getListIdx());
    }

    public int size() {
        return pieceList.size();
    }
    public int pieceCount() {
        int pCnt = 0;
        for(int i = 0; i < ROW_CNT * COL_CNT; i++) {
            if(this.pieceList.get(i).getType() != Type.NO_PIECE) {
                pCnt++;
            }
        }

        return pCnt;
    }
    public int pieceCount(Color color, Type type) {
        int pCnt = 0;
        for(Piece piece: pieceList) {
            if(piece.getColor() == color && piece.getType() == type) {
                pCnt ++;
            }
        }

        return pCnt;
    }
}
