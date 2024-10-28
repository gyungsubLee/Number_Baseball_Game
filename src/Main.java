import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Lv1.BaseballGame 객체 생성 & 게임 시작
        BaseballGame baseballGame = new BaseballGame(scanner, random);
        baseballGame.play();
    }
}