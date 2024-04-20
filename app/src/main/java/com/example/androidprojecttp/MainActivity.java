package com.example.androidprojecttp;

import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity<Database> extends AppCompatActivity {
    static DatabaseHelper myDb;
    Button btnadd, btnview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        init();
    }

    private void init() {
        btnadd = findViewById(R.id.btn_add);
        btnview = findViewById(R.id.btn_view);

        btnadd.setOnClickListener(v -> {
            startActivity(AddBook.class);
        });

        btnview.setOnClickListener(v -> {
            startActivity(ViewBook.class);
        });
    }


    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}