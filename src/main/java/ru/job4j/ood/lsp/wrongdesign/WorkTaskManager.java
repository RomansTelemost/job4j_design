package ru.job4j.ood.lsp.wrongdesign;

import java.util.List;
import java.util.Optional;

public class WorkTaskManager implements TaskManager {

    private List<Task> tasks;

    public WorkTaskManager(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public Task getTask(int id) {
        Optional<Task> optionalTask = tasks.stream().filter(task -> task.getId() == id).findFirst();
        return optionalTask.orElse(null);
    }

    @Override
    public boolean setResponsible(Task task, String responsible) {
        if (task.getResponsible() != null) {
            return false;
        }
        task.setResponsible(responsible);
        return true;
    }

    @Override
    public boolean setComplete(Task task) {
        if (task.isComplete()) {
            return false;
        }
        task.setComplete(true);
        return true;
    }

    @Override
    public void sendStatusToReporter(Task task) {
        System.out.println(String.format("Task id %s, has status %s, responsible person %s", task.getId(), task.getTaskStatus(), task.getResponsible()));
    }
}
