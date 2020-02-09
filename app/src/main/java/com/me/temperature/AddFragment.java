package com.me.temperature;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {
    private Button buttonSubmit;
    private EditText editTextTp;
    private TpViewModel tpViewModel;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = requireActivity();
        tpViewModel = ViewModelProviders.of(activity).get(TpViewModel.class);
        buttonSubmit = activity.findViewById(R.id.buttonAdd);
        editTextTp = activity.findViewById(R.id.editTextTp);
        buttonSubmit.setEnabled(false);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String type = editTextTp.getText().toString().trim();
                buttonSubmit.setEnabled(!type.isEmpty());

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        editTextTp.addTextChangedListener(textWatcher);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tp = editTextTp.getText().toString().trim();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");// HH:mm:ss
                //获取当前时间
                Date date = new Date(System.currentTimeMillis());
                temperature temperature = new temperature(tp+"℃",simpleDateFormat.format(date));
                tpViewModel.insertTemperature(temperature);
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_addFragment_to_investigationFragment);
            }
        });
    }
}
