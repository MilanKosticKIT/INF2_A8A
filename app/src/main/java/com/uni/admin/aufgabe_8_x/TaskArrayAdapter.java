package com.uni.admin.aufgabe_8_x;

/**
 * Created by MK on 24.01.18.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskArrayAdapter extends ArrayAdapter<Tasks>{
    private final Context context;
    private ArrayList<Tasks> notesList;

    public TaskArrayAdapter(Context context, ArrayList<Tasks> notesList) {
        super(context, -1, notesList);
        this.context = context;
        this.notesList = notesList;
    }
    static class ViewHolder {
        TextView notesName;
        Button btnDelete;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_tasks, parent, false);

            holder = new ViewHolder();
            holder.notesName = convertView.findViewById(R.id.notes_name);
            holder.btnDelete = convertView.findViewById(R.id.notes_delete);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Tasks note = notesList.get(position);

        holder.notesName.setText(note.getTitle());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesList.remove(position);
                TaskArrayAdapter.this.notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return notesList.size();
    }

    @Nullable
    @Override
    public Tasks getItem(int position) {
        return notesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
