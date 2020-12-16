package view;

import domain.MainMenu;
import domain.Menu;
import domain.MenuRepository;
import domain.Table;
import domain.TableRepository;
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

    public static Table inputTable() {
        System.out.println("## 테이블을 선택하세요.");
        int tableNumber = Integer.parseInt(getInput());
        try {
            return TableRepository.getTable(tableNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            breakLine();
            return inputTable();
        }
    }

    public static Menu inputMenu() {
        System.out.println("## 등록할 메뉴를 선택하세요.");
        int menuNumber = Integer.parseInt(getInput());
        try {
            return MenuRepository.getMenu(menuNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            breakLine();
            return inputMenu();
        }
    }

    public static int inputAmount() {
        System.out.println("## 메뉴의 수량을 입력하세요.");
        return Integer.parseInt(getInput());
    }

    public static String getInput() {
        return scanner.nextLine().trim();
    }

    private static void breakLine() {
        System.out.println();
    }

    public static String getPayType(Table table) {
        System.out.printf("## %s번 테이블의 결제를 진행합니다. \n", table.getNumber());
        System.out.println("## 신용카드는 1번, 현금 결제는 2번");
        try {
            String payType = getInput();
            if (payType.equals("1") || payType.equals("2")) {
                return payType;
            }
            throw new IllegalArgumentException("올바르지 못한 선택입니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            breakLine();
            return getPayType(table);
        }
    }
}
