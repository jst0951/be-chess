package chess;

import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import static utils.StringUtils.appendNewLine;

public class Board {
    private List<Piece> pieceList;
    private final int ROW_CNT = 8;
    private final int COL_CNT = 8;
    // ROW는 1부터 시작합니다.
    private final int BLACK_PAWN_ROW = 2;
    private final int WHITE_PAWN_ROW = 6;


    public Board() {
        this.pieceList = new ArrayList<>();
    }

    public void add(Piece piece) {
        this.pieceList.add(piece);
    }

    public Integer size() {
        return pieceList.size();
    }

    public Piece findPawn(int idx) {
        return this.pieceList.get(idx);
    }

    public void initialize() {
        int i;
        int startIdx, endIdx;
        // 전체 null로 초기화
        for (i = 0; i < ROW_CNT * COL_CNT; i++) {
            this.pieceList.add(null);
        }
        // 검은색 폰 셋팅
        startIdx = ROW_CNT * (BLACK_PAWN_ROW - 1);
        endIdx = startIdx + COL_CNT;
        for (i = startIdx; i < endIdx; i++) {
            this.pieceList.set(i, Piece.createBlackPawn());
        }
        // 흰색 폰 셋팅
        startIdx = ROW_CNT * (WHITE_PAWN_ROW - 1);
        endIdx = startIdx + COL_CNT;
        for (i = startIdx; i < endIdx; i++) {
            this.pieceList.set(i, Piece.createWhitePawn());
        }
    }

    public String getWhitePawnsResult() {
        return rowToString(48, 56);
    }

    public String getBlackPawnsResult() {
        return rowToString(8, 16);
    }

    public String rowToString(int start, int end) {
        StringBuilder sb = new StringBuilder();
        for(int i=start;i<end;i++) {
            sb.append(this.pieceList.get(i).getRepresentation());
        }
        return sb.toString();
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<64;i++) {
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
        return -1;
    }

    public String showBoard() {
        return "";
    }
}
