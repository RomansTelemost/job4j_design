package ru.job4j.ood.ocp.wrongdesign;

/**
 * Нарушение OCP:
 * - Используем конкретную реализцию BankStore, а не интерфейс.
 * - Используем конкретную реализацию в методе isBankAccountBanned(). Хотя должен использоваться созданный экземпляр. Скорее всего синглтон.
 * у данного сервиса и будем спрашивать. Или же добавили бы свойство в класс BankAccount "boolean enabled".
 * - Переменные класса BankAccount публичны. Т.е. открыто для изменения.
 */
public class BankAccount {

    public User user;

    public BankStore bankStore;

    public BankAccount(User user, BankStore bankStore) {
        this.user = user;
        this.bankStore = bankStore;
    }

    public void setAmount(long amount) {
        bankStore.setAmount(user, amount);
    }

    public long getAmount() {
        return bankStore.getAmount(user);
    }

    public boolean isBankAccountBanned() {
        return new BankService().isBankAccountBanned(this);
    }
}
