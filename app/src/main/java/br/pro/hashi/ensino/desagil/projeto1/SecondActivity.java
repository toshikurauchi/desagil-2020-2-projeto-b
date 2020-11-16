package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class SecondActivity extends AppCompatActivity {

    private Button morseButton;
    private Button contactButton;
    private ImageButton checkButton;
    private ImageButton backspaceButton;
    private TextView morseText;
    private TextView translatedText;
    private ImageButton slashButton;
    private ImageButton spaceButton;
    private Translator translator;
    private Button cuidadorButton;
    //private Intent intent = getIntent();
    //private String message = intent.getStringExtra("Message");

    private void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        morseButton = findViewById(R.id.morse_btn);
        slashButton = findViewById(R.id.slashButton);
        spaceButton = findViewById(R.id.spaceButton);
        contactButton = findViewById(R.id.contactButton);
        checkButton = findViewById(R.id.aprovado);
        backspaceButton = findViewById(R.id.backspace);
        translatedText = findViewById(R.id.textMorse);
        morseText = findViewById(R.id.textTranslate);
        cuidadorButton = findViewById(R.id.cuidadorButton);

        translator = new Translator();

        Intent intent = getIntent();
        String message = intent.getStringExtra("Message");

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

        cuidadorButton.setOnClickListener((view -> {
            morseText.setText("..... ..... ..... ..... ..... ..... ..... .....");
            translatedText.setText("55555555");
        }));

        checkButton.setOnClickListener((view -> {
            String phoneNumber = translatedText.getText().toString();
            if(phoneNumber.isEmpty()){
                showToast("Número vazio ou inválido!");
                return;
            }
            if(message.isEmpty()) {
                showToast("Mensagem vazia ou inválida!");
                return;
            }
            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(phoneNumber, null, message, null, null);
            showToast("Mensagem enviada!");
            translatedText.setText("");
            morseText.setText("");
        }));

    }
}