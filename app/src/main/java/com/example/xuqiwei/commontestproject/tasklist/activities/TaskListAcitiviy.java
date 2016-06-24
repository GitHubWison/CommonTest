package com.example.xuqiwei.commontestproject.tasklist.activities;

import android.os.Bundle;

import com.example.commonlibrary.activity.BaseActivity;
import com.example.xuqiwei.commontestproject.tasklist.fragments.TaskListFragment;

/**
 * Created by xuqiwei on 16-6-23.
 */
public class TaskListAcitiviy extends BaseActivity {
    private TaskListFragment taskListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (taskListFragment==null)
        {
            taskListFragment = new TaskListFragment();

        }
        initFragment(taskListFragment,taskListFragment.getClass());

    }
}
