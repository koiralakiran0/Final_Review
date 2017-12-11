package com.example.mshehab.inclass07;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {
    private static final String ARG_PARAM1 = "contacts";
    private ArrayList<Contact> mContacts;
    private OnFragmentInteractionListener mListener;
    ListView listView;
    ContactsAdapter contactsAdapter;
    final String TAG = "demo";

    public ContactsFragment() {
        // Required empty public constructor
    }

    public static ContactsFragment newInstance(ArrayList<Contact> param1) {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContacts = (ArrayList<Contact>) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        listView = view.findViewById(R.id.listView);
        contactsAdapter = new ContactsAdapter(getContext(), R.layout.contact_item_layout, mContacts);
        listView.setAdapter(contactsAdapter);

        view.findViewById(R.id.buttonCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.startNewContactFragment();
                }
            }
        });

        return view;
    }

    class ContactsAdapter extends ArrayAdapter<Contact>{
        public ContactsAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item_layout, parent, false);
            }

            Contact contact = getItem(position);

            TextView textViewName = convertView.findViewById(R.id.textViewName);
            TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);
            TextView textViewPhone = convertView.findViewById(R.id.textViewPhone);
            TextView textViewDept = convertView.findViewById(R.id.textViewDept);
            ImageView imageView = convertView.findViewById(R.id.imageView);

            if(contact.getImgId() != -1){
                imageView.setImageResource(contact.getImgId());
            } else{
                imageView.setImageResource(R.drawable.select_avatar);
            }


            textViewName.setText(contact.getName());
            textViewEmail.setText(contact.getEmail());
            textViewDept.setText(contact.getDept());
            textViewPhone.setText(contact.getPhone());
            return convertView;
        }
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
        void startNewContactFragment();
    }
}
