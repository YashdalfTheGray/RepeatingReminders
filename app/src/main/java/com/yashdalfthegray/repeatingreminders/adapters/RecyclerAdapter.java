package com.yashdalfthegray.repeatingreminders.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yashdalfthegray.repeatingreminders.R;
import com.yashdalfthegray.repeatingreminders.models.Reminder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

        TextView title, reminderText;
        ImageButton editReminderButton;
        LinearLayout itemLinearLayout;
        int position;
        Reminder current;
        Resources res;

        public ReminderViewHolder(View itemView) {
            super(itemView);
            res = itemView.getResources();

            itemLinearLayout = (LinearLayout) itemView.findViewById(R.id.reminder_item_container);
            title = (TextView) itemView.findViewById(R.id.reminder_title);
            reminderText = (TextView) itemView.findViewById(R.id.reminder_text);
            editReminderButton = (ImageButton) itemView.findViewById(R.id.button_edit_reminder);
        }

        public void setData(Reminder currentObj, final int position) {
            this.current = currentObj;
            this.position = position;
            this.title.setText(current.getTitle());
            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.US);
            this.reminderText.setText(String.format(res.getString(R.string.reminder_text), sdf.format(new Date()), sdf.format(new Date())));
            this.editReminderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v.getRootView().findViewById(R.id.main_app_view), "You tried to edit a list item!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });
            this.itemLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v.getRootView().findViewById(R.id.main_app_view), "You clicked on a list item!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });
        }
    }
}
