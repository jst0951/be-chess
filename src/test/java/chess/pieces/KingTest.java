package chess.pieces;

import chess.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    private Piece king;

    @BeforeEach
    public void setup() {
        king = Piece.createWhiteKing();
    }

    @Test
    @DisplayName("킹은 어느 방향으로든 1칸 움직일 수 있다.")
    public void kingMoveOneSquare() {
        // Given
        Position source = new Position("f5");
        Position targetDiagonal = new Position("g6");
        Position targetLinear = new Position("g5");

        // When
        boolean isMovableDiagonal = king.verifyMovePosition(source, targetDiagonal);
        boolean isMovableLinear = king.verifyMovePosition(source, targetLinear);

        // Then
        assertThat(isMovableDiagonal).isTrue();
        assertThat(isMovableLinear).isTrue();
    }

    @Test
    @DisplayName("킹은 여러 칸 움직일 수 없다.")
    public void kingMoveMultiSquare() {
        // Given
        Position source = new Position("f5");
        Position target = new Position("d3");

        // When
        boolean isMovable = king.verifyMovePosition(source, target);

        // Then
        assertThat(isMovable).isFalse();
    }

}