package com.example.homework_37.servise;

import com.example.homework_37.entity.Note;
import com.example.homework_37.repository.NotesRepository;
import com.example.homework_37.mapper.NotesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NotesService {

    private final NotesRepository notesRepository;

    private final NotesMapper noteMapper;

    public List<Note> findAllNotes() {
        return this.notesRepository.findAll();
    }

    public Optional<Note> findById(Integer id) {
        return this.notesRepository.findById(id);
    }

    public Note savingToNotes(Note note){
        return this.notesRepository.save(note);
    }

    public void deleteById(Integer id) {
        this.notesRepository.deleteById(id);
    }

    public void updateNotes(Integer id, String content) {

        if (this.notesRepository.existsById(id)) {
            this.notesRepository.updateByIdNotes(id, content);
        }
    }

    public List<Note> findByNameNotes(String text) {
        return this.notesRepository.findByName(text, text);
    }

    public List<Note> findByDate(String dateStart, String dateEnd) throws ParseException {
        Long longDateStart = noteMapper.sToL(dateStart);
        Long longDateEnd = noteMapper.sToL(dateEnd);

        if (longDateStart > longDateEnd) {
            return this.notesRepository.findByCreatedAtBetweenOrderByCreatedAt(longDateEnd, longDateStart);
        }
        return this.notesRepository.findByCreatedAtBetweenOrderByCreatedAt(longDateStart, longDateEnd);
    }
}
