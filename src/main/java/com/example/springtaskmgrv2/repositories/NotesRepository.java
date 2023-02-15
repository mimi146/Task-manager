package com.example.springtaskmgrv2.repositories;

import com.example.springtaskmgrv2.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntity, Integer> {


    @Query(" FROM notes n where n.task.id = :id")
    List<NoteEntity> findbyTid(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("Update notes n set n.task.id =?1 where n.id= ?2")
    void updateFkey(@Param("id") Integer id, @Param("key") Integer key);




//    void deleteByField1andField2(String field1, String field2);
  //  void deletebByidandtask_id(Integer nodeid, Integer id);
}

