package chess;

import chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import chess.pieces.Piece.Type;
import chess.pieces.Piece.Color;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static utils.StringUtils.appendNewLine;

class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("isBlack() : 검은 말은 true, 흰색 말은 false를 반환해야 한다.")
    public void validateIsBlack() throws Exception {
        // Given
        Piece blackPiece = Piece.createBlackPawn();
        Piece whitePiece = Piece.createWhitePawn();

        // When
        boolean isBlackPieceBlack = blackPiece.isBlack();
        boolean isWhitePieceBlack = whitePiece.isBlack();

        // Then
        assertThat(isBlackPieceBlack).isEqualTo(true);
        assertThat(isWhitePieceBlack).isEqualTo(false);
    }

    @Test
    @DisplayName("isWhite() : 검은 말은 false, 흰색 말은 true를 반환해야 한다.")
    public void validateIsWhite() throws Exception {
        // Given
        Piece blackPiece = Piece.createBlackPawn();
        Piece whitePiece = Piece.createWhitePawn();

        // When
        boolean isBlackPieceWhite = blackPiece.isWhite();
        boolean isWhitePieceWhite = whitePiece.isWhite();

        // Then
        assertThat(isBlackPieceWhite).isEqualTo(false);
        assertThat(isWhitePieceWhite).isEqualTo(true);
    }

    @Test
    @DisplayName("모든 기물의 개수를 출력해야 한다.")
    public void pieceCount() {
        // Given
        board.initialize();

        // When
        int pieceCount = board.pieceCount();

        // Then
        assertThat(pieceCount).isEqualTo(32);
    }

    @Test
    @DisplayName("특정 색과 종류에 해당하는 기물의 개수를 출력해야 한다.")
    public void specificPieceCount() {
        // Given
        board.initialize();

        // When
        int pieceCount = board.pieceCount(Color.BLACK, Type.PAWN);

        // Then
        assertThat(pieceCount).isEqualTo(8);
    }

    @Test
    @DisplayName("주어진 위치의 기물이 조회된다.")
    public void findPiece() {
        // Given
        board.initialize();
        String positionString = "a8";

        // When
        Piece piece = board.findPiece(new Position(positionString));

        // Then
        assertThat(piece.getColor()).isEqualTo(Color.BLACK);
        assertThat(piece.getType()).isEqualTo(Type.ROOK);
    }

    @Test
    @DisplayName("임의의 기물을 체스판 위에 추가한다.(원래 있던 것은 무시, 추가만)")
    public void putPiece() {
        // Given
        board.initializeEmpty();
        Piece piece = Piece.createBlackRook();
        String position = "b5";

        // When
        board.putPiece(new Position(position), piece);

        // Then
        assertThat(board.findPiece(new Position(position))).isEqualTo(piece);
    }

    @Test
    @DisplayName("기물을 목표 위치로 옮긴다.(Game 제약상의 조건 무시)")
    public void movePiece() {
        // Given
        board.initialize();
        Position source = new Position("b2");
        Position target = new Position("b3");

        // When
        board.movePiece(source, target);

        // Then
        assertThat(board.findPiece(source).getType()).isEqualTo(Type.NO_PIECE);
        assertThat(board.findPiece(source).getColor()).isEqualTo(Color.NOCOLOR);
        assertThat(board.findPiece(target).getType()).isEqualTo(Type.PAWN);
        assertThat(board.findPiece(target).getColor()).isEqualTo(Color.WHITE);
    }

}