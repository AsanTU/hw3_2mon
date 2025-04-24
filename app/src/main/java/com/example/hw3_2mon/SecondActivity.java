package com.example.hw3_2mon;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    private TextView resultTitle;
    private ImageButton heartBtn;
    private boolean isFavorited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        resultTitle = findViewById(R.id.titleText);
        heartBtn = findViewById(R.id.favoriteButton);

        String res = getIntent().getStringExtra("result");
        resultTitle.setText("Результат: " + res);

        heartBtn.setOnClickListener(v -> {
            isFavorited = !isFavorited;
            if (isFavorited) heartBtn.setColorFilter(ContextCompat.getColor(this, R.color.red));
            else heartBtn.setColorFilter(ContextCompat.getColor(this, R.color.gray));
        });

        Button nextBtn = findViewById(R.id.nextButton);
        nextBtn.setOnClickListener(v -> {
            finishAffinity();
        });
    }
}