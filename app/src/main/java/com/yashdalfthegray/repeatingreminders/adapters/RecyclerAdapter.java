package com.yashdalfthegray.repeatingreminders.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import com.yashdalfthegray.repeatingreminders.R;
import com.yashdalfthegray.repeatingreminders.models.Reminder;

import java.util.List;

/**
 * Created by Yash Kulshrestha on 4/21/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ReminderViewHolder> {

    private List<Reminder> mData;
    private LayoutInflater mInflater;

    public RecyclerAdapter(Context context, List<Reminder> data) {
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerAdapter.ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.reminder_item, parent, false);
        ReminderViewHolder holder = new ReminderViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ReminderViewHolder holder, int position) {
        Reminder currentObj = mData.get(position);
        holder.setData(currentObj, position);
    }

    @Override
    public int getItemCount() {
        return this.mData.size();
    }

    public void addItem(int position, Reminder reminder) {
        mData.add(position, reminder);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mData.size());
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
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
