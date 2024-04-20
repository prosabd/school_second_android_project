package com.example.androidprojecttp;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class DeleteBook extends AppCompatActivity {
    EditText id;
    Button btnDelete, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_book);

        init();
    }

    private void init() {
        id = findViewById(R.id.input_deletebook_bookid);
        String selectedId = getIntent().getStringExtra("selectedId");
        String selectedTitle = getIntent().getStringExtra("selectedTitle");
        String input = selectedId + " - " + selectedTitle;
        id.setText(input);
        btnDelete = findViewById(R.id.btn_deletebook_confirm);
        btnCancel = findViewById(R.id.btn_deletebook_cancel);

        btnDelete.setOnClickListener(v -> {
            DatabaseHelper myDb = MainActivity.myDb;
            boolean isDeleted = myDb.deleteBook(Integer.valueOf(selectedId));
            if (isDeleted) {
                Toast.makeText(DeleteBook.this, "Data Deleted", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
            } else {
                Toast.makeText(DeleteBook.this, "Data not Deleted", Toast.LENGTH_LONG).show();
            }
            finish();
        });

        btnCancel.setOnClickListener(v -> {
            finish();
        });
    }
}