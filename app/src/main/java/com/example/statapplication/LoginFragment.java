package com.example.statapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {
    private FragmentLoginListener listener;
    private EditText editText;
    private Button buttonOk;

    public interface FragmentLoginListener{
        void onInputLoginSent(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        editText = v.findViewById(R.id.edit_text);
        buttonOk = v.findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                CharSequence input = editText.getText();
                //Sent to whoever implements this login interface
                listener.onInputLoginSent(input);
            }
        });

        return v;
    }

    public void updateEditText(CharSequence newText){
        editText.setText(newText);
    }

    //Fragment must be attached to an activity for this to work
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof  FragmentLoginListener){
            listener = (FragmentLoginListener) context;
        }else{
            throw new RuntimeException(context.toString()
            + " must implement FragmentLoginListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
