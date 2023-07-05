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

        public char getWhiteRepresentation() {
            return this.representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(this.representation);
        }
    }

    public static final String PAWN_NAME = "pawn";
    public static final String KNIGHT_NAME = "knight";
    public static final String ROOK_NAME = "rook";
    public static final String BISHOP_NAME = "bishop";
    public static final String QUEEN_NAME = "queen";
    public static final String KING_NAME = "king";

    private Color color;
    private String name;
    private Type type;

    private Piece(Color color, String name, Type type){
        this.color = color;
        this.name = name;
        this.type = type;
    }

    public static Piece createWhitePawn() {
        return new Piece(Color.WHITE, PAWN_NAME, Type.PAWN);
    }

    public static Piece createBlackPawn() {
        return new Piece(Color.BLACK, PAWN_NAME, Type.PAWN);
    }

    public static Piece createWhiteKnight() {
        return new Piece(Color.WHITE, KNIGHT_NAME, Type.KNIGHT);
    }

    public static Piece createBlackKnight() {
        return new Piece(Color.BLACK, KNIGHT_NAME, Type.KNIGHT);
    }

    public static Piece createWhiteRook() {
        return new Piece(Color.WHITE, ROOK_NAME, Type.ROOK);
    }

    public static Piece createBlackRook() {
        return new Piece(Color.BLACK, ROOK_NAME, Type.ROOK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(Color.WHITE, BISHOP_NAME, Type.BISHOP);
    }

    public static Piece createBlackBishop() {
        return new Piece(Color.BLACK, BISHOP_NAME, Type.BISHOP);
    }

    public static Piece createWhiteQueen() {
        return new Piece(Color.WHITE, QUEEN_NAME, Type.QUEEN);
    }

    public static Piece createBlackQueen() {
        return new Piece(Color.BLACK, QUEEN_NAME, Type.QUEEN);
    }

    public static Piece createWhiteKing() {
        return new Piece(Color.WHITE, KING_NAME, Type.KING);
    }

    public static Piece createBlackKing() {
        return new Piece(Color.BLACK, KING_NAME, Type.KING);
    }

    public Color getColor() {
        return this.color;
    }

    public String getName() {
        return this.name;
    }

    public char getRepresentation() {
        if(this.color.equals(Color.WHITE)) {
            return this.type.getWhiteRepresentation();
        }
        else {
            return this.type.getBlackRepresentation();
        }
    }

    // extra features
    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return this.color.equals(Color.WHITE);
    }
}
