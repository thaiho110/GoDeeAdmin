package com.example.godeeadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class UserAccountActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore db;

    private ProfileAdapter adapter;
    private List<UserModel>profileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.userRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationView navigationView = findViewById(R.id.page_navigation);
        navigationView.setSelectedItemId(R.id.activity_user);
        pageNavigation(navigationView);


        adapter = new ProfileAdapter(profileList);
        recyclerView.setAdapter(adapter);
        db.collection("users").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                profileList = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String name = (String) document.get("name");
                    String email = (String) document.get("name");
                    String phone = (String) document.get("name");
                    int age = (int) document.get("name");
                    String nationality = (String) document.get("name");
                    userAddress userAddress = (com.example.godeeadmin.userAddress) document.get("address");
                    int type = (int) document.get("name");
                    UserModel userModel = new UserModel(name, email, phone, age, nationality, userAddress, 100);
                    profileList.add(userModel);
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
                return true;
            }
            if (itemId == R.id.activity_driver){
                Intent intent = new Intent(getApplicationContext(), DriverAccountActivity.class);
                startActivity(intent);

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

    private void fetchSessionsFromFirebase() {
        db = FirebaseFirestore.getInstance();
        db.collection("users").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                profileList = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String name = (String) document.get("name");
                    String email = (String) document.get("name");
                    String phone = (String) document.get("name");
                    int age = (int) document.get("name");
                    String nationality = (String) document.get("name");
                    userAddress userAddress = (com.example.godeeadmin.userAddress) document.get("address");
                    int type = (int) document.get("name");
                    UserModel userModel = new UserModel(name, email,phone,age,nationality,userAddress,100);
                    profileList.add(userModel);
                }
                adapter = new ProfileAdapter(profileList);
                recyclerView.setAdapter(adapter);
            } else {
                // Handle errors
            }
        });
    }


}