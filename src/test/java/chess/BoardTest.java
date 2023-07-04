package chess;

import chess.Board;
import chess.pieces.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BoardTest {
    @Test
    @DisplayName("Board에 Pawn 2개 생성 후 확인")
    public void create() throws Exception {
        Board board = new Board();

        Pawn pawn;
        pawn = addPawn(board, Pawn.WHITE_COLOR);
        assertThat(board.size()).isEqualTo(1);
        assertThat(board.findPawn(0)).isEqualTo(pawn);

        pawn = addPawn(board, Pawn.BLACK_COLOR);
        assertThat(board.size()).isEqualTo(2);
        assertThat(board.findPawn(1)).isEqualTo(pawn);
    }

    public Pawn addPawn(Board board, String color) {
        Pawn pawn = new Pawn(color);
        board.add(pawn);

        return pawn;
    }
}