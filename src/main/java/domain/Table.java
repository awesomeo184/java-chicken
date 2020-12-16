package domain;

import java.util.HashMap;
import java.util.Map;

public class Table {

    private static final int MAX_AMOUNT = 99;
    private final int number;
    private final Map<Menu, Integer> orders = new HashMap<>();

    public Table(final int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void order(Menu menu, int amount) {
        checkMaxAmount(amount);
        orders.put(menu, orders.getOrDefault(menu, amount) + amount);
    }

    public void checkMaxAmount(int amount) {
        int currentAmount = orders.values().stream().reduce(0, Integer::sum);
        if (currentAmount + amount >= MAX_AMOUNT) {
            throw new IllegalArgumentException("[ERROR 주문할 수 있는 최대 수량은 99개 입니다.");
        }
    }

    public boolean hasOrder() {
        return orders.size() != 0;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
