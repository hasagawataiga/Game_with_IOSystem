package vistula.hvlt.l11_hoang_52840_iofgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Random;

public class ImageGame extends AppCompatActivity {
    TextView tv_scoreImageGame;
    Spinner spinner_animals;
    Button btn_returnHome;
    Button btn_goToNumberGame;
    ImageView imageView2;
    String[] animalArray = new String[]{
            "bear", "crocodile", "dog",
            "cat", "mouse", "chicken"
    };
    String result = "";
    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_game);
        tv_scoreImageGame = findViewById(R.id.tv_scoreImageGame);
        btn_returnHome = findViewById(R.id.btn_returnHome2);
        btn_goToNumberGame = findViewById(R.id.btn_goToNumberGame);
        spinner_animals = findViewById(R.id.spinner_animals);
        imageView2 = findViewById(R.id.imageView2);
        tv_scoreImageGame.setText(Integer.toString(score));
        takeTheRandom();
        spinner_animals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String answer = spinner_animals.getSelectedItem().toString();
                Log.d("answer: ", answer);
                if (!answer.equals("Select answer:")) {
                    if (result.equals(answer)) {
                        score++;
                        tv_scoreImageGame.setText(Integer.toString(score));
                    } else {
                        if (score > 0) {
                            score--;
                            tv_scoreImageGame.setText(Integer.toString(score));
                        }
                    }
                    Log.d("score:", Integer.toString(score));
                    takeTheRandom();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void takeTheRandom(){
        Random rd = new Random();
        int randomInt = rd.nextInt(6);
        result = animalArray[randomInt];
        int resId = getResources().getIdentifier(result, "drawable", getPackageName());
        imageView2.setImageResource(resId);
    }

    private void writeScore(){
        try{
            FileOutputStream outputFile = openFileOutput(ConstantVariables.FILE_STORE_IMAGE_GAME, MODE_PRIVATE);
            Log.d("Channel: ", String.valueOf(outputFile.getChannel()));
            OutputStreamWriter outputStreamWrite = new OutputStreamWriter(outputFile);
            try{
                outputStreamWrite.write(Integer.toString(score));
            }catch(Exception e){
                Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }
            outputStreamWrite.flush();
            outputStreamWrite.close();
        }catch (Exception e){
            Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void returnHome2(View view){
        writeScore();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToNumberGame(View view){
        writeScore();
        Intent intent = new Intent(this, NumberGame.class);
        startActivity(intent);
    }

}