package com.example.mshehab.firebasedemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {
    ListView listView;
    PersonAdapter personAdapter;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    ArrayList<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        persons = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        mDatabase.child("contacts").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                persons.clear();
                for (DataSnapshot node : dataSnapshot.getChildren() ) {

                    Person person = node.getValue(Person.class);
                    person.key = node.getKey();
                    persons.add(person);

                }

                personAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView = findViewById(R.id.listView);
        personAdapter = new PersonAdapter(this,R.layout.contact_item, persons);
        listView.setAdapter(personAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mDatabase.child("contacts")
                        .child(mAuth.getCurrentUser().getUid())
                        .child(persons.get(i).key)
                        .removeValue();
                return false;
            }
        });


        findViewById(R.id.buttonCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactsActivity.this, NewContactActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //need to include the logout code here .

                SharedPreferences prefs = getSharedPreferences("info", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("email", mAuth.getCurrentUser().getEmail());
                editor.commit();

                mAuth.signOut();
                Intent intent = new Intent(ContactsActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
