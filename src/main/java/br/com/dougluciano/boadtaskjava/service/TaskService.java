package br.com.dougluciano.boadtaskjava.service;

import br.com.dougluciano.boadtaskjava.entities.Task;
import br.com.dougluciano.boadtaskjava.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService (TaskRepository repository){
        this.repository = repository;
    }

    public List<Task> findTaskByUserId(Long userId){
        return repository.findByUserId(userId);
    }
}
