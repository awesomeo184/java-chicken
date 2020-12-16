package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {

    private static final int MAX_AMOUNT = 99;
    private static final double CASH_DISCOUNT = 0.95;
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
        orders.put(menu, orders.getOrDefault(menu, 0) + amount);
    }

    public void checkMaxAmount(int amount) {
        int currentAmount = orders.values().stream().reduce(0, Integer::sum);
        if (currentAmount + amount > MAX_AMOUNT) {
            throw new IllegalArgumentException("[ERROR 주문할 수 있는 최대 수량은 99개 입니다.");
        }
    }

    public boolean hasOrder() {
        return orders.size() != 0;
    }

    public List<Menu> orderedMenu() {
        return new ArrayList<>(orders.keySet());
    }

    public List<Integer> orderedAmount() {
        return new ArrayList<>(orders.values());
    }


    @Override
    public String toString() {
        return Integer.toString(number);
    }

    public int chargeWithCard() {
        return getPrice() - chickenDiscount();
    }

    public int chargeWithCash() {
        return (int) ((getPrice() - chickenDiscount()) * CASH_DISCOUNT);
    }

    private int chickenDiscount() {
        int chickenCount = 0;
        List<Menu> menus = orderedMenu();
        List<Integer> amounts = orderedAmount();
        for (int i = 0; i < menus.size(); i++) {
            if (menus.get(i).getCategory() == Category.CHICKEN) {
                chickenCount += amounts.get(i);
            }
        }
        if (chickenCount == 0) {
            return 0;
        }
        return (chickenCount / 10) * 10_000;
    }

    private int getPrice() {
        int price = 0;
        List<Menu> menus = orderedMenu();
        List<Integer> amounts = orderedAmount();
        for (int i = 0; i < menus.size(); i++) {
            price += amounts.get(i) * menus.get(i).getPrice();
        }
        return price;
    }

    public void clear() {
        orders.clear();
    }
}
