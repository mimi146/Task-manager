package com.example.springtaskmgrv2.controllers;

import com.example.springtaskmgrv2.dto.NoteRequestDto;
import com.example.springtaskmgrv2.dto.NotesDto;
import com.example.springtaskmgrv2.entities.NoteEntity;
import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.services.TasksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @PostMapping("")
    private int saveTask(@RequestBody TaskEntity taskEntity) {
        tasksService.saveTask(taskEntity);
        return taskEntity.getId();
    }
    @PatchMapping ("/{id}")
    public ResponseEntity<TaskEntity> update(@PathVariable Integer id, @RequestBody TaskEntity taskEntity){

        return tasksService.patchTask(id,taskEntity);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        tasksService.deletebyId(id);
        return ResponseEntity.ok("deleted");
    }

    @GetMapping("/h")
    public String hello() {
        return "Hello";

    }
    @GetMapping("")
    public List<TaskEntity> getTasks() {
        return tasksService.getall();

    }

    @GetMapping("/{id}")
    public Optional<TaskEntity> getTask(@PathVariable Integer id) {
        return tasksService.getTaskbyId(id);
    }

    @GetMapping("/")
    public List<TaskEntity> TaskbyTitle(@RequestParam("title") String title) {
        return tasksService.getTaskByTitle(title);
       // return title;
        //return tasksService.getTaskTitle(title);

    }
    @GetMapping("completed")
    public List<TaskEntity> Taskcompleted(@RequestParam("completed") boolean completed) {
        return tasksService.getTaskCompleted(completed);
       // return completed;
    }

    @GetMapping("/{id}/notes")
    public List<NotesDto> getNotes(@PathVariable Integer id) {
        return tasksService.getNotesId(id);
    }

    @PostMapping("/{id}/notes")
    public Optional<NoteEntity> addNotes(@RequestBody NoteRequestDto noteRequestDto, @PathVariable Integer id) {
       // TaskEntity taskEntity =
      return  tasksService.addNotes(noteRequestDto,id);
        //return noteEntity.getId();
    }

    @DeleteMapping("/{id}/notes/{nodeid}")
    public ResponseEntity deletenode(@PathVariable("id") Integer id,@PathVariable("nodeid") Integer nodeid) {
        tasksService.deleteNode(id,nodeid);
        return ResponseEntity.ok("deleted");
    }



}
