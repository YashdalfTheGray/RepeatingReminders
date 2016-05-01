package com.yashdalfthegray.repeatingreminders.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yash Kulshrestha on 4/20/2016.
 */
public class Reminder {

    private String title;
    private Date start;
    private Date end;
    private Date interval;
    private Boolean pushInterval;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getInterval() {
        return interval;
    }

    public void setInterval(Date interval) {
        this.interval = interval;
    }

    public Boolean getPushInterval() {
        return pushInterval;
    }

    public void setPushInterval(Boolean pushInterval) {
        this.pushInterval = pushInterval;
    }

    public static List<Reminder> getData() {
        List<Reminder> dataList = new ArrayList<>();

        String[] titles = getTitles();

        for (String title : titles) {
            Reminder item = new Reminder();
            item.setTitle(title);
            item.setStart(new Date());
            item.setEnd(new Date());
            item.setInterval(new Date());
            item.setPushInterval(false);

            dataList.add(item);
        }

        return dataList;
    }

    private static String[] getTitles() {
        return new String[] {
                "Test 1",
                "Test 2",
                "Test 3",
                "Test 4",
                "Test 5",
                "Test 6",
                "Test 7",
                "Test 8",
                "Test 9",
                "Test 0"
        };
    }
}
