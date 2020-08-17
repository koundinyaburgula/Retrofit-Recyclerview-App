package com.example.assignment_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.assignment_app.Viewimg.EXTRA_NAME;
import static com.example.assignment_app.Viewimg.EXTRA_URL;

public class DetailedImage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_image);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String name = intent.getStringExtra(EXTRA_NAME);

        ImageView imageView = findViewById(R.id.image_detail);
        TextView textView = findViewById(R.id.image_text);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        textView.setText(name);




    }

}
