package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.StringUtils.appendNewLine;

public class ViewTest {

    private Board board;
    private View view;

    @BeforeEach
    public void setup() {
        board = new Board();
        view = new View(board);
    }

    @Test
    @DisplayName("초기화 후 최초에 보드가 올바르게 표시되어야 한다.")
    public void create() throws Exception {
        // Given
        board.initialize();

        // When, Then
        assertThat(board.pieceCount()).isEqualTo(32);
        String blankRank = appendNewLine("........");
        assertThat(view.showBoard()).isEqualTo(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"));
    }
}
