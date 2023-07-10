import chess.Board;
import chess.Game;
import chess.Position;
import chess.View;

import static utils.StringUtils.appendNewLine;

import java.util.Scanner;

public class Main {
    private static final String ERROR_MESSAGE_START = "올바르지 않은 입력입니다. 게임을 시작하려면 start를 눌러주세요.";
    private static final String INFO_GAME_START = "게임이 시작되었습니다. 말을 움직여주세요.";
    private static final String ERROR_MESSAGE_MOVE = "올바르지 않은 입력입니다. 'move b2 b3'과 같은 형태를 입력해주세요.";
    private static final String ERROR_MESSAGE_END = "게임을 종료하시려면 end를 눌러주세요.";
    private static final String ERROR_MESSAGE_GAME = appendNewLine(ERROR_MESSAGE_MOVE) + ERROR_MESSAGE_END;
    private static final String INFO_GAME_END = "게임이 종료되었습니다.";

    public static void main(String[] args) {
        // 게임 시작 처리
        waitUntilInputString("start");
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
            if(inputString.equals("end")) {
                System.out.println(INFO_GAME_END);
                break;
            }
            else if(inputString.startsWith("move")) {
                String[] tokens = inputString.split(" ");
                game.move(new Position(tokens[1]), new Position(tokens[2]));
                System.out.println(view.showBoard());
            }
            else {
                System.out.println(ERROR_MESSAGE_GAME);
            }
        }
    }

    private static void waitUntilInputString(String targetStr) {
        Scanner in = new Scanner(System.in);

        while(true) {
            String inputString = in.next();
            if(inputString.equals(targetStr)) {
                System.out.println(INFO_GAME_START);
                break;
            }
            else {
                System.out.println(ERROR_MESSAGE_START);
            }
        }
    }
}