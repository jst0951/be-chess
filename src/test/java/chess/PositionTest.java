package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}