package com.example.springtaskmgrv2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity(name = "notes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteEntity extends BaseEntity {

    @Column(name = "body", nullable = false, length = 500)
    String body;


//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name="task_id")
    @ManyToOne
        @JoinColumn(name="tasks_id")
    TaskEntity task;
}
