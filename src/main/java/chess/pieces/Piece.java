package chess.pieces;

public class Piece {
    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('p'),
        ROOK('r'),
        KNIGHT('n'),
        BISHOP('b'),
        QUEEN('q'),
        KING('k'),
        NO_PIECE('.');

        private char representation;
        Type(char representation) {
            this.representation = representation;
        }
    }

    public static final String PAWN_NAME = "pawn";
    public static final String KNIGHT_NAME = "knight";
    public static final String ROOK_NAME = "rook";
    public static final String BISHOP_NAME = "bishop";
    public static final String QUEEN_NAME = "queen";
    public static final String KING_NAME = "king";

    private String color;
    private String name;
    private char representation;

    private Piece(String color, String name, char representation){
        this.color = color;
        this.name = name;
        this.representation = representation;
    }

    public static Piece createWhitePawn() {
        return new Piece(WHITE_COLOR, PAWN_NAME, WHITE_PAWN_REPRESENTATION);
    }

    public static Piece createBlackPawn() {
        return new Piece(BLACK_COLOR, PAWN_NAME, BLACK_PAWN_REPRESENTATION);
    }

    public static Piece createWhiteKnight() {
        return new Piece(WHITE_COLOR, KNIGHT_NAME, WHITE_KNIGHT_REPRESENTATION);
    }

    public static Piece createBlackKnight() {
        return new Piece(BLACK_COLOR, KNIGHT_NAME, BLACK_KNIGHT_REPRESENTATION);
    }

    public static Piece createWhiteRook() {
        return new Piece(WHITE_COLOR, ROOK_NAME, WHITE_ROOK_REPRESENTATION);
    }

    public static Piece createBlackRook() {
        return new Piece(BLACK_COLOR, ROOK_NAME, BLACK_ROOK_REPRESENTATION);
    }

    public static Piece createWhiteBishop() {
        return new Piece(WHITE_COLOR, BISHOP_NAME, WHITE_BISHOP_REPRESENTATION);
    }

    public static Piece createBlackBishop() {
        return new Piece(BLACK_COLOR, BISHOP_NAME, BLACK_BISHOP_REPRESENTATION);
    }

    public static Piece createWhiteQueen() {
        return new Piece(WHITE_COLOR, QUEEN_NAME, WHITE_QUEEN_REPRESENTATION);
    }

    public static Piece createBlackQueen() {
        return new Piece(BLACK_COLOR, QUEEN_NAME, BLACK_QUEEN_REPRESENTATION);
    }

    public static Piece createWhiteKing() {
        return new Piece(WHITE_COLOR, KING_NAME, WHITE_KING_REPRESENTATION);
    }

    public static Piece createBlackKing() {
        return new Piece(BLACK_COLOR, KING_NAME, BLACK_KING_REPRESENTATION);
    }

    public String getColor() {
        return this.color;
    }

    public String getName() {
        return this.name;
    }

    public char getRepresentation() {
        return this.representation;
    }

    // extra features
    public boolean isBlack() {
        return this.color.equals(BLACK_COLOR);
    }

    public boolean isWhite() {
        return this.color.equals(WHITE_COLOR);
    }
}
