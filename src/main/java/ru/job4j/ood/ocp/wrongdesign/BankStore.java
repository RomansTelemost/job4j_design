package ru.job4j.ood.ocp.wrongdesign;

public class BankStore {

    private long amount;

    public void setAmount(User user, long amount) {
        this.amount = amount;
    }

    public long getAmount(User user) {
        return findAmountByUser(user);
    }

    public long findAmountByUser(User user) {
        /**
         * From DB
         */
        return 100;
    }

}
