package com.yashdalfthegray.repeatingreminders;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.yashdalfthegray.repeatingreminders.adapters.RecyclerAdapter;
import com.yashdalfthegray.repeatingreminders.lib.SwipeableRecyclerViewTouchListener;
import com.yashdalfthegray.repeatingreminders.models.Reminder;

public class MainActivity extends AppCompatActivity {

    private RecyclerAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You tried to add an item!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reminders_recycler);
        adapter = new RecyclerAdapter(this, Reminder.getData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        SwipeableRecyclerViewTouchListener srvtl = new SwipeableRecyclerViewTouchListener(recyclerView, new SwipeableRecyclerViewTouchListener.SwipeListener() {
            @Override
            public boolean canSwipeLeft(int position) {
                return true;
            }

            @Override
            public boolean canSwipeRight(int position) {
                return true;
            }

            @Override
            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                for (int p : reverseSortedPositions) {
                    adapter.removeItem(p);
                }
            }

            @Override
            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                for (int p : reverseSortedPositions) {
                    adapter.removeItem(p);
                }
            }
        });

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        assert recyclerView != null;
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(srvtl);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Snackbar.make(findViewById(R.id.main_app_view), "You tried to go into settings!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void showNotification(Context context, String title, String subtitle) {
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
            .setSmallIcon(R.drawable.ic_alarm_black_24dp)
            .setContentTitle(title)
            .setContentText(subtitle);

        NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, mBuilder.build());
    }
}
