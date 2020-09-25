package vn.edu.csc.furnitureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView  txtDescription;
    ImageView imageView;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        utils = new Utils(DetailActivity.this);

        imageView = findViewById(R.id.imageView2);
        txtDescription = findViewById(R.id.txtDescription);

        Intent intent = getIntent();
        Furniture furniture = (Furniture) intent.getSerializableExtra("furniture");
        txtDescription.setText(furniture.getDescription());
        imageView.setImageBitmap(utils.convertStringToBitmapFromAccess(furniture.getImage()));
        getSupportActionBar().setTitle(furniture.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
