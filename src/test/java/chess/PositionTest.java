package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.Position.ERROR_OUT_OF_BOUNDARY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class PositionTest {

    @Test
    @DisplayName("문자열로 입력된 좌표를 X,Y좌표로 변환해야 한다.")
    public void translateXY() {
        // Given
        Position position = new Position("d5");

        // When
        int xPos = position.getXPos();
        int yPos = position.getYPos();

        // Then
        assertThat(xPos).isEqualTo(3);
        assertThat(yPos).isEqualTo(3);
    }

    @Test
    @DisplayName("문자열로 입력된 좌표를 list 인덱스로 변환해야 한다.")
    public void translateListIdx() {
        // Given
        Position position = new Position("d5");

        // When
        int listIdx = position.getListIdx();

        // Then
        assertThat(listIdx).isEqualTo(27);
    }

    @Test
    @DisplayName("범위를 벗어난 좌표를 받으면 Exception을 발생시켜야 한다.")
    public void positionOutOfBoundary(){
        // Given
        String positonString = "z1";

        // when
        Throwable throwable = catchThrowable(() -> new Position(positonString));

        // Then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage(ERROR_OUT_OF_BOUNDARY);
    }
}