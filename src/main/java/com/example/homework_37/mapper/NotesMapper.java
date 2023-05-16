package com.example.homework_37.mapper;
import com.example.homework_37.entity.Note;
import com.example.homework_37.entity.NoteDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class NotesMapper {

    public NoteDTO NoteToNoteDTO(Note note) {

        return NoteDTO.builder()
                .name(note.getName())
                .content(note.getContent())
                .createdAt(lToS(note.getCreatedAt()))
                .build();
    }

    public List<NoteDTO> mapListNoteToListNoteDto(List<Note> list) {
        List<NoteDTO> noteDtoList = new ArrayList<>();
        for (Note a : list) {
            noteDtoList.add(NoteToNoteDTO(a));
        }
        return noteDtoList;
    }

    public String lToS (Long date){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        ZoneId zoneId = ZoneId.of("UTC");
        return  Instant.ofEpochMilli(date).atZone(zoneId).format(dateTimeFormatter);
    }

    public Long sToL (String dateString) throws ParseException {
        SimpleDateFormat b = new SimpleDateFormat("dd-MM-yyyy");
        Date date = b.parse(dateString);
        return date.getTime();
    }
}
