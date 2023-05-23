package ru.job4j.ood.lsp.wrongdesign;

/**
 * Нет необходимости отправлять задачу самому себе. Приходится не реализовывать метод
 */
public class HomeTaskManager implements TaskManager {

    @Override
    public void addTask(Task task) {

    }

    @Override
    public Task getTask(int id) {
        return null;
    }

    @Override
    public boolean setResponsible(Task task, String responsible) {
        return false;
    }

    @Override
    public boolean setComplete(Task task) {
        return false;
    }

    @Override
    public void sendStatusToReporter(Task task) {
        throw new UnsupportedOperationException();
    }
}
