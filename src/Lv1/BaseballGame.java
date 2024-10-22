package Lv1;

import java.util.*;

public class BaseballGame {

    static int digitCount;
    static int size = 3;

    static StringBuilder result = new StringBuilder();
    static List<Integer>  answer = new ArrayList<>();
    private final Set<Integer>  checkUsedNumberOfAnswer = new HashSet<>();
    private final Set<Integer>  checkUsedNumberOfInupt = new HashSet<>();  // 입력된 숫자를 저장할 Set (중복 방지)

    /**
     *  Answer 생성: 재귀 구현
     *    종료 조건: cnt > size
     */
    private void generateRecursiveAnswer(int cnt, Random random){
        if(cnt > size) return;
        while(true){
            int randomNumber = random.nextInt(9) + 1;
            if(!checkUsedNumberOfAnswer.contains(randomNumber)){
                checkUsedNumberOfAnswer.add(randomNumber);
                answer.add(randomNumber);
                generateRecursiveAnswer(cnt+1, random);
                break;
            }
        }
    }

    public void initializeConditions(Random random){
        digitCount = 1;
        result.setLength(0);
        answer.clear();
        checkUsedNumberOfAnswer.clear();
        checkUsedNumberOfInupt.clear();
    }

    public void play(Scanner sc, Random random){
        initializeConditions(random);

        generateRecursiveAnswer(1, random);

        System.out.println("< 게임을 시작합니다 >");

        // System.out.print("자리수를 입력해 주세요");
        // size = sc.nextInt();

        while (digitCount <= 3) {

            System.out.print("1~9 사이의 숫자를 입력하세요: ");

            String input = sc.next();

            // 입력값 유효성 검사
            if (!isValidRangeByRegex(input)) {
                System.out.println("유효하지 않은 입력입니다. 1~9 사이의 숫자를 입력하세요.");
                continue;
            }

            // 형변환: String -> Integer
            int inputNumber = Integer.parseInt(input);

            // 중복 값 확인
            if (isDuplicate(inputNumber)) {
                System.out.println("이미 입력된 숫자입니다. 다른 숫자를 입력하세요.");
                continue;
            }

            String s = compareAnswerAndInputNumForBaseballResult(digitCount, inputNumber);
            System.out.println("결과: " +digitCount+ " "+ s);

            if("스트라이크".equals(s)){
                result.append(inputNumber);
                if(digitCount==3) {
                    System.out.println("정답: " + result.toString());
                    break;
                }
                checkUsedNumberOfInupt.add(inputNumber);  // check를 위해 Set에 입력값 추가
                digitCount++;
            }
        }
    }


    // 정규식을 사용하여 입력값 검사
        // 유효 범위: 1 ~ 9
    protected boolean isValidRangeByRegex(String inputStr) {
        if (inputStr.isEmpty()) {
            return false;
        }
        return inputStr.matches("^[1-9]$");
    }

    // 중복 검사
    protected boolean isDuplicate(int number) {
        if (checkUsedNumberOfInupt.contains(number)) {
            return true;
        }
        return false;
    }


    // 두 값 비교 ( 값, 자릿수)
        // 1) 자리수, 값 동일       -> "스트라이크"
        // 2) 자리수 다름, 값 동일   -> "볼"
        // 3) 자리수, 값 다름       -> "아웃"
    public String compareAnswerAndInputNumForBaseballResult(int digitCount, int inputNumber){
        if(checkUsedNumberOfAnswer.contains(inputNumber)){
            if(answer.get(digitCount-1) ==  inputNumber) {
                return "스트라이크";
            }
            else return "볼";
        } else return "아웃";
    }


//    private int countStrike(String input) {
//
//    }
//
//    private int countBall(String input) {
//
//    }
}