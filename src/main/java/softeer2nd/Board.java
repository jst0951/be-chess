package softeer2nd;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Integer size;
    private List<Pawn> pawnList;

    public Board() {
        size = 0;
        pawnList = new ArrayList<Pawn>();
    }

    public void add(Pawn pawn) {
        pawnList.add(pawn);
        size += 1;
    }

    public Integer size() {
        return size;
    }

    public Pawn findPawn(Integer idx) {
        return pawnList.get(idx);
    }
}
