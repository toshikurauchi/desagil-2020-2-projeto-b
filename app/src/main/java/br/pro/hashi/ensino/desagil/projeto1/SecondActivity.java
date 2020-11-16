package br.pro.hashi.ensino.desagil.projeto1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class SecondActivity extends AppCompatActivity {

    private Button morseButton;
    private Button contactButton;
    private ImageButton checkButton;
    private ImageButton backspaceButton;
    private TextView morseText;
    private TextView translatedText;
    private Translator translator;
    private Button cuidadorButton;
    private Timer slashTimer, spaceTimer;
    private long timerDelay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        morseButton = findViewById(R.id.morse_btn);
        contactButton = findViewById(R.id.contactButton);
        checkButton = findViewById(R.id.check);
        backspaceButton = findViewById(R.id.backspace);
        translatedText = findViewById(R.id.textMorse);
        morseText = findViewById(R.id.textTranslate);
        cuidadorButton = findViewById(R.id.cuidadorButton);

        translator = new Translator();

        timerDelay = 1600;

        morseButton.setOnClickListener((view -> {
            morseText.setText(morseText.getText() + ".");

            if(slashTimer != null) {
                slashTimer.cancel();
                spaceTimer.cancel();
            }
            createSlashTimer();
            createSpaceTimer();

        }));

        morseButton.setOnLongClickListener((view -> {
            morseText.setText(morseText.getText() + "-");

            if(slashTimer != null) {
                slashTimer.cancel();
                spaceTimer.cancel();
            }
            createSlashTimer();
            createSpaceTimer();

            return true;
        }));

        backspaceButton.setOnClickListener((view -> {
            if(morseText.getText().toString().length() > 0) {
                String[] substringsMorse = morseText.getText().toString().split(" ");
                morseText.setText("");

                for(int i = 0; i < substringsMorse.length - 1; i++){
                    morseText.setText(morseText.getText() + substringsMorse[i] + " ");
                }
            }
            if(translatedText.getText().toString().length() > 0) {
                translatedText.setText(translatedText.getText().toString().substring(0, translatedText.getText().toString().length() - 1));
            }

            if(slashTimer != null) {
                slashTimer.cancel();
            }
            createSlashTimer();

        }));

        cuidadorButton.setOnClickListener((view -> {
            morseText.setText("..... ..... ..... ..... ..... ..... ..... .....");
            translatedText.setText("55555555");
        }));

    }

    private void createSlashTimer() {
        TimerTask slashTask = new TimerTask() {
            @Override
            public void run() {
                if(morseText.getText().length() == 0){
                    return;
                }
                morseText.setText(morseText.getText() + "/ ");
                translatedText.setText(translatedText.getText().toString() + " ");
            }
        };

        slashTimer = new Timer();

        slashTimer.schedule(slashTask, timerDelay);
    }

    private void createSpaceTimer() {
        TimerTask spaceTask = new TimerTask() {
            @Override
            public void run() {
                morseText.setText(morseText.getText() + " ");

                String[] morseTextArray = morseText.getText().toString().split(" ");
                char newLetter = translator.morseToChar(morseTextArray[morseTextArray.length - 1]);

                if(newLetter == '@'){
                    deleteMistake();
                    return;
                }

                translatedText.setText(translatedText.getText().toString() + newLetter);
            }
        };

        spaceTimer = new Timer();

        spaceTimer.schedule(spaceTask, (int)(timerDelay * (3.0 / 7.0)));
    }

    private void deleteMistake(){
        String[] substringsMorse = morseText.getText().toString().split(" ");
        morseText.setText("");

        for(int i = 0; i < substringsMorse.length - 1; i++){
            morseText.setText(morseText.getText() + substringsMorse[i] + " ");
        }

        slashTimer.cancel();
        spaceTimer.cancel();
    }
}