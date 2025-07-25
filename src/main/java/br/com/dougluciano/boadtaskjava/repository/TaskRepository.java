package br.com.dougluciano.boadtaskjava.repository;

import br.com.dougluciano.boadtaskjava.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);
}
