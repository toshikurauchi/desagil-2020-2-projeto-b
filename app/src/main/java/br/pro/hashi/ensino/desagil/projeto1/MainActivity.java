package br.pro.hashi.ensino.desagil.projeto1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageButton checkButton, backspaceButton;
    private Button morseButton;
    private TextView morseText, translatedText;
    private Translator translator;
    private Timer slashTimer, spaceTimer;
    private ListView messageListview;
    private long timerDelay;
    private static final int REQUEST_SEND_SMS = 0;
    private Button saveQuoteButton;
    private boolean mustSave;
    private ArrayAdapter linkedListAdapter;
    private LinkedList<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        morseButton = findViewById(R.id.morse_btn);


        checkButton = findViewById(R.id.check);
        backspaceButton = findViewById(R.id.backspace);
        translatedText = findViewById(R.id.textMorse);
        morseText = findViewById(R.id.textTranslate);
        messageListview = findViewById(R.id.Lista_mensagens);
        saveQuoteButton = findViewById(R.id.contactButton);
        mustSave = false;
        messageListview = findViewById(R.id.Lista_mensagens);
        messages = new LinkedList<>();

        linkedListAdapter = new ArrayAdapter<Message>(this, android.R.layout.simple_list_item_2, android.R.id.text1, messages){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);

                text1.setText(messages.get(position).getName());
                return view;
            }
        };

        messageListview.setAdapter(linkedListAdapter);

        messageListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Message message = (Message) parent.getItemAtPosition(position);

                translatedText.setText(message.getName() + " ");
                morseText.setText("");

                char[] substring = message.getName().toCharArray();

                for(char c : substring){
                    morseText.setText(morseText.getText() + translator.charToMorse(c) + " ");
                }
                morseText.setText(morseText.getText() + "/ ");
            }
        });

        translator = new Translator();

        timerDelay = 1600;

        saveQuoteButton.setOnClickListener((view -> {
            if(mustSave){
                mustSave = false;
                morseText.setHint("  Entre com sua mensagem...");
                translatedText.setHint("  Entre com sua mensagem...");
            } else {
                mustSave = true;
                morseText.setHint("  Entre com a frase para salvar");
                translatedText.setHint("  Entre com a frase para salvar");
            }
        }));

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

        checkButton.setOnClickListener((view) -> {
            if(!mustSave){
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    startSMSActivity(translatedText.getText().toString());
                } else {
                    String[] permissions = new String[]{
                            Manifest.permission.SEND_SMS,
                    };
                    ActivityCompat.requestPermissions(this, permissions, REQUEST_SEND_SMS);
                }
            } else {
                messages.add(new Message(translatedText.getText().toString()));
                linkedListAdapter.notifyDataSetChanged();
                morseText.setText("");
                translatedText.setText("");
                morseText.setHint("  Entre com sua mensagem...");
                translatedText.setHint("  Entre com sua mensagem...");
                mustSave = false;
            }
        });
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

    private void startSMSActivity(String message) {
        Intent intent = new Intent(this, SecondActivity.class);
        //Bundle bundle = new Bundle();
        //bundle.putString("Message", message);
        intent.putExtra("Message", message);
        startActivity(intent);
    }

}