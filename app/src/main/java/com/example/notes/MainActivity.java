package com.example.notes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    List<NoteClass> list;
    CustomAdapter customAdapter;
    AppDatatbase appDatatbase;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleteAllItem) {
            appDatatbase.noteClassDao().emptyDatabse();
            customAdapter.updateData(appDatatbase.noteClassDao().getAllTexts());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_file, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatatbase = Room.databaseBuilder(getApplicationContext(), AppDatatbase.class, "hey").allowMainThreadQueries().build();
        list = appDatatbase.noteClassDao().getAllTexts();


        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        customAdapter = new CustomAdapter(list, this, appDatatbase);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(editText)) {
                    Toast.makeText(MainActivity.this, "Enter a text", Toast.LENGTH_SHORT).show();
                } else {
                    appDatatbase.noteClassDao().insertText(new NoteClass(editText.getText().toString()));

                    customAdapter.updateData(appDatatbase.noteClassDao().getAllTexts());
                    editText.setText("");
                }
            }
        });


    }


    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
