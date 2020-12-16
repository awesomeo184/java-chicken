package view;

import domain.MainMenu;
import java.util.Scanner;
import utils.Validator;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static MainMenu inputMainMenu() {
        System.out.println("## 원하는 기능을 선택하세요.");
        int moduleNumber = Integer.parseInt(getInput());
        try {
            breakLine();
            return Validator.checkAndThrowMainMenu(moduleNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            breakLine();
            return inputMainMenu();
        }
    }

    public static int inputTableNumber() {
        System.out.println("## 주문할 테이블을 선택하세요.");
        return Integer.parseInt(getInput());
    }

    public static String getInput() {
        return scanner.nextLine().trim();
    }

    private static void breakLine() {
        System.out.println();
    }
}
