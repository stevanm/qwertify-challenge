package com.qwertify.project.presentation.fragment;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.qwertify.project.R;
import com.qwertify.project.data.network.Resource;
import com.qwertify.project.domain.model.login.UserLogin;
import com.qwertify.project.presentation.activity.MainActivity;
import com.qwertify.project.presentation.viewmodel.AuthViewModel;
import com.qwertify.project.utils.ValidationUtil;

import org.jetbrains.annotations.NotNull;

public class AuthFragment extends Fragment {

    private AuthViewModel authViewModel;
    private UserLogin userLogin;

    //ui elements
    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private MaterialTextView forgottenPasswordTextView;
    private MaterialButton loginButton;
    private LinearLayout facebookLinearLayout;
    private LinearLayout googleLinearLayout;
    private MaterialTextView signupTextView;


    private MainActivity mainActivity;

    public static AuthFragment newInstance() {
        return new AuthFragment();
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        this.mainActivity = (MainActivity) context;
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

        setupUI(view);

        authViewModel.observableUserLoginData().observe(getViewLifecycleOwner(), userLogin -> {
            Toast.makeText(view.getContext(), userLogin.toString(), Toast.LENGTH_SHORT).show();
        });

        //handle status
        authViewModel.observableUserLoginStatus().observe(getViewLifecycleOwner(), resource -> {
            if (resource instanceof Resource.Loading) {
                Toast.makeText(mainActivity.getApplicationContext(), "Logging... Please, wait!", Toast.LENGTH_SHORT).show();
            } else if (resource instanceof Resource.Error) {
                Toast.makeText(mainActivity.getApplicationContext(), "ERROR: " + ((Resource.Error) resource).errorMessage, Toast.LENGTH_SHORT).show();
            } else if (resource instanceof Resource.Success) {
                Toast.makeText(mainActivity.getApplicationContext(), "Logged successful!", Toast.LENGTH_SHORT).show();
                UserLogin ul = ((Resource.Success) resource).userLogin;
                //cache token
                authViewModel.cacheAuthData(ul.getJwt());
                mainActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, PasswordListFragment.newInstance())
                        .commitNow();
            }
        });

    }

    void setupUI(View view) {
        emailEditText = view.findViewById(R.id.email_input);
        passwordEditText = view.findViewById(R.id.password_input);
        forgottenPasswordTextView = view.findViewById(R.id.forgotten_password);
        loginButton = view.findViewById(R.id.login_button);
        facebookLinearLayout = view.findViewById(R.id.facebook_login);
        googleLinearLayout = view.findViewById(R.id.google_login);
        signupTextView = view.findViewById(R.id.sign_up);

        forgottenPasswordTextView.setPaintFlags(forgottenPasswordTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        loginButton.setOnClickListener(v -> {
            String email = null != emailEditText.getText() ? emailEditText.getText().toString().trim() : "";
            if (!ValidationUtil.isEmailValid(email)) {
                Toast.makeText(mainActivity.getApplicationContext(), "Email is not valid!", Toast.LENGTH_SHORT).show();
                return;
            }
            String pass = null != passwordEditText.getText() ? passwordEditText.getText().toString().trim() : "";
            authViewModel.login(email, pass);
        });

        facebookLinearLayout.setOnClickListener(v -> Toast.makeText(mainActivity.getApplicationContext(), "Not implemented yet!", Toast.LENGTH_SHORT).show());
        googleLinearLayout.setOnClickListener(v -> Toast.makeText(mainActivity.getApplicationContext(), "Not implemented yet!", Toast.LENGTH_SHORT).show());
        signupTextView.setOnClickListener(v -> Toast.makeText(mainActivity.getApplicationContext(), "Not implemented yet!", Toast.LENGTH_SHORT).show());
    }

}