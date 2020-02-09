package com.me.temperature;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class InvestigationFragment extends Fragment {
    private TpViewModel tpViewModel;
    private RecyclerView recyclerView;
    private MyAdpter myAdpter;
    private FloatingActionButton floatingActionButton;
    private LiveData<List<temperature>> findTemperature;
    private List<temperature> allTemperatures;
    public InvestigationFragment() {
        setHasOptionsMenu(true);
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_investigation, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.clearAll:
                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                builder.setTitle("清空账单");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tpViewModel.deleteAllTemperature();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create();
                builder.show();
                break;
            case R.id.chart:
                NavController navController = Navigation.findNavController(requireActivity(),R.id.fragment);
                navController.navigate(R.id.action_investigationFragment_to_chartFragment);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu,menu);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tpViewModel = ViewModelProviders.of(requireActivity()).get(TpViewModel.class);
        recyclerView = requireActivity().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        myAdpter = new MyAdpter();
        recyclerView.setAdapter(myAdpter);
        findTemperature =  tpViewModel.getAllTemperature();
        findTemperature.observe(requireActivity(), new Observer<List<temperature>>() {
            @Override
            public void onChanged(List<temperature> temperatures) {
                allTemperatures = temperatures;
                myAdpter.setAllTemperature(temperatures);
                myAdpter.notifyDataSetChanged();
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.START | ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                temperature temperature = allTemperatures.get(viewHolder.getLayoutPosition());
                tpViewModel.deleteTemperature(temperature);
            }
        }).attachToRecyclerView(recyclerView);
        floatingActionButton = requireActivity().findViewById(R.id.buttonTpAdd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_investigationFragment_to_addFragment);
            }
        });
    }
}
