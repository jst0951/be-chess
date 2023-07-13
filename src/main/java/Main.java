import chess.Board;
import chess.Game;
import chess.Position;
import chess.View;
import static chess.pieces.Piece.Color;

import static utils.StringUtils.appendNewLine;

import java.util.Scanner;

public class Main {
    private static final String COMMAND_GAME_START = "start";
    private static final String COMMAND_GAME_END = "end";
    private static final String COMMAND_MOVE = "move";
    private static final String INFO_START_PENDING = "체스 게임에 오신 것을 환영합니다. 게임을 시작하려면 start를 입력해주세요.";
    private static final String ERROR_MESSAGE_START = "올바르지 않은 입력입니다. 게임을 시작하려면 start를 입력해주세요.";
    private static final String INFO_GAME_START = "게임이 시작되었습니다. 'move b2 b3'와 같이 입력하여 말을 움직여주세요.";
    private static final String INFO_TURN = "현재 %s의 턴입니다.(흑색이 대문자, 백색이 소문자입니다.)";
    private static final String ERROR_MESSAGE_RETYPE = "다시 입력해주세요.";
    private static final String ERROR_MESSAGE_MOVE = "올바르지 않은 입력입니다. 'move b2 b3'과 같은 형태를 입력해주세요.";
    private static final String ERROR_MESSAGE_END = "게임을 종료하시려면 end를 눌러주세요.";
    private static final String ERROR_MESSAGE_GAME = appendNewLine(ERROR_MESSAGE_MOVE) + ERROR_MESSAGE_END;
    private static final String INFO_GAME_END = "게임이 종료되었습니다.";
    private static final String INFO_TOTAL_SCORE = "백색의 점수 : %.1f점, 흑색의 점수 : %.1f점";
    private static final String INFO_WINNER = "승자 : %s";
    private static final String INFO_DRAW = "무승부입니다.";

    public static void main(String[] args) {
        // 게임 시작 처리
        waitUntilInput();
        // 게임 진행
        playGame();
    }

    private static void waitUntilInput() {
        System.out.println(INFO_START_PENDING);
        Scanner in = new Scanner(System.in);

        while(true) {
            String inputString = in.next();
            if(inputString.equals(COMMAND_GAME_START)) {
                System.out.println(INFO_GAME_START);
                break;
            }
            System.out.println(ERROR_MESSAGE_START);
        }
    }

    private static void playGame() {
        Board board = new Board();
        Game game = new Game(board);
        View view = new View(board);

        board.initialize();
        printBoard(game, view);

        Scanner in = new Scanner(System.in);

        while(true) {
            String inputString = in.nextLine();
            if(inputString.equals(COMMAND_GAME_END)) {
                System.out.println(INFO_GAME_END);
                printScore(game);
                break;
            }
            if(inputString.startsWith(COMMAND_MOVE)) {
                logicMove(inputString, game, view);
            }
            else {
                System.out.println(ERROR_MESSAGE_GAME);
            }
        }
    }

    private static void logicMove(String inputString, Game game, View view) {
        try {
            String[] tokens = inputString.split(" ");
            // 입력 형태 검증
            if (tokens.length != 3) {
                throw new IllegalArgumentException(ERROR_MESSAGE_MOVE);
            }
            // 기물 이동
            game.move(new Position(tokens[1]), new Position(tokens[2]));

            printBoard(game, view);
        }
        catch (IllegalArgumentException e) {
            printExceptionMessage(e);
        }
    }
    private static void printBoard(Game game, View view) {
        String colorInfo = String.format(INFO_TURN, game.getTurn().getColorName());
        System.out.println(colorInfo);
        System.out.println(view.showBoard());
    }
    private static void printScore(Game game) {
        double scoreWhite = game.calculatePoint(Color.WHITE);
        double scoreBlack = game.calculatePoint(Color.BLACK);
        String totalScore = String.format(INFO_TOTAL_SCORE, scoreWhite, scoreBlack);
        System.out.println(totalScore);

        String winner;
        if(scoreWhite > scoreBlack) {
            winner = String.format(INFO_WINNER, Color.WHITE.getColorName());
        }
        else if(scoreWhite < scoreBlack) {
            winner = String.format(INFO_WINNER, Color.BLACK.getColorName());
        }
        else {
            winner = INFO_DRAW;
        }
        System.out.println(winner);
    }
    private static void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
        System.out.println(ERROR_MESSAGE_RETYPE);
    }

}