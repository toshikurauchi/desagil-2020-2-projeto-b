package br.pro.hashi.ensino.desagil.projeto1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private ListView contactListview;
    private Timer slashTimer, spaceTimer;
    private long timerDelay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        morseButton = findViewById(R.id.morse_btn);
        contactButton = findViewById(R.id.contactButton);
        checkButton = findViewById(R.id.aprovado);
        backspaceButton = findViewById(R.id.backspace);
        translatedText = findViewById(R.id.textMorse);
        morseText = findViewById(R.id.textTranslate);
        contactListview = findViewById(R.id.Lista_contato);


        translator = new Translator();

        timerDelay = 1600;

        Intent intent = getIntent();
        String message = intent.getStringExtra("Message");

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


        LinkedList<Contact> contacts = new LinkedList<>();
        contacts.add(new Contact("Gabriel", "1234") );
        contacts.add(new Contact("Andresa", "5678") );
        contacts.add(new Contact("Toshi", "1234") );
        contacts.add(new Contact("Caio", "5678") );
        
        contactListview.setAdapter(new ArrayAdapter<Contact>(this,android.R.layout.simple_list_item_2, android.R.id.text1, contacts){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(contacts.get(position).getName());
                text2.setText(contacts.get(position).getPhone());
                return view;
            }
        });


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

    private void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}