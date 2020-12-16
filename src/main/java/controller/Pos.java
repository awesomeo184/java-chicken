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

    public void run() {
        while (true) {
            OutputView.printMain();
            MainMenu mainMenu = InputView.inputMainMenu();

            if (mainMenu == MainMenu.EXIT) {
                break;
            }
        }
    }

    private void registerOrder() {
        OutputView.printTables(tables);
        Table table = InputView.inputTable();

        OutputView.printMenus(menus);

        Menu menu = InputView.inputMenu();
        int amount = InputView.inputAmount();
        table.order(menu, amount);
    }


}
