package Lv1;

import java.util.*;

public class Main {

    // 추가 계산 여부 판단 메서드
    public static boolean shouldContinue(Scanner sc) {
        System.out.println("계임을 계속 하시겠습니까?  ( 계속하려면 'yes' 입력 )" );
        String isContinueGame = sc.next();
        return "yes".equalsIgnoreCase(isContinueGame);  // 대소문자 구분 X
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StringBuilder result = new StringBuilder();  // 최종 3자리 숫자를 저장할 StringBuilder
        Random random = new Random();

        // Lv1.BaseballGame 객체 생성 & 게임 시작
        BaseballGame baseballGame = new BaseballGame();
        baseballGame.play(scanner, random);


        while(shouldContinue(scanner)){
            baseballGame.play(scanner, random);
        }

    }
}