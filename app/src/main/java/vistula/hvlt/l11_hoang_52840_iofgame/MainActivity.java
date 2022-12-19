package vistula.hvlt.l11_hoang_52840_iofgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Spinner spinner_games;
    TextView tv_resultNumber;
    TextView tv_resultImage;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_resultImage = findViewById(R.id.tv_resultImage);
        tv_resultNumber = findViewById(R.id.tv_resultNumber);
        spinner_games = findViewById(R.id.spinner_games);
        imageView = findViewById(R.id.imageView);



        spinner_games.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int position = spinner_games.getSelectedItemPosition();
                switch (position) {
                    case 0:
                        imageView.setImageResource(R.drawable.number_game);
                        imageView.setOnClickListener(view1 -> {
                            Intent intent = new Intent(getApplicationContext(), NumberGame.class);
                            startActivity(intent);
                        });
                        break;
                    case 1:
                        imageView.setImageResource(R.drawable.img_game);
                        imageView.setOnClickListener(view12 -> {
                            Intent intent = new Intent(getApplicationContext(), ImageGame.class);
                            startActivity(intent);
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        readFile(tv_resultNumber, ConstantVariables.FILE_STORE_NUMBER_GAME);
        readFile(tv_resultImage, ConstantVariables.FILE_STORE_IMAGE_GAME);
    }

    public void readFile(TextView tv, String fileName){
        int readBlockSize = 100;
        try{
            FileInputStream inputFile = openFileInput(fileName);
            InputStreamReader inputFileReader = new InputStreamReader(inputFile);
            char[] inputBuffer = new char[readBlockSize];
            String str = "";
            int charRead;
            while((charRead = inputFileReader.read(inputBuffer)) > 0){
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                str += readString;
                inputBuffer = new char[readBlockSize];
                Log.d("String Score: ", str);
            }
            tv.setText(str);
        }catch (Exception e){
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}