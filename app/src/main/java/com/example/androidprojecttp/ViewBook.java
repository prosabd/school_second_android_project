package com.example.androidprojecttp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewBook extends AppCompatActivity {
    ListView listView;
    Button btnEdit, btnDelete;
    String selectedItem;
    private static final int REQUEST_CODE_EDIT = 1;
    private static final int REQUEST_CODE_DELETE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        init();
    }

    private void showMessage(String error, String nothingFound) {
    }

    private void init() {
        listView = findViewById(R.id.listview_viewbook_booklist);
        btnEdit = findViewById(R.id.btn_viewbook_edit);
        btnDelete = findViewById(R.id.btn_viewbook_delete);

        fillListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = (String) parent.getItemAtPosition(position);
                for (int i = 0; i < parent.getChildCount(); i++) {
                    if (position == i) {
                        parent.getChildAt(i).setBackgroundColor(Color.GRAY);
                    } else {
                        parent.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });
        btnEdit.setOnClickListener(v -> {
            if (selectedItem == null) {
                Toast.makeText(this, "Please select a book", Toast.LENGTH_SHORT).show();
                return;
            }
//            selectedItem = (String) listView.getSelectedItem();
            String selectedId = selectedItem.split("-")[0];
            String selectedTitle = selectedItem.split("-")[1];
            startActivity(EditBook.class, selectedId, selectedTitle);
        });
        btnDelete.setOnClickListener(v -> {
            if (selectedItem == null) {
                Toast.makeText(this, "Please select a book", Toast.LENGTH_SHORT).show();
                return;
            }
//            selectedItem = (String) listView.getSelectedItem();
            String selectedId = selectedItem.split("-")[0];
            String selectedTitle = selectedItem.split("-")[1];
            startActivity(DeleteBook.class, selectedId, selectedTitle);
        });
    }

    private void startActivity(Class<?> cls, String id, String title) {
        Intent intent = new Intent(this, cls);
        intent.putExtra("selectedId", id);
        intent.putExtra("selectedTitle", title);
//        startActivity(intent);
        if (cls == EditBook.class) {
            startActivityForResult(intent, REQUEST_CODE_EDIT);
        } else if (cls == DeleteBook.class) {
            startActivityForResult(intent, REQUEST_CODE_DELETE);
        }
    }

    private void fillListView() {
        Cursor res = MainActivity.myDb.getAllData();
        List<String> items = new ArrayList<>();
        while (res.moveToNext()) {
            String resString = res.getString(0) + "-" + res.getString(1);
            items.add(resString);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == REQUEST_CODE_EDIT || requestCode == REQUEST_CODE_DELETE) && resultCode == RESULT_OK) {
            fillListView();
        }
    }

}