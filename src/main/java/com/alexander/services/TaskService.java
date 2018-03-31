package com.alexander.services;

import com.alexander.models.Task;
import com.alexander.repositories.TaskRepository;
import com.alexander.services.interfaces.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {
    @Autowired
    TaskRepository taskRepository;

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public List<Task> findAll(String username) {
        return taskRepository.findAllByuser(username);
    }
}
