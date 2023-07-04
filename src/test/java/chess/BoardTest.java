package chess;

import chess.pieces.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BoardTest {
    @Test
    @DisplayName("Board에 Piece 2개 생성 후 확인")
    public void create() throws Exception {
        Board board = new Board();

        Piece whitePawn = Piece.createWhitePawn();
        board.add(whitePawn);
        assertThat(board.size()).isEqualTo(1);
        assertThat(board.findPawn(0)).isEqualTo(whitePawn);

        Piece blackPawn = Piece.createBlackPawn();
        board.add(blackPawn);
        assertThat(board.size()).isEqualTo(2);
        assertThat(board.findPawn(1)).isEqualTo(blackPawn);
    }

    @Test
    public void initialize() throws Exception {
        Board board = new Board();
        board.initialize();
        assertThat(board.getWhitePawnsResult()).isEqualTo("pppppppp");
        assertThat(board.getBlackPawnsResult()).isEqualTo("PPPPPPPP");
    }

    @Test
    public void print() throws Exception {
        Board board = new Board();
        board.initialize();
        System.out.println(board.print());
    }
}