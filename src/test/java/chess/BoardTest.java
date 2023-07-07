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

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("초기화 후 보드를 출력한다.")
    public void print() throws Exception {
        board.initialize();
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("초기화 후 최초에 보드가 올바르게 표시되어야 한다.")
    public void create() throws Exception {
        board.initialize();
        assertThat(board.pieceCount()).isEqualTo(32);
        String blankRank = appendNewLine("........");
        assertThat(board.showBoard()).isEqualTo(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"));
    }

    @Test
    @DisplayName("isBlack() : 검은 말은 true, 흰색 말은 false를 반환해야 한다.")
    public void validateIsBlack() throws Exception {
        Piece blackPiece = Piece.createBlackPawn();
        Piece whitePiece = Piece.createWhitePawn();
        assertThat(blackPiece.isBlack()).isEqualTo(true);
        assertThat(whitePiece.isBlack()).isEqualTo(false);
    }

    @Test
    @DisplayName("isWhite() : 검은 말은 false, 흰색 말은 true를 반환해야 한다.")
    public void validateIsWhite() throws Exception {
        Piece blackPiece = Piece.createBlackPawn();
        Piece whitePiece = Piece.createWhitePawn();
        assertThat(blackPiece.isWhite()).isEqualTo(false);
        assertThat(whitePiece.isWhite()).isEqualTo(true);
    }

    @Test
    @DisplayName("모든 기물의 개수를 출력해야 한다.")
    public void pieceCount() {
        board.initialize();
        assertThat(board.pieceCount()).isEqualTo(32);
    }

    @Test
    @DisplayName("특정 색과 종류에 해당하는 기물의 개수를 출력해야 한다.")
    public void specificPieceCount() {
        board.initialize();
        assertThat(board.pieceCount(Color.BLACK, Type.PAWN)).isEqualTo(8);
    }

    @Test
    @DisplayName("주어진 위치의 기물이 조회된다.")
    public void findPiece() {
        board.initialize();
        assertThat(board.findPiece(new Position("a8")).getColor()).isEqualTo(Color.BLACK);
        assertThat(board.findPiece(new Position("a8")).getType()).isEqualTo(Type.ROOK);
    }

    @Test
    @DisplayName("임의의 기물을 체스판 위에 추가한다.")
    public void addPieceByPosition() {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook();
        board.move(new Position(position), piece);

        assertThat(board.findPiece(new Position(position))).isEqualTo(piece);
        System.out.println(board.showBoard());
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

        assertThat(board.calculatePoint(Color.BLACK)).isEqualTo(15.0, withPrecision(0.01));
        assertThat(board.calculatePoint(Color.WHITE)).isEqualTo(7.0, withPrecision(0.01));

        System.out.println(board.showBoard());
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

        assertThat(board.calculatePoint(Color.BLACK)).isEqualTo(20, withPrecision(0.01));
        assertThat(board.calculatePoint(Color.WHITE)).isEqualTo(19.5, withPrecision(0.01));

        System.out.println(board.showBoard());
    }
    private void addPiece(String position, Piece piece) {
        board.move(new Position(position), piece);
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

        List<Piece> sortedPieceList = board.pieceListSortedByScoreAsc(Color.WHITE);
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

        List<Piece> sortedPieceList = board.pieceListSortedByScoreDesc(Color.WHITE);
    }

    @Test
    @DisplayName("기물을 현재 위치에서 다른 위치로 이동한다(기물 이동 원칙은 무시)")
    public void move() throws Exception {
        // Given
        board.initialize();

        // When
        String sourcePosition = "b2";
        String targetPosition = "b3";
        board.move(sourcePosition, targetPosition);

        // Then
        assertThat(board.findPiece(new Position(sourcePosition)).getColor()).isEqualTo(Color.NOCOLOR);
        assertThat(board.findPiece(new Position(sourcePosition)).getType()).isEqualTo(Type.NO_PIECE);
        assertThat(board.findPiece(new Position(targetPosition)).getColor()).isEqualTo(Color.WHITE);
        assertThat(board.findPiece(new Position(targetPosition)).getType()).isEqualTo(Type.QUEEN);
    }
}