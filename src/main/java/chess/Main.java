package chess;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 게임 시작 처리
        waitUntilInputString("start");
        // 게임 판 출력
        Board board = new Board();
        board.initialize();
        System.out.println(board.showBoard());
        // 게임 종료 처리
        waitUntilInputString("end");
    }

    public static void waitUntilInputString(String targetStr) {
        Scanner in = new Scanner(System.in);
        final String errorMessage = "올바르지 않은 입력입니다. 게임을 시작하려면 start, 종료하려면 end를 눌러주세요.";

        while(true) {
            String str = in.next();
            if(str.equals(targetStr)) {
                break;
            }
            else {
                System.out.println(errorMessage);
            }
        }
    }
}