package com.example.tp1_g82;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Quiz5 extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb;
    Button bNext;
    int score;
//    String RepCorrect="Non";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz5);

        // Retrieve the question and answer from the database
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(MyDatabaseHelper.TABLE_QUESTIONS,
                new String[] {MyDatabaseHelper.COLUMN_QUESTION, MyDatabaseHelper.COLUMN_ANSWER},
                MyDatabaseHelper.COLUMN_ID + " = ?", new String[] {"5"},
                null, null, null);
        cursor.moveToFirst();
        @SuppressLint("Range") String question = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_QUESTION));
        @SuppressLint("Range") int answer = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_ANSWER));
        cursor.close();

        // Display the question on the screen
        TextView textViewQuestion = findViewById(R.id.tvQuiz5);
        textViewQuestion.setText(question);

        rg=(RadioGroup) findViewById(R.id.rg);
        bNext=(Button) findViewById(R.id.btnNext);
        Intent intent=getIntent();
        score=intent.getIntExtra("score",0) ;
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rg.getCheckedRadioButtonId()==-1){
                    Toast.makeText(getApplicationContext(),"Merci de choisir une réponse S.V.P !",Toast.LENGTH_SHORT).show();
                }
                else {
                    rb = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                    String userAnswer = rb.getText().toString();
                    String correctAnswer = (answer == 1) ? "4-2" : "3-2";
                    if(userAnswer.equals(correctAnswer)){
                        score+=1;
                    }
                    Intent intent=new Intent(Quiz5.this,Score.class);
                    intent.putExtra("score",score);
                    startActivity(intent);
                    overridePendingTransition(R.anim.exit,R.anim.entry);
                    finish();
                }

            }
        });

    }
}
