package com.example.springtaskmgrv2.services;

import com.example.springtaskmgrv2.entities.NoteEntity;
import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.repositories.NotesRepository;
import com.example.springtaskmgrv2.repositories.TasksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksService {
    final TasksRepository tasksRepository;

    final NotesRepository notesRepository;

    public TasksService(TasksRepository tasksRepository, NotesRepository notesRepository) {
        this.tasksRepository = tasksRepository;
        this.notesRepository = notesRepository;
    }



    public List<TaskEntity> getall() {
       return tasksRepository.findAll();
    }

    public void saveTask(TaskEntity taskEntity) {
        tasksRepository.save(taskEntity);
    }

    public Optional<TaskEntity> getTaskbyId(Integer id) {
        return tasksRepository.findById(id);
    }

    public ResponseEntity<TaskEntity> patchTask(Integer id, TaskEntity taskEntity) {
        try {
            TaskEntity old = tasksRepository.findById(id).get();
            old.setTitle(taskEntity.getTitle());
            old.setDescription(taskEntity.getDescription());
            final TaskEntity Taskupdated = tasksRepository.save(old);
            return ResponseEntity.ok(Taskupdated);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
           // return new ResourceNotFoundException("id dont exist");

        }
               // .orElseThrow(() -> new IllegalArgumentException("User not exist with id :" + id));



    }

    public void deletebyId(Integer id) {

       tasksRepository.deleteById(id);
    }

    public List<TaskEntity> getTaskTitle(String title) {
        System.out.println(title);
        return tasksRepository.getTaskTitle(title);
    }


    public List<TaskEntity> getTaskByTitle(String title)
    {
        return tasksRepository.findAllByTitleContainingIgnoreCase(title);
    }

    public List<TaskEntity> getTaskCompleted(Boolean completed) {
        return tasksRepository.findAllByCompleted(completed);
        //return tasksRepository.findall
    }

    public List<NoteEntity> getNotesId(Integer id) {
        return notesRepository.findbyTid(id);


    }

    public Optional<NoteEntity> addNotes(NoteEntity noteEntity, Integer id) {
        Boolean check = tasksRepository.existsById(id);

        if(!check) throw new RuntimeException("id dont exist");
         notesRepository.save(noteEntity);
         int key = noteEntity.getId();
        notesRepository.updateFkey(id, key);
        return notesRepository.findById(key);
    }

    public void deleteNode(Integer id, Integer nodeid) {
        notesRepository.deleteById(nodeid);
    }
}
