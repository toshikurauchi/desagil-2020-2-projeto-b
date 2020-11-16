package br.pro.hashi.ensino.desagil.projeto1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
    private static final int REQUEST_SEND_SMS = 0;
    private void startSMSActivity(String message) {
        Intent intent = new Intent(this, SecondActivity.class);
        //Bundle bundle = new Bundle();
        //bundle.putString("Message", message);
        intent.putExtra("Message", message);
        startActivity(intent);
    }

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

        checkButton.setOnClickListener((view) -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                startSMSActivity(translatedText.getText().toString());
            } else {
                String[] permissions = new String[]{
                        Manifest.permission.SEND_SMS,
                };
                ActivityCompat.requestPermissions(this, permissions, REQUEST_SEND_SMS);
            }
        });

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