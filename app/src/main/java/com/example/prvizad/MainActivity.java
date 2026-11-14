package com.example.prvizad;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);

        TextView textViewResult = findViewById(R.id.textViewMessage);

        EditText editTextFirstNum = findViewById(R.id.editTextFirstNumber);
        EditText editTextSecondNum = findViewById(R.id.editTextSecondNumber);
        EditText editTextOperator = findViewById(R.id.editTextOperator);

        Button button = findViewById(R.id.buttonCalculate);
        String toastTextfirst = "Unesite brojeve u zadane prozore";
        int duration = Toast.LENGTH_SHORT;

        DecimalFormat formatNum = new DecimalFormat("0.####");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = editTextFirstNum.getText().toString().trim();
                String input2 = editTextSecondNum.getText().toString().trim();
                String inputOperator = editTextOperator.getText().toString();
                String[] arrOfStrings ={"+","-","*","/"};

                float result = 0;

                if(input1.isEmpty() || input2.isEmpty()){
                    Toast toast = Toast.makeText(MainActivity.this,toastTextfirst,duration);
                    toast.show();

                    return;
                }

                if(!Arrays.asList(arrOfStrings).contains(inputOperator)){
                    Toast toast = Toast.makeText(MainActivity.this,"Unesite pravi operator",duration);
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


                textViewResult.setText(formatNum.format(result));

            }
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}