package com.qwertify.project.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.qwertify.project.R;

public class PasswordListFragment extends Fragment {

    public PasswordListFragment() {
        // Required empty public constructor
    }

    public static PasswordListFragment newInstance() {
        PasswordListFragment fragment = new PasswordListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_password_list, container, false);
    }
}