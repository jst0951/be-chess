package chess.pieces;

import chess.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BlankTest {
    private Piece blank;

    @BeforeEach
    public void setup() {
        blank = Piece.createBlank();
    }

    @Test
    @DisplayName("빈 칸은 어디로도 이동할 수 없다.")
    public void blankMove() {
        // Given
        Position source = new Position("b5");
        Position target = new Position("b6");

        // When
        boolean isMovable = blank.verifyMovePosition(source, target);

        // Then
        assertThat(isMovable).isFalse();
    }
}