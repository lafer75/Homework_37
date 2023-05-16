package com.example.homework_37.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Notes")
public class Note {

    @Id
    @Column("id")
    private Integer id;

    @Column("name")
    private String name;

    @Column("content")
    private String content;

    @Column("created_at")
    private Long createdAt;

    public Note(String name, String content, Long dateNotes) {
        this.name = name;
        this.content = content;
        this.createdAt = dateNotes;
    }
}