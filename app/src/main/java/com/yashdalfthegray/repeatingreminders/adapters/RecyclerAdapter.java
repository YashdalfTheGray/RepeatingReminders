package com.yashdalfthegray.repeatingreminders.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import com.yashdalfthegray.repeatingreminders.R;
import com.yashdalfthegray.repeatingreminders.models.Reminder;

/**
 * Created by Yash Kulshrestha on 4/21/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ReminderViewHolder> {

    @Override
    public RecyclerAdapter.ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ReminderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ReminderViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextClock start, end, interval;
        int position;
        Reminder current;

        public ReminderViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.reminder_title);
            start = (TextClock) itemView.findViewById(R.id.reminder_start);
            end = (TextClock) itemView.findViewById(R.id.reminder_end);
            interval = (TextClock) itemView.findViewById(R.id.reminder_interval);
        }

        public void setData(Reminder currentObj, final int position) {
            this.current = currentObj;
            this.position = position;
            this.title.setText(current.getTitle());
            this.start.setText(current.getStart().toString());
            this.end.setText(current.getEnd().toString());
            this.interval.setText(current.getInterval().toString());
        }
    }
}
