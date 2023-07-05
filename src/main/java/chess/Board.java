package chess;

import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import static utils.StringUtils.appendNewLine;
import chess.pieces.Piece.Type;

public class Board {
    private List<Piece> pieceList;
    private final int ROW_CNT = 8;
    private final int COL_CNT = 8;

    public Board() {
        this.pieceList = new ArrayList<>();
    }

    public void addPiece(Piece piece) {
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
    public void addBlankRow() {
        for(int i = 0; i < COL_CNT; i++) {
            addPiece(Piece.createBlank());
        }
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < ROW_CNT * COL_CNT; i++) {
            sb.append(this.pieceList.get(i).getRepresentation());

            if (i % 8 == 7) {
                appendNewLine(sb);
            }
        }
        return sb.toString();
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

}
