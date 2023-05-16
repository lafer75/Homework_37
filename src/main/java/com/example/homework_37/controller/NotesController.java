package com.example.homework_37.controller;

import com.example.homework_37.entity.Note;
import com.example.homework_37.entity.NoteDTO;
import com.example.homework_37.servise.NotesService;
import com.example.homework_37.mapper.NotesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.List;
@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NotesController {

    private final NotesService notesService;

    private final NotesMapper notesMapper;


    @GetMapping("/all")
    public List<NoteDTO> getById() {
        return this.notesMapper.mapListNoteToListNoteDto(notesService.findAllNotes());
    }

    @GetMapping("/id")
    public NoteDTO getById(@RequestParam("id") Integer id) {
        return this.notesMapper
                .NoteToNoteDTO(notesService.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("no note with id = " + id)));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Note add(
            @RequestParam("name") String name,
            @RequestParam("content") String content) {
        Long dateAddNote = ZonedDateTime.now().toInstant().toEpochMilli();
        return notesService.savingToNotes(new Note(name, content, dateAddNote));
    }

    @GetMapping("/delete")
    public void deleteById(@RequestParam("id") Integer id) {
        notesService.deleteById(id);
    }

    @GetMapping("/update")
    public void getById(@RequestParam("id") Integer id,
                        @RequestParam("content") String content) {
        notesService.updateNotes(id, content);
    }

    @GetMapping("/find/name")
    public List<NoteDTO> findByName(@RequestParam("text") String text) {
        return this.notesMapper.mapListNoteToListNoteDto(notesService.findByNameNotes(text));
    }

    @GetMapping("/find/date")
    public List<NoteDTO> findByDate(
            @RequestParam("dateStart") String dateStart,
            @RequestParam("dateEnd") String dateEnd) throws ParseException {
        return this.notesMapper.mapListNoteToListNoteDto(notesService.findByDate(dateStart, dateEnd));
    }
}