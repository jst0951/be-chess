package chess.pieces;

import chess.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
    private Piece queen;

    @BeforeEach
    public void setup() {
        queen = Piece.createWhiteQueen();
    }

    @Test
    @DisplayName("퀸은 어느 방향으로든 여러 칸 움직일 수 있다.")
    public void queenMove() {
        // Given
        Position source = new Position("d4");
        Position targetOneSqure = new Position("e5");
        Position targetMultiSquare = new Position("f6");

        // When
        boolean isMovableOneSquare = queen.verifyMovePosition(source, targetOneSqure);
        boolean isMovableMultiSquare = queen.verifyMovePosition(source, targetMultiSquare);

        // Then
        assertThat(isMovableOneSquare).isTrue();
        assertThat(isMovableMultiSquare).isTrue();
    }

}