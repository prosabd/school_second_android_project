package com.example.androidprojecttp;

import android.database.Cursor;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class EditBook extends AppCompatActivity {
    EditText title, pages, id;
    Button btnUpdate, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        init();
    }

    private void init() {
        title = findViewById(R.id.input_editbook_title);
        pages = findViewById(R.id.input_editbook_pages);
        id = findViewById(R.id.input_editbook_bookid);
        String selectedId = getIntent().getStringExtra("selectedId");
        String selectedTitle = getIntent().getStringExtra("selectedTitle");
        String input = selectedId + " - " + selectedTitle;
        id.setText(input);
        btnUpdate = findViewById(R.id.btn_editbook_confirm);
        btnCancel = findViewById(R.id.btn_editbook_cancel);
        DatabaseHelper myDb = MainActivity.myDb;
        Cursor res = myDb.getBook(Integer.valueOf(selectedId));
        while (res.moveToNext()) {
            title.setText(res.getString(1));
            pages.setText(res.getString(2));
        }

        btnUpdate.setOnClickListener(v -> {
            if (title.getText().toString().isEmpty() || pages.getText().toString().isEmpty()) {
                Toast.makeText(EditBook.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                return;
            }
            boolean isUpdate = myDb.updateData(Integer.valueOf(selectedId), title.getText().toString(), Integer.valueOf(pages.getText().toString()));
            if (isUpdate) {
                Toast.makeText(EditBook.this, "Data Updated", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
            } else {
                Toast.makeText(EditBook.this, "Data not Updated", Toast.LENGTH_LONG).show();
            }
            finish();
        });

        btnCancel.setOnClickListener(v -> {
            finish();
        });
    }
}