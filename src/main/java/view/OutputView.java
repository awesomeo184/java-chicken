package view;

import domain.MainMenu;
import domain.Menu;
import domain.Table;

import java.util.ArrayList;
import java.util.List;
import sun.rmi.rmic.Main;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE = "└ ─ ┘";
    private static final String BOTTOM_LINE_WITH_ORDER = "└ $ ┘";
    private static final String RECEIPT_TITLE = "메뉴 수량 금액";

    public static void printMain() {
        System.out.println("## 메인 화면");
        for (MainMenu menu : MainMenu.values()) {
            System.out.println(menu);
        }
        System.out.println();
    }

    public static void printTables(final List<Table> tables) {
        System.out.println("## 테이블 목록");
        final int size = tables.size();
        printLine(TOP_LINE, size);
        printTableNumbers(tables);
        printBottomLine(tables);
        System.out.println();
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
        System.out.println();
    }

    private static void printBottomLine(final List<Table> tables) {
        for (final Table table : tables) {
            if (table.hasOrder()) {
                System.out.print(BOTTOM_LINE_WITH_ORDER);
                continue;
            }
            System.out.print(BOTTOM_LINE);
        }
        System.out.println();
    }

    private static void printLine(final String line, final int count) {
        for (int index = 0; index < count; index++) {
            System.out.print(line);
        }
        System.out.println();
    }

    private static void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }


    public static void printReceipt(Table table) {
        System.out.println("## 주문 내역");
        System.out.println(RECEIPT_TITLE);
        List<Menu> menus = table.orderedMenu();
        List<Integer> amounts = table.orderedAmount();
        for (int i = 0; i < menus.size(); i++) {
            int price = amounts.get(i) * menus.get(i).getPrice();
            System.out.println(menus.get(i).getName()
                + " " + amounts.get(i)
                + " " + price);
        }
        System.out.println();
    }

    public static void printTotalPrice(int totalPrice) {
        System.out.println("## 최종 결제할 금액");
        System.out.println(totalPrice);
        System.out.println();
    }
}
