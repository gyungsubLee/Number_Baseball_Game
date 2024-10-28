import java.util.*;

public class BaseballGame {

    private Scanner sc;
    private Random random;

    private  int size = 3;
    private boolean continueProgram = true;

    private final List<Integer>  answer = new ArrayList<>();
    private final Set<Integer>  checkUsedNumberOfAnswer = new HashSet<>();


    public BaseballGame(Scanner sc , Random random) {
        this.sc = sc;
        this.random = random;
    }

    /**
     *  Answer 생성: 재귀 구현
     *    종료 조건: cnt > size
     */
    private void generateRecursiveAnswer(int cnt){
        if(cnt > size) return;
        while(true){
            int randomNumber = random.nextInt(9) + 1;
            if(!checkUsedNumberOfAnswer.contains(randomNumber)){
                checkUsedNumberOfAnswer.add(randomNumber);
                answer.add(randomNumber);
                generateRecursiveAnswer(cnt+1);
                break;
            }
        }
    }

    private boolean validateInput(String input) {
        //  숫자만 포함되어 있는지 확인
        if (!input.matches("\\d+")) {
            System.out.println("올바르지 않은 입력값입니다. (숫자만 입력해주세요.)");
            return false;
        }

        // 3자리 수인지 확인
        if (input.length() != 3) {
            System.out.println("올바르지 않은 입력값입니다. (3자리 수가 아닙니다.)");
            return false;
        }

        // 중복된 숫자가 없는지 확인
        Set<Character> uniqueDigits = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (!uniqueDigits.add(c)) { // 중복된 숫자가 있으면 false 반환
                System.out.println("올바르지 않은 입력값입니다. (중복된 숫자가 있습니다.)");
                return false;
            }
        }
        return true;
    }

    private void handleMenuChoice(int menuChoice) {
        switch (menuChoice) {
            case 1:
                startGame();
                break;
            case 2:
                showGameRecords();
                break;
            case 3:
                exitGame();
                break;
            default:
                System.out.println("잘못된 선택입니다. 다시 시도해주세요.");
                break;
        }
    }


    // 두 값 비교 ( 값, 자릿수)
    // 1) 자리수, 값 동일       -> "스트라이크"
    // 2) 자리수 다름, 값 동일   -> "볼"
    // 3) 자리수, 값 다름       -> "아웃"
    private BaseballEventEnum compareAnswerAndInputNumForBaseballResult(int digit, int inputNumber){
        if(checkUsedNumberOfAnswer.contains(inputNumber)){
            if(answer.get(digit) ==  inputNumber) return  BaseballEventEnum.STRIKE;
            else return BaseballEventEnum.BALL;
        } else return BaseballEventEnum.OUT;
    }

    // 초기 값 설정
    private void initCondition(){
        checkUsedNumberOfAnswer.clear();
        answer.clear();
        generateRecursiveAnswer(1);
    }

    // 게임 시작
    private void startGame(){
        initCondition();

//        System.out.println(answer.toString()); 정답 확인
        System.out.println("< 게임을 시작합니다 >");

        int strikeCnt = 0, ballCnt = 0, outCnt = 0;
        while(strikeCnt<3) {
            String inputStr = sc.next();

            while(!validateInput(inputStr)){
                inputStr = sc.next();
            }

            for (int i = 0; i < inputStr.length(); i++) {
                int inputNumber = Character.getNumericValue(inputStr.charAt(i));
                BaseballEventEnum result = compareAnswerAndInputNumForBaseballResult(i, inputNumber);

                if((BaseballEventEnum.STRIKE).equals(result)) {
                    strikeCnt++;
                } else if ((BaseballEventEnum.BALL).equals(result)){
                    ballCnt++;
                } else if ((BaseballEventEnum.OUT).equals(result)) {
                    outCnt++;
                }
            }

            if ( strikeCnt == 3){
                System.out.println("3 스트라이크! 정답입니다.");
                System.out.println();
                break;
            }

            System.out.println("스트라이크: " + strikeCnt + ", 볼: " + ballCnt +", 아웃: " + outCnt);

            strikeCnt =0;
            ballCnt=0;
            outCnt=0;
        }
    }

    private void exitGame() {
        System.out.println("게임을 종료하시겠습니까? (Y/N)");
        String response = sc.next();
        if (response.equalsIgnoreCase("Y")) {
            continueProgram = false; // 프로그램 종료
        } else {
            System.out.println("메뉴로 돌아갑니다.");
        }
    }

    // TODO
    private void showGameRecords() {
    }

    public void play(){

        while (continueProgram) {
            System.out.println("환영합니다! 원하시는 번호를 입력해주세요");

            int menuChoice;
            do {
                System.out.println("1. 게임 시작하기 2. 게임 기록보기 3. 종료하기");
                menuChoice = sc.nextInt();
                handleMenuChoice(menuChoice);
            } while (menuChoice < 1 || menuChoice > 3);
        }

        System.out.println("프로그램이 종료되었습니다.");
    }
}