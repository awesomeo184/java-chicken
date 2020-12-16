package utils;

import domain.MainMenu;
import java.util.Arrays;

public class Validator {

    public static MainMenu checkAndThrowMainMenu(int moduleNumber) {
        return Arrays.stream(MainMenu.values())
            .filter(mainMenu -> mainMenu.getNumber() == moduleNumber)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 선택할 수 없는 기능입니다."));
    }

}
