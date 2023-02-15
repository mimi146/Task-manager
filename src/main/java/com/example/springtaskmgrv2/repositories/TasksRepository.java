package com.example.springtaskmgrv2.repositories;

import com.example.springtaskmgrv2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity, Integer> {

    @Query("SELECT t from tasks t where t.completed = ?1")
    List<TaskEntity> findAllByCompleted(boolean completed);

    // Ideally this is 'business logic' terminology (i.e. 'overdue') so shouldn't be here
    @Query("SELECT t FROM tasks t WHERE t.completed = false AND t.dueDate < CURRENT_DATE")
    List<TaskEntity> findAllOverdue();

    List<TaskEntity> findAllByCompletedAndDueDateBefore(boolean completed, Date dueDate);

    List<TaskEntity> findAllByTitleContainingIgnoreCase(String titleFragment);
  @Query("select t from tasks t where t.title = ?1")
    List<TaskEntity> getTaskTitle(String title);
}
