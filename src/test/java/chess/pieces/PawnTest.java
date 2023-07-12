package chess.pieces;

import chess.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    private Piece whitePawn;
    private Piece blackPawn;

    @BeforeEach
    public void setup() {
        whitePawn = Piece.createWhitePawn();
        blackPawn = Piece.createBlackPawn();
    }

    @Test
    @DisplayName("흰색 폰은 북,북서,북동 방향으로만 움직일 수 있다.")
    public void whitePawnMove() {
        // Given
        Position source = new Position("e2");
        Position targetN = new Position("e3");
        Position targetNW = new Position("d3");
        Position targetNE = new Position("f3");
        Position targetS = new Position("e1");

        // When
        boolean isMovableN = whitePawn.verifyMovePosition(source, targetN);
        boolean isMovableNW = whitePawn.verifyMovePosition(source, targetNW);
        boolean isMovableNE = whitePawn.verifyMovePosition(source, targetNE);
        boolean isMovableS = whitePawn.verifyMovePosition(source, targetS);

        // Then
        assertThat(isMovableN).isTrue();
        assertThat(isMovableNW).isTrue();
        assertThat(isMovableNE).isTrue();
        assertThat(isMovableS).isFalse();
    }

    @Test
    @DisplayName("검은 폰은 남,남서,남동 방향으로만 움직일 수 있다.")
    public void blackPawnMove() {
        // Given
        Position source = new Position("c7");
        Position targetS = new Position("c6");
        Position targetSW = new Position("b6");
        Position targetSE = new Position("d6");
        Position targetN = new Position("c8");

        // When
        boolean isMovableS = blackPawn.verifyMovePosition(source, targetS);
        boolean isMovableSW = blackPawn.verifyMovePosition(source, targetSW);
        boolean isMovableSE = blackPawn.verifyMovePosition(source, targetSE);
        boolean isMovableN = blackPawn.verifyMovePosition(source, targetN);

        // Then
        assertThat(isMovableS).isTrue();
        assertThat(isMovableSW).isTrue();
        assertThat(isMovableSE).isTrue();
        assertThat(isMovableN).isFalse();
    }

    @Test
    @DisplayName("폰은 한 칸만 이동할 수 있다.")
    public void pawnMoveOneSquare() {
        // Given
        Position source = new Position("e2");
        Position targetOneSquare = new Position("e3");
        Position targetMultiSquare = new Position("e5");

        // When
        boolean isMovableOneSquare = whitePawn.verifyMovePosition(source, targetOneSquare);
        boolean isMovableMultiSquare = whitePawn.verifyMovePosition(source, targetMultiSquare);

        // Then
        assertThat(isMovableOneSquare).isTrue();
        assertThat(isMovableMultiSquare).isFalse();
    }
}