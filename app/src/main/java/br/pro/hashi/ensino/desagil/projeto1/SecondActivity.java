package br.pro.hashi.ensino.desagil.projeto1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private Button morseButton;
    private Button contactButton;
    private ImageButton checkButton;
    private ImageButton backspaceButton;
    private ImageButton plusButton;
    private ImageButton arrowButton;
    private TextView phoneText;
    private TextView phoneMorseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        morseButton = findViewById(R.id.morse_btn);
        contactButton = findViewById(R.id.contactButton);
        checkButton = findViewById(R.id.check);
        backspaceButton = findViewById(R.id.backspace);
        plusButton = findViewById(R.id.mais);
        arrowButton = findViewById(R.id.seta);
        phoneText = findViewById(R.id.phoneText);
        phoneMorseText = findViewById(R.id.phoneMorse);

        morseButton.setOnClickListener((view -> {
            phoneText.setText(phoneText.getText() + "Clicked ");
            phoneMorseText.setText(phoneMorseText.getText() + "Clicked ");
        }));

        backspaceButton.setOnClickListener((view -> {
            if(phoneText.getText().toString().length() > 0) {
                phoneText.setText(phoneText.getText().toString().substring(0, phoneText.getText().toString().length() - 1));
            }
            if(phoneMorseText.getText().toString().length() > 0) {
                phoneMorseText.setText(phoneMorseText.getText().toString().substring(0, phoneMorseText.getText().toString().length() - 1));
            }
        }));

    }
}