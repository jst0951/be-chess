package chess;

import chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.pieces.Piece.Type;
import chess.pieces.Piece.Color;

import static org.assertj.core.api.Assertions.*;
import static utils.StringUtils.appendNewLine;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
        board.initialize();
    }

    @Test
    @DisplayName("초기화 후 보드를 출력한다.")
    public void print() throws Exception {
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("초기화 후 최초에 보드가 올바르게 표시되어야 한다.")
    public void create() throws Exception {
        assertThat(board.pieceCount()).isEqualTo(32);
        String blankRank = appendNewLine("........");
        assertThat(board.showBoard()).isEqualTo(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"));
    }

    @Test
    @DisplayName("isBlack() : 검은 말은 true, 흰색 말은 false를 반환해야 한다.")
    public void validateIsBlack() throws Exception {
        Piece blackPiece = Piece.createBlackPawn();
        Piece whitePiece = Piece.createWhitePawn();
        assertThat(blackPiece.isBlack()).isEqualTo(true);
        assertThat(whitePiece.isBlack()).isEqualTo(false);
    }

    @Test
    @DisplayName("isWhite() : 검은 말은 false, 흰색 말은 true를 반환해야 한다.")
    public void validateIsWhite() throws Exception {
        Piece blackPiece = Piece.createBlackPawn();
        Piece whitePiece = Piece.createWhitePawn();
        assertThat(blackPiece.isWhite()).isEqualTo(false);
        assertThat(whitePiece.isWhite()).isEqualTo(true);
    }

    @Test
    @DisplayName("모든 기물의 개수를 출력해야 한다.")
    public void pieceCount() {
        assertThat(board.pieceCount()).isEqualTo(32);
    }

    @Test
    @DisplayName("특정 색과 종류에 해당하는 기물의 개수를 출력해야 한다.")
    public void specificPieceCount() {
        assertThat(board.pieceCount(Color.BLACK, Type.PAWN)).isEqualTo(8);
    }

    @Test
    @DisplayName("주어진 위치의 기물이 조회된다.")
    public void findPiece() {
        assertThat(board.findPiece("a8").getColor()).isEqualTo(Color.BLACK);
        assertThat(board.findPiece("a8").getType()).isEqualTo(Type.ROOK);
    }
}