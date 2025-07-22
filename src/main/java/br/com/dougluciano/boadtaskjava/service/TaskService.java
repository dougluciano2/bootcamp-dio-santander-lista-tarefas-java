package br.com.dougluciano.boadtaskjava.service;

import br.com.dougluciano.boadtaskjava.entities.Task;
import br.com.dougluciano.boadtaskjava.entities.User;
import br.com.dougluciano.boadtaskjava.enums.SystemMessages;
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

    public Task findById(Long taskId){
        return repository.findById(taskId).orElseThrow(() ->
                new IllegalArgumentException(SystemMessages.TASK_NOT_FOUND.getMessage())
        );
    }

    public void createTask(Task task, User user){
        task.setUser(user);
        repository.save(task);
    }

    public void updateTask(Long id, Task task){
        var toPersist = findById(id);

        toPersist.setTitle(task.getTitle());
        toPersist.setDescription(task.getDescription());
        toPersist.setStatus(task.getStatus());
        toPersist.setPriority(task.getPriority());
        toPersist.setDueDate(task.getDueDate());

        repository.save(toPersist);

    }

    public void deleteTask(Long taskId, String username){
        var task = findById(taskId);

        repository.delete(task);
    }
}
