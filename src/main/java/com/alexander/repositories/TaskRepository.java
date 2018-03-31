package com.alexander.repositories;

import com.alexander.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskRepository")
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByuser(String username);
}
