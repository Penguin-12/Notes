package com.example.notes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteClassDao {


    @Query("Select * from NoteClass")
    List<NoteClass> getAllTexts();


    @Insert
    void insertText(NoteClass... noteClasses);

    @Update
    void updateDatabase(NoteClass... noteClasses);

    @Delete
    void deleteNote(NoteClass... noteClasses);

    @Query("Delete from NoteClass")
    void emptyDatabse();

    @Query("SELECT * FROM NoteClass WHERE id = :number")
    public NoteClass getNoteWithId(String number);
}
