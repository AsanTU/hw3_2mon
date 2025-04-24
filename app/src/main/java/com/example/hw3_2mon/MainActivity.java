package com.example.hw3_2mon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private String currentInput = "";
    private boolean isOperationClick = false;
    private Button resultBtn;

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
        resultBtn = findViewById(R.id.btn_show_result);
    }

    public void onNumberClick(View view) {
        String textBtn = ((MaterialButton) view).getText().toString();
        String current = textRes.getText().toString();

        if (textBtn.equals("AC")) {
            textRes.setText("0");
            firstVar = 0;
            operation = "";
            currentInput = "";
            resultBtn.setVisibility(View.GONE);
            return;
        }

        if (isOperationClick || current.equals("0")) textRes.setText(textBtn);
        else textRes.append(textBtn);
        isOperationClick = false;
        currentInput += textBtn;
        resultBtn.setVisibility(View.GONE);
    }

    public void onOperationClick(View view) {
        String op = ((MaterialButton) view).getText().toString();

        if (op.equals("=")) {
            double secondVar = Double.parseDouble(currentInput);
            double result = 0;

            switch (operation){
                case "+":
                    result = firstVar + secondVar;
                    break;
                case "-":
                    result = firstVar - secondVar;
                    break;
                case "X":
                    result = firstVar * secondVar;
                    break;
                case "/":
                    if (secondVar != 0) {
                        result = firstVar / secondVar;
                    } else {
                        textRes.setText("Ошибка");
                        return;
                    }
                    break;
            }

            textRes.setText(removeDot(result));
            currentInput = String.valueOf(result);
            resultBtn.setVisibility(View.VISIBLE);
        } else {
            firstVar = Double.parseDouble(textRes.getText().toString());
            operation = op;
            isOperationClick = true;
            currentInput = "";
            resultBtn.setVisibility(View.GONE);
        }
    }

    @SuppressLint("DefaultLocale")
    private String removeDot(double res){
        if (res == (long) res) return String.format("%d", (long) res);
        else return String.format("%s", res);
    }

    public void onShowResultClick(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("result", textRes.getText().toString());
        startActivity(intent);
    }
}