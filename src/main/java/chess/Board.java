package chess;

import chess.pieces.Piece;

import java.util.*;

import static utils.StringUtils.appendNewLine;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;

public class Board {
    public static final int ROW_CNT = 8;
    public static final int COL_CNT = 8;
    public static final double PAWN_SAMEROW_SCORE = 0.5;

    private final List<Piece> pieceList;
    private final View view;

    public Board() {
        this.pieceList = new ArrayList<>();
        this.view = new View(pieceList);
    }

    public void addPiece(Piece piece) {
        this.pieceList.add(piece);
    }

    public Piece findPiece(int idx) {
        return this.pieceList.get(idx);
    }
    public Piece findPiece(Position position) {
        return pieceList.get(position.getListIdx());
    }

    public void initialize() {
        int i;
        // 흑색 기물(폰 제외) 셋팅
        addPiece(Piece.createBlackRook());
        addPiece(Piece.createBlackKnight());
        addPiece(Piece.createBlackBishop());
        addPiece(Piece.createBlackQueen());
        addPiece(Piece.createBlackKing());
        addPiece(Piece.createBlackBishop());
        addPiece(Piece.createBlackKnight());
        addPiece(Piece.createBlackRook());
        // 검은색 폰 셋팅
        for (i = 0; i < COL_CNT; i++) {
            addPiece(Piece.createBlackPawn());
        }
        // 빈 칸 4줄 셋팅
        addBlankRow();
        addBlankRow();
        addBlankRow();
        addBlankRow();
        // 흰색 폰 셋팅
        for (i = 0; i < COL_CNT; i++) {
            addPiece(Piece.createWhitePawn());
        }
        // 백색 기물(폰 제외) 셋팅
        addPiece(Piece.createWhiteRook());
        addPiece(Piece.createWhiteKnight());
        addPiece(Piece.createWhiteBishop());
        addPiece(Piece.createWhiteQueen());
        addPiece(Piece.createWhiteKing());
        addPiece(Piece.createWhiteBishop());
        addPiece(Piece.createWhiteKnight());
        addPiece(Piece.createWhiteRook());
    }
    public void initializeEmpty() {
        for(int i = 0; i < ROW_CNT; i++) {
            addBlankRow();
        }
    }
    public void addBlankRow() {
        for(int i = 0; i < COL_CNT; i++) {
            addPiece(Piece.createBlank());
        }
    }

    public int size() {
        return pieceList.size();
    }

    public String showBoard() {
        return view.showBoard();
    }

    public int pieceCount() {
        int pCnt = 0;
        for(int i = 0; i < ROW_CNT * COL_CNT; i++) {
            if(this.pieceList.get(i).getType() != Type.NO_PIECE) {
                pCnt++;
            }
        }

        return pCnt;
    }

    public int pieceCount(Color color, Type type) {
        int pCnt = 0;
        for(Piece piece: pieceList) {
            if(piece.getColor() == color && piece.getType() == type) {
                pCnt ++;
            }
        }

        return pCnt;
    }

    public void move(Position position, Piece piece) {
        this.pieceList.set(position.getListIdx(), piece);
    }

    public double calculatePoint(Color color) {
        List<Piece> nonBlankPieceList = new ArrayList<>();
        for(Piece piece: pieceList) {
            if(piece.getColor() == color) { // 주어진 색인 경우에만 추가
                nonBlankPieceList.add(piece);
            }
        }

        // 우선 모든 기물들의 기본 점수를 더한다.
        double score = 0;
        for(Piece piece: nonBlankPieceList) {
            score += piece.getType().getDefaultPoint();
        }

        // 각 열별 pawn의 개수을 구하고, 각 목록의 길이에 따라 점수를 차감한다.
        int[] pawnCntList = countPawn(color);
        // 점수를 차감한다.
        for(int i = 0; i < COL_CNT; i++) {
            if(pawnCntList[i] > 1) {
                score -= (pawnCntList[i] * PAWN_SAMEROW_SCORE);
            }
        }

        return score;
    }

    private int[] countPawn(Color color) {
        // 1. 각 열별 pawn의 개수를 저장할 list를 생성한다.
        int[] pawnCntList = new int[COL_CNT];
        Arrays.fill(pawnCntList, 0);
        // 2. 각 pawn의 위치에 따라 list에 저장한다.
        for(int idx = 0; idx < ROW_CNT * COL_CNT; idx++) {
            if(pieceList.get(idx).getColor() == color && pieceList.get(idx).getType() == Type.PAWN) {
                int xPos = idx % 8;
                pawnCntList[xPos] += 1;
            }
        }

        return pawnCntList;
    }

    public List<Piece> pieceListSortedByScoreAsc(Color color) {
        // 1. 각 piece에 대해 기본 점수로 Hashmap을 만든다.
        Map<Piece, Double> scoreMap = new HashMap<>();
        for(Piece piece: pieceList) {
            if(piece.getColor() == color) {
                scoreMap.put(piece, piece.getType().getDefaultPoint());
            }
        }
        // 2. pawn이 같은 세로줄에 있는 경우, 0.5점을 넣는다.
        int[] pawnCntList = countPawn(color);
        for(int idx = 0; idx < ROW_CNT * COL_CNT; idx++) {
            if(pieceList.get(idx).getColor() == color && pieceList.get(idx).getType() == Type.PAWN) {
                int xPos = idx % 8;
                if(pawnCntList[xPos] > 1) {
                    scoreMap.put(pieceList.get(idx), PAWN_SAMEROW_SCORE);
                }
            }
        }

        // 3. scoreMap의 value 기준으로 정렬한다.
        List<Piece> keySet = new ArrayList<>(scoreMap.keySet());
        // 오름차순 정렬
        keySet.sort(Comparator.comparing(scoreMap::get));

        return keySet;
    }

    public List<Piece> pieceListSortedByScoreDesc(Color color) {
        List<Piece> sorted = pieceListSortedByScoreAsc(color);
        Collections.reverse(sorted);

        return sorted;
    }

    public void move(Position sourcePosition, Position targetPosition) {
        Piece piece = findPiece(sourcePosition);

        this.pieceList.set(sourcePosition.getListIdx(), Piece.createBlank());
        this.pieceList.set(targetPosition.getListIdx(), piece);
    }
}
