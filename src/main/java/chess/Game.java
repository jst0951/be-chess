package chess;

import chess.pieces.Piece;
import static chess.Board.ROW_CNT;
import static chess.Board.COL_CNT;

import java.util.*;

public class Game {
    public static final double PAWN_SAMEROW_SCORE = 0.5;

    private final Board board;
    private final List<Piece> pieceList;

    public Game(Board board) {
        this.board = board;
        this.pieceList = board.getPieceList();
    }

    public void move(Position source, Position target) {
        if (isMovable(source, target)) {
            board.movePiece(source, target);
        }
    }

    public boolean isMovable(Position source, Position target) {
        // 원본 좌표가 벗어나지 않는지 계산
        if(source.getXPos() < 0 || source.getYPos() < 0) {
            return false;
        }
        // 목표 좌표가 벗어나지 않는지 계산
        if(target.getXPos() >= COL_CNT || target.getYPos() >= ROW_CNT) {
            return false;
        }

        // 이동하려는 위치에 같은 편의 기물이 있는지 확인
        if(pieceList.get(target.getListIdx()).getColor() == pieceList.get(source.getListIdx()).getColor()) {
            return false;
        }

        // 각 기물이 행할 수 있는 움직임인지 판별
        Piece piece = pieceList.get(source.getListIdx());
        if(!piece.verifyMovePosition(source, target)) {
            return false;
        }

        return true;
    }

    public double calculatePoint(Piece.Color color) {
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
    private int[] countPawn(Piece.Color color) {
        // 1. 각 열별 pawn의 개수를 저장할 list를 생성한다.
        int[] pawnCntList = new int[COL_CNT];
        Arrays.fill(pawnCntList, 0);
        // 2. 각 pawn의 위치에 따라 list에 저장한다.
        for(int idx = 0; idx < ROW_CNT * COL_CNT; idx++) {
            if(pieceList.get(idx).getColor() == color && pieceList.get(idx).getType() == Piece.Type.PAWN) {
                int xPos = idx % 8;
                pawnCntList[xPos] += 1;
            }
        }

        return pawnCntList;
    }

    public List<Piece> pieceListSortedByScoreAsc(Piece.Color color) {
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
            if(pieceList.get(idx).getColor() == color && pieceList.get(idx).getType() == Piece.Type.PAWN) {
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
    public List<Piece> pieceListSortedByScoreDesc(Piece.Color color) {
        List<Piece> sorted = pieceListSortedByScoreAsc(color);
        Collections.reverse(sorted);

        return sorted;
    }
}
