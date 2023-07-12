package chess.pieces;

import chess.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    private Piece knight;

    @BeforeEach
    public void setup() {
        knight = Piece.createWhiteKnight();
    }

    @Test
    @DisplayName("나이트는 정해진 패턴대로만 움직일 수 있다.")
    public void knightMove() {
        // Given
        Position source = new Position("d4");
        Position targetPattern = new Position("b3");
        Position targetNonPattern = new Position("c3");

        // When
        boolean isMovablePattern = knight.verifyMovePosition(source, targetPattern);
        boolean isMovableNonPattern = knight.verifyMovePosition(source, targetNonPattern);

        // Then
        assertThat(isMovablePattern).isTrue();
        assertThat(isMovableNonPattern).isFalse();
    }
}