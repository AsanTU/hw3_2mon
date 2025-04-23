package com.example.hw3_2mon;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textRes;
    private double firstVar = 0;
    private String operation = "";
    private boolean isOperationClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textRes = findViewById(R.id.tv_res);
    }

    public void onNumberClick(View view) {
        String textBtn = ((MaterialButton) view).getText().toString();
        String current = textRes.getText().toString();

        if (textBtn.equals("AC")) {
            textRes.setText("0");
            firstVar = 0;
            operation = "";
            return;
        }

        if (isOperationClick || current.equals("0")) textRes.setText(textBtn);
        else textRes.append(textBtn);
        isOperationClick = false;
    }

    public void onOperationClick(View view) {
        String op = ((MaterialButton) view).getText().toString();

        if (op.equals("=")) {
            double secondVar = Double.parseDouble(textRes.getText().toString());

            switch (operation){
                case "+":
                    textRes.setText(removeDot(firstVar + secondVar));
                    break;
                case "-":
                    textRes.setText(removeDot(firstVar - secondVar));
                    break;
                case "X":
                    textRes.setText(removeDot(firstVar * secondVar));
                    break;
                case "/":
                    if (secondVar != 0) textRes.setText(removeDot(firstVar / secondVar));
                    else textRes.setText("Ошибка");
                    break;
            }
        } else {
            firstVar = Double.parseDouble(textRes.getText().toString());
            operation = op;
            isOperationClick = true;
        }
    }

    @SuppressLint("DefaultLocale")
    private String removeDot(double res){
        if (res == (long) res) return String.format("%d", (long) res);
        else return String.format("%s", res);
    }
}