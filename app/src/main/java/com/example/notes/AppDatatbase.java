package com.example.notes;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {NoteClass.class}, version = 1)

public abstract class AppDatatbase extends RoomDatabase {
    public abstract NoteClassDao noteClassDao();
}
