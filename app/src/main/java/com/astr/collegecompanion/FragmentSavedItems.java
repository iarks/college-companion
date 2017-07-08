package com.astr.collegecompanion;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSavedItems extends Fragment
{
    ListView lv;
    LinearLayout message,list;
    ArrayList<SavedItemsDataset> saved_items = new ArrayList<SavedItemsDataset>();
    SwipeRefreshLayout swipeContainer;

    public FragmentSavedItems()
    {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_saveditems, container, false);
        lv=(ListView)v.findViewById(R.id.lv_saveditems);
        message = (LinearLayout) v.findViewById(R.id.message);
        list = (LinearLayout) v.findViewById(R.id.list);

        DatabaseHandler db = new DatabaseHandler(getActivity());
        saved_items = db.getAll();
        if (!saved_items.isEmpty())
        {
            message.setVisibility(View.GONE);
        }
        AdapterSavedItems adapter= new AdapterSavedItems(getActivity(), saved_items);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(click_list);

        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipecontainer);

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(pull_refresh);

        return v;
    }

   AdapterView.OnItemClickListener click_list = new AdapterView.OnItemClickListener()
   {
       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id)
       {
           String body =saved_items.get(position).getDate();
           String date =saved_items.get(position).getBody();
           String head = saved_items.get(position).getHeader();
           int urgent = saved_items.get(position).getUrgent();

           Intent switch_to_sub = new Intent(getActivity(),ActivitySub.class);
           switch_to_sub.putExtra("head", head);
           switch_to_sub.putExtra("body", body);
           switch_to_sub.putExtra("date", date);
           switch_to_sub.putExtra("urgent",urgent);
           startActivity(switch_to_sub);
       }
   };

    @Override
    public void onResume()
    {
        super.onResume();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle("Saved items");
    }

    SwipeRefreshLayout.OnRefreshListener pull_refresh = new SwipeRefreshLayout.OnRefreshListener()
    {
        @Override
        public void onRefresh()
        {
            reload();
            swipeContainer.setRefreshing(false);
        }
    };


    Void reload()
    {
        saved_items.clear();
        DatabaseHandler db = new DatabaseHandler(getActivity());
        saved_items = db.getAll();
        if (saved_items.isEmpty())
        {
            message.setVisibility(View.VISIBLE);
        }
        AdapterSavedItems adapter= new AdapterSavedItems(getActivity(), saved_items);
        lv.setAdapter(adapter);
        return null;
    }


}