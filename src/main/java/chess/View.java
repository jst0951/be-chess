package chess;

import chess.pieces.Piece;
import static chess.Board.ROW_CNT;
import static chess.Board.COL_CNT;

import java.util.List;

import static utils.StringUtils.appendNewLine;

public class View {
    private final List<Piece> pieceList;

    public View(List<Piece> pieceList) {
        this.pieceList = pieceList;
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ROW_CNT * COL_CNT; i++) {
            sb.append(this.pieceList.get(i).getRepresentation());

            if (i % COL_CNT == (COL_CNT - 1)) {
                appendNewLine(sb);
            }
        }
        return sb.toString();
    }
}
