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
    private Button frase1Button;
    private Button frase2Button;
    private TextView morseText;
    private TextView translatedText;
    private ImageButton slashButton;
    private ImageButton spaceButton;
    private Translator translator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        morseButton = findViewById(R.id.morse_btn);
        slashButton = findViewById(R.id.slashButton);
        spaceButton = findViewById(R.id.spaceButton);

        frase1Button = findViewById(R.id.frasePronta1);
        frase2Button = findViewById(R.id.frasePronta2);

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

        slashButton.setOnClickListener((view -> {
            morseText.setText(morseText.getText() + " / ");

            String[] morseTextArray = morseText.getText().toString().split(" ");
            char newLetter = translator.morseToChar(morseTextArray[morseTextArray.length - 2]);
            translatedText.setText(translatedText.getText().toString() + newLetter + " ");
        }));

        spaceButton.setOnClickListener((view -> {
            morseText.setText(morseText.getText() + " ");

            String[] morseTextArray = morseText.getText().toString().split(" ");
            char newLetter = translator.morseToChar(morseTextArray[morseTextArray.length - 1]);
            translatedText.setText(translatedText.getText().toString() + newLetter);
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

        frase1Button.setOnClickListener((view -> {
            morseText.setText(morseText.getText() + " ... --- ...");
            translatedText.setText(translatedText.getText() + " SOS");
        }));

        frase2Button.setOnClickListener((view -> {
            morseText.setText(morseText.getText() + " .-.. --- --. --- / - . / .-. . ... .--. --- -. -.. ---");
            translatedText.setText(translatedText.getText() + " Logo te respondo");
        }));
    }
}