package vn.edu.csc.furnitureapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;

public class InfoActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtPass, edtUsername;
    RadioGroup rdbGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        edtName = findViewById(R.id.editText4);
        edtEmail = findViewById(R.id.editText);
        edtUsername = findViewById(R.id.editText2);
        edtPass = findViewById(R.id.editText3);
        getSupportActionBar().setTitle("Account Info");

        Intent intent = getIntent();
        edtName.setText(intent.getStringExtra("Username"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnuSave){

        }

        return super.onOptionsItemSelected(item);
    }
}
