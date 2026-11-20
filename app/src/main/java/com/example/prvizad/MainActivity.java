package com.example.prvizad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;


import java.text.DecimalFormat;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);

        EditText editTextFirstNum = findViewById(R.id.editTextFirstNumber);
        EditText editTextSecondNum = findViewById(R.id.editTextSecondNumber);
        Spinner spinner = findViewById(R.id.spinnerOperator);

        Button button = findViewById(R.id.buttonCalculate);
        String toastTextfirst = "Unesite brojeve u zadane prozore";
        int duration = Toast.LENGTH_SHORT;

        DecimalFormat formatNum = new DecimalFormat("0.####");
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = editTextFirstNum.getText().toString().trim();
                String input2 = editTextSecondNum.getText().toString().trim();
                String inputOperator = spinner.getSelectedItem().toString();


                float result = 0;

                if(input1.isEmpty() || input2.isEmpty()){
                    Toast toast = Toast.makeText(MainActivity.this,toastTextfirst,duration);
                    toast.show();

                    return;
                }

                //Pretvori mi u brojeve
                float num1 = Float.parseFloat(input1);
                float num2 = Float.parseFloat(input2);

                if (inputOperator.equals("+")){
                    result = num1 + num2;
                }else if (inputOperator.equals("-")){
                    result = num1 - num2;
                }else if(inputOperator.equals("*")){
                    result = num1 * num2;
                }else if (inputOperator.equals("/")){
                    if (num2==0){
                        Toast toast = Toast.makeText(MainActivity.this,"Nije dozovoljeno djeliti sa nulom",duration);
                        toast.show();
                        return;
                    }
                    result = num1/num2;

                }

                intent.putExtra("result",result);
                startActivity(intent);

            }
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}