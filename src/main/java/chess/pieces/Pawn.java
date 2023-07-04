package chess.pieces;

public class Pawn {
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";

    public static final Character WHITE_REPRESENTATION = 'p';
    public static final Character BLACK_REPRESENTATION = 'P';

    private String color;
    private Character representation;

    public Pawn(){
        this.color = WHITE_COLOR;
        this.representation = WHITE_REPRESENTATION;
    }

    public Pawn(final String color) {
        this.color = color;
        if(color.equals(WHITE_COLOR)) {
            this.representation = WHITE_REPRESENTATION;
        }
        else {
            this.representation = BLACK_REPRESENTATION;
        }
    }

    public Pawn(final String color, final char representation) {
        this.color = color;
        this.representation = representation;
    }

    public String getColor() {
        return this.color;
    }

    public Character getRepresentation() {
        return this.representation;
    }

}
