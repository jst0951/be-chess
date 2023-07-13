package chess;

import chess.pieces.Piece;

import java.util.*;

import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;

public class Board {
    public static final int ROW_CNT = 8;
    public static final int COL_CNT = 8;

    private final List<Piece> pieceList;

    public Board() {
        this.pieceList = new ArrayList<>();
    }

    public void initialize() {
        this.pieceList.clear();
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
        for (int i = 0; i < COL_CNT; i++) {
            addPiece(Piece.createBlackPawn());
        }
        // 빈 칸 4줄 셋팅
        addBlankRow();
        addBlankRow();
        addBlankRow();
        addBlankRow();
        // 흰색 폰 셋팅
        for (int i = 0; i < COL_CNT; i++) {
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
        this.pieceList.clear();
        for(int i = 0; i < ROW_CNT; i++) {
            addBlankRow();
        }
    }
    private void addPiece(Piece piece) {
        this.pieceList.add(piece);
    }
    private void addBlankRow() {
        for(int i = 0; i < COL_CNT; i++) {
            addPiece(Piece.createBlank());
        }
    }

    public Piece findPiece(Position position) {
        return pieceList.get(position.getListIdx());
    }
    public List<Piece> getPieceList() {
        return Collections.unmodifiableList(this.pieceList);
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

    public void putPiece(Position position, Piece piece) {
        pieceList.set(position.getListIdx(), piece);
    }
    public void movePiece(Position source, Position target) {
        Piece piece = pieceList.get(source.getListIdx());

        pieceList.set(source.getListIdx(), Piece.createBlank());
        pieceList.set(target.getListIdx(), piece);
    }
}
