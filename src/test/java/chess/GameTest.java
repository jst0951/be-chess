package chess;

import chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.Game.*;
import static chess.pieces.Piece.Color;
import static chess.pieces.Piece.Type;
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

        assertThat(game.calculatePoint(Color.BLACK)).isEqualTo(15.0, withPrecision(0.01));
        assertThat(game.calculatePoint(Color.WHITE)).isEqualTo(7.0, withPrecision(0.01));
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

        assertThat(game.calculatePoint(Color.BLACK)).isEqualTo(20, withPrecision(0.01));
        assertThat(game.calculatePoint(Color.WHITE)).isEqualTo(19.5, withPrecision(0.01));
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

        List<Piece> sortedPieceList = game.pieceListSortedByScoreAsc(Color.WHITE);
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

        List<Piece> sortedPieceList = game.pieceListSortedByScoreDesc(Color.WHITE);
    }

    @Test
    @DisplayName("원본 좌표, 목표 좌표가 주어졌을 때 움직여진다.(이동 가능한 경우)")
    public void isMovableTestOK() {
        // Given
        board.initialize();
        Position source = new Position("b2");
        Position target = new Position("b3");

        // When
        game.move(source, target);

        // Then
        // 올바르게 이동됨
        assertThat(board.findPiece(source).getColor()).isEqualTo(Color.NOCOLOR);
        assertThat(board.findPiece(source).getType()).isEqualTo(Type.NO_PIECE);
        assertThat(board.findPiece(target).getColor()).isEqualTo(Color.WHITE);
        assertThat(board.findPiece(target).getType()).isEqualTo(Type.PAWN);
    }
    @Test
    @DisplayName("원본 좌표, 목포 좌표가 주어졌을 때 움직여지지 않는다.(판 밖으로 벗어나는 경우)")
    public void isMovableTestOutOfBoundary() {
        // Given
        board.initialize();
        Position source = new Position("h2");
        Position target = new Position("i3");

        // When
        Throwable throwable = catchThrowable(() ->
                game.move(source, target)
        );

        // Then
        // Exception 발생
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage(ERROR_OUT_OF_BOUNDARY);
        // 이동이 진행되지 않음
        assertThat(board.findPiece(source).getColor()).isEqualTo(Color.WHITE);
        assertThat(board.findPiece(source).getType()).isEqualTo(Type.PAWN);
    }

    @Test
    @DisplayName("원본 좌표, 목표 좌표가 주어졌을 때 움직여지지 않는다.(원본 좌표와 목표 좌표가 같은 경우)")
    public void isMovableSamePosition() {
        // Given
        board.initialize();
        Position source = new Position("b2");
        Position target = new Position("b2");

        // When
        Throwable throwable = catchThrowable(() ->
                game.move(source, target)
        );

        // Then
        // Exception 발생
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage(ERROR_SAME_POSITION);
        // 이동이 진행되지 않음
        assertThat(board.findPiece(source).getColor()).isEqualTo(Color.WHITE);
        assertThat(board.findPiece(source).getType()).isEqualTo(Type.PAWN);
        assertThat(board.findPiece(target).getColor()).isEqualTo(Color.WHITE);
        assertThat(board.findPiece(target).getType()).isEqualTo(Type.PAWN);
    }
    @Test
    @DisplayName("원본 좌표, 목표 좌표가 주어졌을 때 움직여지지 않는다.(이동하려는 곳에 같은 편의 기물이 있는 경우)")
    public void isMovableTestSameTeamExists() {
        // Given
        board.initialize();
        Position source = new Position("e1");
        Position target = new Position("e2");

        // When
        Throwable throwable = catchThrowable(() ->
                game.move(source, target)
        );

        // Then
        // Exception 발생
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage(ERROR_SAME_TEAM_EXISTS);
        // 이동이 진행되지 않음
        assertThat(board.findPiece(source).getColor()).isEqualTo(Color.WHITE);
        assertThat(board.findPiece(source).getType()).isEqualTo(Type.KING);
        assertThat(board.findPiece(target).getColor()).isEqualTo(Color.WHITE);
        assertThat(board.findPiece(target).getType()).isEqualTo(Type.PAWN);
    }
    @Test
    @DisplayName("시작 위치, 이동 위치가 주어졌을 때 움직여지지 않는다.(이동할 수 없는 움직임인 경우)")
    public void isMovableTestMoveUnavailable() {
        // Given
        board.initialize();
        Position source = new Position("e1");
        Position target = new Position("e4");

        // When
        Throwable throwable = catchThrowable(() ->
                game.move(source, target)
        );

        // Then
        // Exception 발생
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage(ERROR_MOVE_UNAVAILABLE);
        // 올바르게 이동되는 경우
        assertThat(board.findPiece(source).getColor()).isEqualTo(Color.WHITE);
        assertThat(board.findPiece(source).getType()).isEqualTo(Type.KING);
        assertThat(board.findPiece(target).getColor()).isEqualTo(Color.NOCOLOR);
        assertThat(board.findPiece(target).getType()).isEqualTo(Type.NO_PIECE);
    }

    @Test
    @DisplayName("움직이고 난 뒤 차례가 바뀌어야 한다.")
    public void changeTurn() {
        // Given
        board.initialize();
        Position source = new Position("b2");
        Position target = new Position("b3");

        // When
        game.move(source, target);

        // Then
        assertThat(game.getTurn()).isEqualTo(Color.BLACK);
    }
}