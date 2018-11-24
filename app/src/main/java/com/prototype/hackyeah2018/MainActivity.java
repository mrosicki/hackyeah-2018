package com.prototype.hackyeah2018;

import com.prototype.hackyeah2018.db.AppDatabase;
import com.prototype.hackyeah2018.reader.ReaderCaptureActivity;

import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = AppDatabase.getInstance(this);
        final Button takePicture= findViewById(R.id.buttonPicture);


        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ReaderCaptureActivity.class);
                startActivity(intent);
            }
        });


        if (getIntent().getStringArrayExtra("Array")!=null){
            String[] textArray = getIntent().getStringArrayExtra("Array");
            for (int i = 0;i<textArray.length;i++){
                System.out.println("MainActivity: " + i + " " + textArray[i]);
            }
        }


    }
}
