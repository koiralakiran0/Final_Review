package com.example.mshehab.inclass07;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateContactFragment extends Fragment {

    EditText editTextName, editTextPhone, editTextEmail;
    ImageView imageView;
    int imgId = -1;

    private OnFragmentInteractionListener mListener;

    public CreateContactFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_contact, container, false);
        imgId = mListener.getCurrentImage();

        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextName = view.findViewById(R.id.editTextName);
        editTextPhone = view.findViewById(R.id.editTextPhone);

        imageView = view.findViewById(R.id.imageView);
        if(imgId != -1){
            imageView.setImageResource(imgId);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.startImageSelector();
            }
        });


        view.findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //assuming you are going to write the validation !!
                Contact contact = new Contact(editTextName.getText().toString(),
                        editTextPhone.getText().toString(),editTextEmail.getText().toString(),"SIS",imgId);
                mListener.addContact(contact);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void addContact(Contact contact);
        void startImageSelector();
        int getCurrentImage();
    }
}
