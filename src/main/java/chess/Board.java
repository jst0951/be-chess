package chess;

import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import static utils.StringUtils.appendNewLine;

public class Board {
    private List<Piece> pieceList;
    private final int ROW_CNT = 8;
    private final int COL_CNT = 8;

    public Board() {
        this.pieceList = new ArrayList<>();
    }

    public void add(Piece piece) {
        this.pieceList.add(piece);
    }

    public Integer size() {
        return pieceList.size();
    }

    public Piece findPiece(int idx) {
        return this.pieceList.get(idx);
    }

    public void initialize() {
        int i;
        // 흑색 기물(폰 제외) 셋팅
        add(Piece.createBlackRook());
        add(Piece.createBlackKnight());
        add(Piece.createBlackBishop());
        add(Piece.createBlackQueen());
        add(Piece.createBlackKing());
        add(Piece.createBlackBishop());
        add(Piece.createBlackKnight());
        add(Piece.createBlackRook());
        // 검은색 폰 셋팅
        for (i = 0; i < COL_CNT; i++) {
            add(Piece.createBlackPawn());
        }
        // 빈 칸 4줄 셋팅
        addBlankRow();
        addBlankRow();
        addBlankRow();
        addBlankRow();
        // 흰색 폰 셋팅
        for (i = 0; i < COL_CNT; i++) {
            add(Piece.createWhitePawn());
        }
        // 백색 기물(폰 제외) 셋팅
        add(Piece.createWhiteRook());
        add(Piece.createWhiteKnight());
        add(Piece.createWhiteBishop());
        add(Piece.createWhiteQueen());
        add(Piece.createWhiteKing());
        add(Piece.createWhiteBishop());
        add(Piece.createWhiteKnight());
        add(Piece.createWhiteRook());
    }
    public void addBlankRow() {
        for(int i = 0; i < COL_CNT; i++) {
            this.pieceList.add(null);
        }
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ROW_CNT * COL_CNT; i++) {
            if (this.pieceList.get(i) == null) {
                sb.append('.');
            }
            else {
                sb.append(this.pieceList.get(i).getRepresentation());
            }

            if (i % 8 == 7) {
                appendNewLine(sb);
            }
        }
        return sb.toString();
    }

    public int pieceCount() {
        int pCnt = 0;
        for(int i = 0; i < ROW_CNT * COL_CNT; i++) {
            if(this.pieceList.get(i) != null) {
                pCnt++;
            }
        }

        return pCnt;
    }

}
