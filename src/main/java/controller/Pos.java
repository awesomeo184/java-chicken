package controller;

import domain.MainMenu;
import view.InputView;
import view.OutputView;

public class Pos {

    public void run() {
        while (true) {
            OutputView.printMain();
            MainMenu mainMenu = InputView.inputMainMenu();

            if (mainMenu == MainMenu.EXIT) {
                break;
            }
        }
    }


}
