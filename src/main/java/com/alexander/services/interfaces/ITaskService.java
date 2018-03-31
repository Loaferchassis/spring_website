package com.alexander.services.interfaces;

import com.alexander.models.Task;

import java.util.List;

public interface ITaskService {
    void save(Task task);

    List<Task> findAll(String username);
}
