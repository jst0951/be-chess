package chess;

import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import static utils.StringUtils.appendNewLine;

public class Board {
    private List<Piece> pieceList;
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

    public Piece findPawn(int idx) {
        return this.pieceList.get(idx);
    }

    public void initialize() {
        int i;
        int startIdx, endIdx;
        // 흑색 기물(폰 제외) 셋팅
        this.pieceList.add(Piece.createBlackRook());
        this.pieceList.add(Piece.createBlackKnight());
        this.pieceList.add(Piece.createBlackBishop());
        this.pieceList.add(Piece.createBlackQueen());
        this.pieceList.add(Piece.createBlackKing());
        this.pieceList.add(Piece.createBlackBishop());
        this.pieceList.add(Piece.createBlackKnight());
        this.pieceList.add(Piece.createBlackRook());
        // 검은색 폰 셋팅
        for (i = 0; i < COL_CNT; i++) {
            this.pieceList.add(Piece.createBlackPawn());
        }
        // 빈 칸 4줄 셋팅
        addBlankRow();
        addBlankRow();
        addBlankRow();
        addBlankRow();
        // 흰색 폰 셋팅
        for (i = 0; i < COL_CNT; i++) {
            this.pieceList.add(Piece.createWhitePawn());
        }
        // 백색 기물(폰 제외) 셋팅
        this.pieceList.add(Piece.createWhiteRook());
        this.pieceList.add(Piece.createWhiteKnight());
        this.pieceList.add(Piece.createWhiteBishop());
        this.pieceList.add(Piece.createWhiteQueen());
        this.pieceList.add(Piece.createWhiteKing());
        this.pieceList.add(Piece.createWhiteBishop());
        this.pieceList.add(Piece.createWhiteKnight());
        this.pieceList.add(Piece.createWhiteRook());
    }
    public void addBlankRow() {
        for(int i=0; i<COL_CNT; i++) {
            this.pieceList.add(null);
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

    public String showBoard() {
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
        int pCnt = 0;
        for(int i=0;i<this.pieceList.size();i++) {
            if(this.pieceList.get(i) != null) {
                pCnt++;
            }
        }

        return pCnt;
    }

}
