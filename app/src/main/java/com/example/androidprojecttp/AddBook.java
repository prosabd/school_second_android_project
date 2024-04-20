package com.example.androidprojecttp;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class AddBook extends AppCompatActivity {
    EditText title, pages;
    Button btnadd, btncancel;
    Toast emptyfill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        init();
    }

    private void init() {
        title = findViewById(R.id.input_addbook_title);
        pages = findViewById(R.id.input_addbook_pages);
        btnadd = findViewById(R.id.btn_addbook_confirm);
        btncancel = findViewById(R.id.btn_addbook_cancel);

        btnadd.setOnClickListener(v -> {
            if (title.getText().toString().isEmpty() || pages.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                String titlecontent = title.getText().toString();
                Integer pagescontent = Integer.valueOf(pages.getText().toString());
                DatabaseHelper myDb = new DatabaseHelper(this);
                boolean isInserted = myDb.insertData(titlecontent, pagescontent);
                if (isInserted) {
                    Toast.makeText(this, "Data Inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Data not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
        btncancel.setOnClickListener(v -> {
            finish();
        });
    }
}