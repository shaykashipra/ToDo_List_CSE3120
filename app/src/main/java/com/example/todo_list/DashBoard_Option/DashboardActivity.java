package com.example.todo_list.DashBoard_Option;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todo_list.App_Options.AboutActivity;
import com.example.todo_list.App_Options.AccountActivity;
import com.example.todo_list.App_Options.ContactActivity;
import com.example.todo_list.App_Options.HelpActivity;
import com.example.todo_list.App_Options.PrivacyActivity;
import com.example.todo_list.App_Options.SettingsActivity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.example.todo_list.LoginSignup.LoginActivity;
import com.example.todo_list.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private TextView userNameTextView;
    private TextView userEmailTextView;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
         NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Initialize views
        View headerView = navigationView.getHeaderView(0);
        userNameTextView = headerView.findViewById(R.id.userName);
        userEmailTextView = headerView.findViewById(R.id.userEmail);




        // Initialize Firebase components
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Retrieve the user's email from Firebase Authentication
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            String userEmail = user.getEmail();
            userEmailTextView.setText(userEmail);
        }

        // Retrieve the user's name from Firebase Realtime Database
        String userId = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference userRef = databaseReference.child("users").child(userId);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String userName = dataSnapshot.child("name").getValue(String.class);
                    userNameTextView.setText(userName);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
                Toast.makeText(DashboardActivity.this, "Failed to retrieve user data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        userNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // Replace the fragment with AccountFragment
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();
//                // Close the drawer (optional)
//                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        userEmailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // Replace the fragment with AccountFragment
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();
//                // Close the drawer (optional)
//                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new OptionFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new OptionFragment()).commit();
        } else if (itemId == R.id.nav_settings) {
           Intent intent=new Intent(DashboardActivity.this, SettingsActivity.class);
              startActivity(intent);
        } else if (itemId == R.id.nav_share) {
            // Show sharing options
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "ToDo List App");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out ToDo Code: https://github.com/shaykashipra/ToDo-List");
            startActivity(Intent.createChooser(shareIntent, "Share App"));
        } else if (itemId == R.id.nav_help) {
            Intent intent=new Intent(DashboardActivity.this, HelpActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.nav_account) {
            Intent intent=new Intent(DashboardActivity.this, AccountActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.nav_about) {
            Intent intent=new Intent(DashboardActivity.this, AboutActivity.class);
            startActivity(intent);

        } else if (itemId == R.id.nav_contact) {
            Intent intent=new Intent(DashboardActivity.this, ContactActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.nav_privacy) {
            Intent intent=new Intent(DashboardActivity.this, PrivacyActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.nav_logout) {
            // Handle the logout activity
            // Perform logout

               FirebaseAuth.getInstance().signOut();

            // Redirect to login or splash screen
                Toast.makeText(this, "Logout Successfully!", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish(); // Optional: finish the current activity to prevent going back to it
        }

        // Minimize the side menu
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}