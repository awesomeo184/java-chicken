package domain;

public enum MainMenu {
    REGISTER_ORDER(1, "주문등록"),
    PAY(2, "결제하기"),
    EXIT(3, "프로그램 종료");

    private final int number;
    private final String name;

    MainMenu(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number + " - " + name;
    }
}
