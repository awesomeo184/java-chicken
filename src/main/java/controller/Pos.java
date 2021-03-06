package controller;

import domain.MainMenu;
import domain.Menu;
import domain.MenuRepository;
import domain.Table;
import domain.TableRepository;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Pos {
    private static final List<Table> tables = TableRepository.tables();
    private static final List<Menu> menus = MenuRepository.menus();
    private static final String CARD = "1";
    private static final String CASH = "2";

    public void run() {
        while (true) {
            OutputView.printMain();
            MainMenu mainMenu = InputView.inputMainMenu();

            if (mainMenu == MainMenu.EXIT) {
                break;
            }

            if (mainMenu == MainMenu.REGISTER_ORDER) {
                registerOrder();
            }

            if (mainMenu == MainMenu.PAY) {
                pay();
            }
        }
    }

    private void registerOrder() {
        OutputView.printTables(tables);
        Table table = InputView.inputTable();

        OutputView.printMenus(menus);

        Menu menu = InputView.inputMenu();
        int amount = InputView.inputAmount();
        try {
            table.order(menu, amount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    private void pay() {
        OutputView.printTables(tables);
        try {
            Table table = InputView.inputTable();
            if (!table.hasOrder()) {
                throw new IllegalArgumentException("[ERROR] 주문이 없는 테이블입니다.");
            }
            int totalPrice = 0;
            OutputView.printReceipt(table);
            String payType = InputView.getPayType(table);

            if (payType.equals(CARD)) {
                totalPrice = table.chargeWithCard();
            }
            if (payType.equals(CASH)) {
                totalPrice = table.chargeWithCash();
            }

            OutputView.printTotalPrice(totalPrice);
            table.clear();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }


}
