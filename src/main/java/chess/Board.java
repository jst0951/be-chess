package chess;

import chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Integer size;
    private List<Pawn> pawnList;

    public Board() {
        this.size = 0;
        this.pawnList = new ArrayList<Pawn>();
    }

    public void add(Pawn pawn) {
        this.pawnList.add(pawn);
        this.size += 1;
    }

    public Integer size() {
        return this.size;
    }

    public Pawn findPawn(Integer idx) {
        return this.pawnList.get(idx);
    }
}
