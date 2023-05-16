package com.example.homework_37.repository;
import com.example.homework_37.entity.Note;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends ListCrudRepository<Note, Integer> {

    @Modifying
    @Query("UPDATE \"Notes\" SET content = :content WHERE id = :id ")
    void updateByIdNotes(
            @Param("id") Integer id,
            @Param("content") String content
    );

    List<Note> findByName(String name, String content);

    List<Note> findByCreatedAtBetweenOrderByCreatedAt(Long start, Long end);
}
