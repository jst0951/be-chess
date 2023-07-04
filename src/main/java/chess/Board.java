package chess;

import chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;
import static utils.StringUtils.appendNewLine;

public class Board {
    private List<Pawn> pawnList;

    public Board() {
        this.pawnList = new ArrayList<>();
    }

    public void add(Pawn pawn) {
        this.pawnList.add(pawn);
    }

    public Integer size() {
        return pawnList.size();
    }

    public Pawn findPawn(int idx) {
        return this.pawnList.get(idx);
    }

    public void initialize() {
        int i;
        // 전체 null로 초기화
        for (i = 0; i < 8 * 8; i++) {
            this.pawnList.add(null);
        }
        // 검은색 폰 셋팅
        for (i = 8; i < 16; i++) {
            this.pawnList.set(i, new Pawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION));
        }
        // 흰색 폰 셋팅
        for (i = 48; i < 56; i++) {
            this.pawnList.set(i, new Pawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION));
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
            sb.append(this.pawnList.get(i).getRepresentation());
        }
        return sb.toString();
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<64;i++) {
            if (this.pawnList.get(i) == null) {
                sb.append('.');
            }
            else {
                sb.append(this.pawnList.get(i).getRepresentation());
            }

            if (i % 8 == 7) {
                appendNewLine(sb);
            }
        }
        return sb.toString();
    }
}
