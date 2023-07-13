import chess.Board;
import chess.Game;
import chess.Position;
import chess.View;

import static utils.StringUtils.appendNewLine;

import java.util.Scanner;

public class Main {
    private static final String COMMAND_GAME_START = "start";
    private static final String COMMAND_GAME_END = "end";
    private static final String COMMAND_MOVE = "move";
    private static final String ERROR_MESSAGE_START = "올바르지 않은 입력입니다. 게임을 시작하려면 start를 눌러주세요.";
    private static final String INFO_GAME_START = "게임이 시작되었습니다. 말을 움직여주세요.";
    private static final String ERROR_MESSAGE_RETYPE = "다시 입력해주세요.";
    private static final String ERROR_MESSAGE_MOVE = "올바르지 않은 입력입니다. 'move b2 b3'과 같은 형태를 입력해주세요.";
    private static final String ERROR_MESSAGE_END = "게임을 종료하시려면 end를 눌러주세요.";
    private static final String ERROR_MESSAGE_GAME = appendNewLine(ERROR_MESSAGE_MOVE) + ERROR_MESSAGE_END;
    private static final String INFO_GAME_END = "게임이 종료되었습니다.";

    public static void main(String[] args) {
        // 게임 시작 처리
        waitUntilInput();
        // 게임 진행
        playGame();
    }

    private static void playGame() {
        Board board = new Board();
        Game game = new Game(board);
        View view = new View(board);

        board.initialize();
        System.out.println(view.showBoard());

        Scanner in = new Scanner(System.in);

        while(true) {
            String inputString = in.nextLine();
            if(inputString.equals(COMMAND_GAME_END)) {
                System.out.println(INFO_GAME_END);
                break;
            }
            if(inputString.startsWith(COMMAND_MOVE)) {
                String[] tokens = inputString.split(" ");
                try {
                    game.move(new Position(tokens[1]), new Position(tokens[2]));
                }
                catch(RuntimeException e) {
                    System.out.println(e.getMessage());
                    System.out.println(ERROR_MESSAGE_RETYPE);
                }
                System.out.println(view.showBoard());
            }
            else {
                System.out.println(ERROR_MESSAGE_GAME);
            }
        }
    }

    private static void waitUntilInput() {
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
}