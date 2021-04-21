package info.androidhive.rxjavaretrofit.view;

/**
 * Created by ravi on 20/02/18.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.androidhive.rxjavaretrofit.R;
import info.androidhive.rxjavaretrofit.network.model.ItemLongPressInterface;
import info.androidhive.rxjavaretrofit.network.model.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {
    private final Context context;
    private final List<Note> notesList;
    private final ItemLongPressInterface itemLongPressInterface;
    public String[] mColors = {"BF55EC", "19B5FE", "2ABB9B", "F4D03F", "95A5A6", "DB5A6B", "2ABB9B", "5E97F6", "9CCC65", "4DD0E1", "A1887F"};

    public NotesAdapter(Context context, List<Note> notesList, ItemLongPressInterface noteListener) {
        this.context = context;
        this.notesList = notesList;
        this.itemLongPressInterface = noteListener;
    }

    public static int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        final Note note = notesList.get(i);
        Random r = new Random();
        int i1 = r.nextInt(11 - 0) + 0;
        String color = "#FF" + mColors[i1];
        holder.rl_root.setBackgroundColor(Color.parseColor(color));


        holder.note.setText(note.getNote());
        // Formatting and displaying timestamp
        holder.timestamp.setText(convertTime(note.getTimestamp()));

        holder.rl_root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                itemLongPressInterface.onLongPress(note);
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    public String convertTime(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm a", Locale.getDefault());
        return formatter.format(new Date(Long.parseLong(time)));
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.note)
        TextView note;

        @BindView(R.id.timestamp)
        TextView timestamp;

        @BindView(R.id.rl_root)
        RelativeLayout rl_root;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
