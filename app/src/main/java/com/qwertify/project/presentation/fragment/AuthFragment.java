package com.qwertify.project.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.qwertify.project.R;
import com.qwertify.project.domain.model.login.UserLogin;
import com.qwertify.project.presentation.viewmodel.AuthViewModel;

import org.jetbrains.annotations.NotNull;

public class AuthFragment extends Fragment {

    private AuthViewModel authViewModel;

    public static AuthFragment newInstance() {
        return new AuthFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.auth_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        authViewModel.login("test0@gmail.com", "asdf123");

        authViewModel.observableUserLoginData().observe(getViewLifecycleOwner(), new Observer<UserLogin>() {
            @Override
            public void onChanged(UserLogin userLogin) {
                Toast.makeText(view.getContext(), "TEST", Toast.LENGTH_SHORT).show();
            }
        });

    }


}