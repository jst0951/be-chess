package chess.pieces;

import chess.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Piece {
    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }
    public enum Type {
        PAWN('p', 1.0),
        ROOK('r', 5.0),
        KNIGHT('n', 2.5),
        BISHOP('b', 3.0),
        QUEEN('q', 9.0),
        KING('k', 0.0),
        NO_PIECE('.', 0.0);

        private final char representation;
        private final double defaultPoint;
        Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }

        public char getWhiteRepresentation() {
            return this.representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(this.representation);
        }

        public double getDefaultPoint() {
            return this.defaultPoint;
        }
    }
    public enum Direction {
        EAST(1, 0),
        WEST(-1, 0),
        SOUTH(0, 1),
        NORTH(0, -1),
        NORTHEAST(1, -1),
        SOUTHEAST(1, 1),
        SOUTHWEST(-1, 1),
        NORTHWEST(-1, -1),

        NNE(1, -2),
        NNW(-1, -2),
        SSE(1, 2),
        SSW(-1, 2),
        EEN(2, -1),
        EES(2, 1),
        WWN(-2, -1),
        WWS(-2, 1);


        private final int xDegree;
        private final int yDegree;

        private Direction(int xDegree, int yDegree) {
            this.xDegree = xDegree;
            this.yDegree = yDegree;
        }

        public int getXDegree() {
            return this.xDegree;
        }

        public int getYDegree() {
            return this.yDegree;
        }

        public static List<Direction> linearDirection() {
            return Arrays.asList(EAST, WEST, SOUTH, NORTH);
        }

        public static List<Direction> diagonalDirection() {
            return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
        }

        public static List<Direction> everyDirection() {
            return Arrays.asList(EAST, WEST, SOUTH, NORTH, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
        }

        public static List<Direction> knightDirection() {
            return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
        }

        public static List<Direction> whitePawnDirection() {
            return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
        }

        public static List<Direction> blackPawnDirection() {
            return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
        }
    }

    private final Color color;
    private final Type type;
    private final List<Direction> directionList;

    private Piece(Color color, Type type){
        this.color = color;
        this.type = type;
        this.directionList = getDirectionList(color, type);
    }
    private List<Direction> getDirectionList(Color color, Type type) {
        if(type == Type.KING) {
            return Direction.everyDirection();
        }
        if(type == Type.ROOK) {
            return Direction.linearDirection();
        }
        if(type == Type.BISHOP) {
            return Direction.diagonalDirection();
        }
        if(type == Type.QUEEN) {
            return Direction.everyDirection();
        }
        if(type == Type.KNIGHT) {
            return Direction.knightDirection();
        }
        if(type == Type.PAWN) {
            if(color == Color.WHITE) {
                return Direction.whitePawnDirection();
            }
            else{
                return Direction.blackPawnDirection();
            }
        }

        return new ArrayList<>();
    }

    public static Piece createBlank() {
        return new Piece(Color.NOCOLOR, Type.NO_PIECE);
    }
    private static Piece createWhite(Type type) {
        return new Piece(Color.WHITE, type);
    }
    private static Piece createBlack(Type type) {
        return new Piece(Color.BLACK, type);
    }
    public static Piece createWhitePawn() {
        return createWhite(Type.PAWN);
    }
    public static Piece createBlackPawn() {
        return createBlack(Type.PAWN);
    }
    public static Piece createWhiteKnight() {
        return createWhite(Type.KNIGHT);
    }
    public static Piece createBlackKnight() {
        return createBlack(Type.KNIGHT);
    }
    public static Piece createWhiteRook() {
        return createWhite(Type.ROOK);
    }
    public static Piece createBlackRook() {
        return createBlack(Type.ROOK);
    }
    public static Piece createWhiteBishop() {
        return createWhite(Type.BISHOP);
    }
    public static Piece createBlackBishop() {
        return createBlack(Type.BISHOP);
    }
    public static Piece createWhiteQueen() {
        return createWhite(Type.QUEEN);
    }
    public static Piece createBlackQueen() {
        return createBlack(Type.QUEEN);
    }
    public static Piece createWhiteKing() {
        return createWhite(Type.KING);
    }
    public static Piece createBlackKing() {
        return createBlack(Type.KING);
    }

    public Color getColor() {
        return this.color;
    }
    public Type getType() {
        return this.type;
    }
    public List<Direction> getDirectionList() {
        return this.directionList;
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
    public boolean isEmpty() {
        return this.color.equals(Color.NOCOLOR);
    }

    public boolean isDirectionAvailable(int xDegree, int yDegree) {
        int xDegreeFlat = flatten(xDegree);
        int yDegreeFlat = flatten(yDegree);

        for(Direction direction: this.directionList) {
            switch (this.type) {
                // 정해진 칸으로만 이동 가능한 경우
                case KING: case KNIGHT: case PAWN:
                    if(direction.getXDegree() == xDegree && direction.getYDegree() == yDegree) {
                        return true;
                    }
                    break;
                // 칸수 제한 없이 이동 가능한 경우
                case ROOK: case BISHOP: case QUEEN:
                    if(direction.getXDegree() == xDegreeFlat && direction.getYDegree() == yDegreeFlat) {
                        return true;
                    }
                    break;
                default:
            }
        }

        return false;
    }
    private int flatten(int degree) {
        if(degree != 0) {
            degree /= Math.abs(degree);
        }
        return degree;
    }

}
