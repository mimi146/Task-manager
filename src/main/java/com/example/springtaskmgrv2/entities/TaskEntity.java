package com.example.springtaskmgrv2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "tasks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity extends BaseEntity {

    @Column(name = "title", nullable = false, length = 150)
    String title;
    @Column(name = "description", nullable = true, length = 500)
    String description;

    @Column(name = "completed", nullable = false, columnDefinition = "boolean default false")
    Boolean completed;

    @Column(name = "due_date", nullable = true)
    Date dueDate;

//    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL)
//
//    private List<NoteEntity> noteEntityList;
}
