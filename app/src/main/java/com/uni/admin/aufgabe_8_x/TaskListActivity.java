package com.uni.admin.aufgabe_8_x;

/**
 * Created by MK on 24.01.18.
 */

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {

    private ListView listView;
    private TaskArrayAdapter adapter;
    private ArrayList<Tasks> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTestList();
        initView();
        initAdapter();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.list_notes);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNoteDialog();
            }
        });
    }

    private void initAdapter() {
        adapter = new TaskArrayAdapter(this, notes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Item clicked");
                openNoteDialog(position);
            }
        });
    }

    private void initTestList() {
        this.notes = new ArrayList<>();
        notes.add(new Tasks("0","Schein erhalten!","Aufgabe 8, 10 und 11 bestehen."));


    }

    private void openNoteDialog(int position) {
        Tasks note = notes.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_task_view, null);
        builder.setView(dialogView);

        TextView title = (TextView) dialogView.findViewById(R.id.tv_title);
        title.setText(note.getTitle());

        TextView description = (TextView) dialogView.findViewById(R.id.tv_description);
        description.setText(note.getNote());

        final AlertDialog dialog = builder.create();
        dialog.show();

        dialog.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void openAddNoteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.fragment_task_add, null);
        builder.setView(dialogView);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // add Note
                EditText etTitle = (EditText) dialogView.findViewById(R.id.et_title);
                String title = etTitle.getText().toString();

                EditText etDescription = (EditText) dialogView.findViewById(R.id.et_description);
                String description = etDescription.getText().toString();

                notes.add(new Tasks(String.valueOf(notes.size()), title, description));

                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
