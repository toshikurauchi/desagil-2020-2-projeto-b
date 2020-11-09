package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button morseButton;
    private ImageButton checkButton;
    private ImageButton backspaceButton;
    private TextView morseText;
    private TextView translatedText;
    private Translator translator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        morseButton = findViewById(R.id.morse_btn);
        checkButton = findViewById(R.id.check);
        backspaceButton = findViewById(R.id.backspace);
        translatedText = findViewById(R.id.textMorse);
        morseText = findViewById(R.id.textTranslate);

        translator = new Translator();

        morseButton.setOnClickListener((view -> {
            morseText.setText(morseText.getText() + ".");
        }));

        morseButton.setOnLongClickListener((view -> {
            morseText.setText(morseText.getText() + "-");
            return true;
        }));

        backspaceButton.setOnClickListener((view -> {
            if(morseText.getText().toString().length() > 0) {
                morseText.setText(morseText.getText().toString().substring(0, morseText.getText().toString().length() - 1));
            }
            if(translatedText.getText().toString().length() > 0) {
                translatedText.setText(translatedText.getText().toString().substring(0, translatedText.getText().toString().length() - 1));
            }
        }));

        checkButton.setOnClickListener((view -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }));
    }
}