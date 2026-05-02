package com.stancurrency.convertly;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Variables
    EditText inputAmount;
    Spinner CurrSpinner;
    Button convertBtn;
    TextView resultOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Assigning variables
        inputAmount = findViewById(R.id.inputAmount);
        CurrSpinner = findViewById(R.id.CurrSpinner);
        convertBtn = findViewById(R.id.convertBtn);
        resultOutput = findViewById(R.id.resultOutput);

        //Currency Choices (Spinner)
        String[] currencies = {"EUR", "USD", "KSH"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CurrSpinner.setAdapter(adapter);

        //Now the conversion button
        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        String input = inputAmount.getText().toString();
        //mitigates empty field
        if (input.isEmpty()) {
            resultOutput.setText("Please enter an amount.");
            return;
        }
        double amount = Double.parseDouble(input);
        String selectedCurrency = CurrSpinner.getSelectedItem().toString();
        double convertedAmount = 0;

        //We use switch for the conversion. NOTE : These are hardcoded(preset) values.
        switch (selectedCurrency) {
            case "EUR":
                convertedAmount = amount * 1.09;
                break;
            case "USD":
                convertedAmount = amount * 1.1;
                break;
            case "KSH":
                convertedAmount = amount * 1;
                break;
        }
        resultOutput.setText(convertedAmount + " " + selectedCurrency);
    }
}
