package chess;

import chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.Game.*;
import static org.assertj.core.api.Assertions.*;

class GameTest {
    private Board board;
    private Game game;

    @BeforeEach
    public void setup() {
        board = new Board();
        game = new Game(board);
    }

    @Test
    @DisplayName("각자의 점수를 계산해야한다.(폰이 같은 세로줄에 없는 경우)")
    public void calculatePoint() throws Exception {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        assertThat(game.calculatePoint(Piece.Color.BLACK)).isEqualTo(15.0, withPrecision(0.01));
        assertThat(game.calculatePoint(Piece.Color.WHITE)).isEqualTo(7.0, withPrecision(0.01));
    }
    @Test
    @DisplayName("각자의 점수를 계산해야한다.(폰이 같은 세로줄에 있는 경우)")
    public void calculatePointPawnProb() throws Exception {
        board.initializeEmpty();

        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());
        addPiece("a7", Piece.createBlackPawn());
        addPiece("c7", Piece.createBlackPawn());
        addPiece("d7", Piece.createBlackBishop());
        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());

        addPiece("f4", Piece.createWhiteKnight());
        addPiece("g4", Piece.createWhiteQueen());
        addPiece("f3", Piece.createWhitePawn());
        addPiece("h3", Piece.createWhitePawn());
        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        assertThat(game.calculatePoint(Piece.Color.BLACK)).isEqualTo(20, withPrecision(0.01));
        assertThat(game.calculatePoint(Piece.Color.WHITE)).isEqualTo(19.5, withPrecision(0.01));
    }
    private void addPiece(String position, Piece piece) {
        board.putPiece(new Position(position), piece);
    }

    @Test
    @DisplayName("기물의 점수가 오름차순으로 정렬되어야 한다.")
    public void sortByScoreAsc() {
        board.initializeEmpty();

        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());
        addPiece("a7", Piece.createBlackPawn());
        addPiece("c7", Piece.createBlackPawn());
        addPiece("d7", Piece.createBlackBishop());
        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());

        addPiece("f4", Piece.createWhiteKnight());
        addPiece("g4", Piece.createWhiteQueen());
        addPiece("f3", Piece.createWhitePawn());
        addPiece("h3", Piece.createWhitePawn());
        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        List<Piece> sortedPieceList = game.pieceListSortedByScoreAsc(Piece.Color.WHITE);
    }

    @Test
    @DisplayName("기물의 점수가 내림차순으로 정렬되어야 한다.")
    public void sortByScoreDesc() {
        board.initializeEmpty();

        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());
        addPiece("a7", Piece.createBlackPawn());
        addPiece("c7", Piece.createBlackPawn());
        addPiece("d7", Piece.createBlackBishop());
        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());

        addPiece("f4", Piece.createWhiteKnight());
        addPiece("g4", Piece.createWhiteQueen());
        addPiece("f3", Piece.createWhitePawn());
        addPiece("h3", Piece.createWhitePawn());
        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        List<Piece> sortedPieceList = game.pieceListSortedByScoreDesc(Piece.Color.WHITE);
    }

    @Test
    @DisplayName("원본 좌표, 목표 좌표가 주어졌을 때 움직여진다.(이동 가능한 경우)")
    public void isMovableTestOK() {
        // Given
        board.initialize();
        String sourcePosition = "b2";
        String targetPosition = "b3";

        // When
        game.move(new Position(sourcePosition), new Position(targetPosition));

        // Then
        // 올바르게 이동됨
        assertThat(board.findPiece(new Position(sourcePosition)).getColor()).isEqualTo(Piece.Color.NOCOLOR);
        assertThat(board.findPiece(new Position(sourcePosition)).getType()).isEqualTo(Piece.Type.NO_PIECE);
        assertThat(board.findPiece(new Position(targetPosition)).getColor()).isEqualTo(Piece.Color.WHITE);
        assertThat(board.findPiece(new Position(targetPosition)).getType()).isEqualTo(Piece.Type.PAWN);
    }
    @Test
    @DisplayName("원본 좌표, 목포 좌표가 주어졌을 때 움직여지지 않는다.(판 밖으로 벗어나는 경우)")
    public void isMovableTestOutOfBoundary() {
        // Given
        board.initialize();
        String sourcePosition = "h2";
        String targetPosition = "i3";

        // When
        Throwable throwable = catchThrowable(() ->
                game.move(new Position(sourcePosition), new Position(targetPosition))
        );

        // Then
        // Exception 발생
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage(ERROR_OUT_OF_BOUNDARY);
        // 이동이 진행되지 않음
        assertThat(board.findPiece(new Position(sourcePosition)).getColor()).isEqualTo(Piece.Color.WHITE);
        assertThat(board.findPiece(new Position(sourcePosition)).getType()).isEqualTo(Piece.Type.PAWN);
    }

    @Test
    @DisplayName("원본 좌표, 목표 좌표가 주어졌을 때 움직여지지 않는다.(원본 좌표와 목표 좌표가 같은 경우)")
    public void isMovableSamePosition() {
        // Given
        board.initialize();
        String sourcePosition = "b2";
        String targetPosition = "b2";

        // When
        Throwable throwable = catchThrowable(() ->
                game.move(new Position(sourcePosition), new Position(targetPosition))
        );

        // Then
        // Exception 발생
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage(ERROR_SAME_POSITION);
        // 이동이 진행되지 않음
        assertThat(board.findPiece(new Position(sourcePosition)).getColor()).isEqualTo(Piece.Color.WHITE);
        assertThat(board.findPiece(new Position(sourcePosition)).getType()).isEqualTo(Piece.Type.PAWN);
        assertThat(board.findPiece(new Position(targetPosition)).getColor()).isEqualTo(Piece.Color.WHITE);
        assertThat(board.findPiece(new Position(targetPosition)).getType()).isEqualTo(Piece.Type.PAWN);
    }
    @Test
    @DisplayName("원본 좌표, 목표 좌표가 주어졌을 때 움직여지지 않는다.(이동하려는 곳에 같은 편의 기물이 있는 경우)")
    public void isMovableTestSameTeamExists() {
        // Given
        board.initialize();
        String sourcePosition = "e1";
        String targetPosition = "e2";

        // When
        Throwable throwable = catchThrowable(() ->
                game.move(new Position(sourcePosition), new Position(targetPosition))
        );

        // Then
        // Exception 발생
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage(ERROR_SAME_TEAM_EXISTS);
        // 이동이 진행되지 않음
        assertThat(board.findPiece(new Position(sourcePosition)).getColor()).isEqualTo(Piece.Color.WHITE);
        assertThat(board.findPiece(new Position(sourcePosition)).getType()).isEqualTo(Piece.Type.KING);
        assertThat(board.findPiece(new Position(targetPosition)).getColor()).isEqualTo(Piece.Color.WHITE);
        assertThat(board.findPiece(new Position(targetPosition)).getType()).isEqualTo(Piece.Type.PAWN);
    }
    @Test
    @DisplayName("시작 위치, 이동 위치가 주어졌을 때 움직여지지 않는다.(이동할 수 없는 움직임인 경우)")
    public void isMovableTestMoveUnavailable() {
        // Given
        board.initialize();
        String sourcePosition = "e1";
        String targetPosition = "e4";

        // When
        Throwable throwable = catchThrowable(() ->
                game.move(new Position(sourcePosition), new Position(targetPosition))
        );

        // Then
        // Exception 발생
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage(ERROR_MOVE_UNAVAILABLE);
        // 올바르게 이동되는 경우
        assertThat(board.findPiece(new Position(sourcePosition)).getColor()).isEqualTo(Piece.Color.WHITE);
        assertThat(board.findPiece(new Position(sourcePosition)).getType()).isEqualTo(Piece.Type.KING);
        assertThat(board.findPiece(new Position(targetPosition)).getColor()).isEqualTo(Piece.Color.NOCOLOR);
        assertThat(board.findPiece(new Position(targetPosition)).getType()).isEqualTo(Piece.Type.NO_PIECE);
    }
}