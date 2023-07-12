package chess.pieces;

import chess.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    private Piece rook;

    @BeforeEach
    public void setup() {
        rook = Piece.createWhiteRook();
    }

    @Test
    @DisplayName("룩은 상하좌우만, 몇 칸이던 이동 가능하다.")
    public void rookMove() {
        // Given
        Position source = new Position("d5");
        Position targetLinearOneSquare = new Position("d6");
        Position targetLinearMultiSquare = new Position("d7");
        Position targetDiagonal = new Position("e4");

        // When
        boolean isMovableLinearOneSqure = rook.verifyMovePosition(source, targetLinearOneSquare);
        boolean isMovableLinearMultiSqure = rook.verifyMovePosition(source, targetLinearMultiSquare);
        boolean isMovableDiagonal = rook.verifyMovePosition(source, targetDiagonal);

        // Then
        assertThat(isMovableLinearOneSqure).isTrue();
        assertThat(isMovableLinearMultiSqure).isTrue();
        assertThat(isMovableDiagonal).isFalse();
    }

}