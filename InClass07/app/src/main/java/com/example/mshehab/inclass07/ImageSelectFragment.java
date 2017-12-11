package com.example.mshehab.inclass07;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ImageSelectFragment extends Fragment implements View.OnClickListener{
    private OnFragmentInteractionListener mListener;

    public ImageSelectFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_select, container, false);

        view.findViewById(R.id.imageViewF1).setOnClickListener(this);
        view.findViewById(R.id.imageViewF2).setOnClickListener(this);
        view.findViewById(R.id.imageViewF3).setOnClickListener(this);
        view.findViewById(R.id.imageViewM1).setOnClickListener(this);
        view.findViewById(R.id.imageViewM2).setOnClickListener(this);
        view.findViewById(R.id.imageViewM3).setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        int id = -1;
        if(view.getId() == R.id.imageViewF1){
            id = R.drawable.avatar_f_1;
        } else if(view.getId() == R.id.imageViewF2){
            id = R.drawable.avatar_f_2;
        } else if(view.getId() == R.id.imageViewF3){
            id = R.drawable.avatar_f_3;
        } else if(view.getId() == R.id.imageViewM1){
            id = R.drawable.avatar_m_1;
        } else if(view.getId() == R.id.imageViewM2){
            id = R.drawable.avatar_m_2;
        } else if(view.getId() == R.id.imageViewM3){
            id = R.drawable.avatar_m_3;
        }
        mListener.sendNewImageId(id);
    }

    public interface OnFragmentInteractionListener {
        void sendNewImageId(int id);
    }
}
