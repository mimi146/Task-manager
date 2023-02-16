package com.example.springtaskmgrv2.services;

import com.example.springtaskmgrv2.dto.NoteRequestDto;
import com.example.springtaskmgrv2.dto.NotesDto;
import com.example.springtaskmgrv2.entities.NoteEntity;
import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.repositories.NotesRepository;
import com.example.springtaskmgrv2.repositories.TasksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

//    public List<NoteEntity> getNotesId(Integer id) {
//        return notesRepository.findbyTid(id);
//    }

    public List<NotesDto> getNotesId(Integer id) {
        return notesRepository.findAllByTaskId(id).stream().map(entity->
           new NotesDto(entity.getBody(),entity.getId())
        ).collect(Collectors.toList());
    }

    public Optional<NoteEntity> addNotes(NoteRequestDto noteRequestDto, Integer id) {
       Optional<TaskEntity> taskEntity = tasksRepository.findById(id);
        if(!taskEntity.isPresent()) throw new RuntimeException("id dont exist");
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setBody(noteRequestDto.getBody());
        noteEntity.setTask(taskEntity.get());
         notesRepository.save(noteEntity);
        return Optional.of(noteEntity);
    }

    public void deleteNode(Integer id, Integer nodeid) {
        notesRepository.deleteByTaskAndId(nodeid,id);
    }
}
