package com.example.godeeadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class DriverAccountActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore db;
    private ProfileAdapter adapter;
    private List<UserModel> profileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_account);
        getSupportActionBar().hide();

        BottomNavigationView navigationView = findViewById(R.id.page_navigation);
        navigationView.setSelectedItemId(R.id.activity_driver);
        pageNavigation(navigationView);

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.driverRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db.collection("drivers").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                profileList = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    if (document != null) {
                        UserModel userModel = document.toObject(UserModel.class);
                        profileList.add(userModel);

                    }
                }
                adapter = new ProfileAdapter(profileList);
                recyclerView.setAdapter(adapter);
            }
        });
    }


    public void pageNavigation(BottomNavigationView driverPageMenu) {
        driverPageMenu.setOnItemSelectedListener(item ->
        {   int itemId = item.getItemId();
            if (itemId == R.id.activity_home){
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
            if (itemId == R.id.activity_user){
                Intent intent = new Intent(getApplicationContext(), UserAccountActivity.class);
                startActivity(intent);
            }
            if (itemId == R.id.activity_driver){
                return true;
            }
            // This part is expandable base on the number of tab need for the application
            /* Structure
               if (itemId == R.id.[name of the item]){
               Intent intent = new Intent(getApplicationContext(), [insert the activity associated with the icon].class);
               startActivity(intent);
               finish();}
             */
            return true;
        });
    }
}