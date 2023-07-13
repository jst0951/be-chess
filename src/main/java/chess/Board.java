package chess;

import chess.pieces.Piece;

import java.util.*;
import java.util.stream.IntStream;

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
        pieceList.clear();
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
        pieceList.clear();
        for(int i = 0; i < ROW_CNT; i++) {
            addBlankRow();
        }
    }
    private void addPiece(Piece piece) {
        pieceList.add(piece);
    }
    private void addBlankRow() {
        IntStream.range(0, COL_CNT)
                .forEach((i) -> addPiece(Piece.createBlank()));
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
        return (int) pieceList.stream()
                .filter(piece -> piece.getType() != Type.NO_PIECE)
                .count();
    }
    public int pieceCount(Color color, Type type) {
        return (int) pieceList.stream()
                .filter(piece -> piece.getColor() == color && piece.getType() == type)
                .count();
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
