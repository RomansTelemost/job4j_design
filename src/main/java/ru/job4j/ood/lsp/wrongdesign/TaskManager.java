package ru.job4j.ood.lsp.wrongdesign;

/**
 * Выполняет роль и хранилища и сервиса. Установки ответственного и признака завершен.
 * Лучше тогда выделить setResponsible, setComplete в отдельный интерфейс TaskManagerService
 */
public interface TaskManager {

    void addTask(Task task);
    Task getTask(int id);
    boolean setResponsible(Task task, String responsible);
    boolean setComplete(Task task);
    void sendStatusToReporter(Task task);
}
