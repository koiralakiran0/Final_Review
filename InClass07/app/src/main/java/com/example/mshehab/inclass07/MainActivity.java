package com.example.mshehab.inclass07;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactsFragment.OnFragmentInteractionListener,
        CreateContactFragment.OnFragmentInteractionListener, ImageSelectFragment.OnFragmentInteractionListener{
    ArrayList<Contact> contacts = new ArrayList<>();
    int imgId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentView, ContactsFragment.newInstance(contacts), "ContactsFragment")
                .commit();
    }

    @Override
    public void startNewContactFragment() {
        imgId = -1;
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.contentView, new CreateContactFragment(), "CreateFragment")
                .commit();
    }

    @Override
    public void addContact(Contact contact) {
        contacts.add(contact);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void startImageSelector() {
        imgId = -1;
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.contentView, new ImageSelectFragment(), "ImageFragment")
                .commit();
    }

    @Override
    public int getCurrentImage() {
        return this.imgId;
    }

    @Override
    public void sendNewImageId(int id) {
        imgId = id;
        getSupportFragmentManager().popBackStack();
    }
}
