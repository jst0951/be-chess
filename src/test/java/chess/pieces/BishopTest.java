package chess.pieces;

import chess.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    Piece bishop;

    @BeforeEach
    public void setup() {
        // Given
        bishop = Piece.createWhiteBishop();
    }

    @Test
    @DisplayName("비숍은 대각선으로 이동할 수 있다.")
    public void bishopMoveDiagonal() {
        // Given
        Position source = new Position("d5");
        Position target = new Position("e6");

        // When
        boolean isMovable = bishop.verifyMovePosition(source, target);

        // Then
        assertThat(isMovable).isTrue();
    }

    @Test
    @DisplayName("비숍은 상하좌우로 이동할 수 없다.")
    public void bishopMoveLinear() {
        // Given
        Position source = new Position("d5");
        Position target = new Position("d6");

        // When
        boolean isMovable = bishop.verifyMovePosition(source, target);

        // Then
        assertThat(isMovable).isFalse();
    }
}