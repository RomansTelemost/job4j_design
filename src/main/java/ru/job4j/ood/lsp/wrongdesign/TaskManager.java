package ru.job4j.ood.lsp.wrongdesign;

public interface TaskManager {

    void addTask(Task task);
    Task getTask(int id);
    boolean setResponsible(Task task, String responsible);
    boolean setComplete(Task task);
}
