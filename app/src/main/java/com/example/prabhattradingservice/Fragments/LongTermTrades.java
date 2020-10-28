package com.example.prabhattradingservice.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prabhattradingservice.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LongTermTrades#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LongTermTrades extends Fragment {

    public LongTermTrades() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_long_term_trades, container, false);


        return  view;
    }
}