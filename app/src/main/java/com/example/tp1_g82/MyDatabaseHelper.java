package com.example.tp1_g82;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "my_quiz.db";
    public static final int DATABASE_VERSION = 1;

    // Table name and column names
    public static final String TABLE_QUESTIONS = "questions";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_ANSWER = "answer";

    // Create table SQL statement
    public static final String CREATE_TABLE_QUESTIONS =
            "CREATE TABLE " + TABLE_QUESTIONS + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_QUESTION + " TEXT,"
                    + COLUMN_ANSWER + " INTEGER" + ")";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        db.execSQL(CREATE_TABLE_QUESTIONS);

        // Insert sample questions
        db.execSQL("INSERT INTO " + TABLE_QUESTIONS + " (" + COLUMN_QUESTION + ", " + COLUMN_ANSWER + ") " +
                "VALUES ('Si le Wydad Sports Club a été fondé en 1937, en quelle année la section football du Wydad Sports Club a-t-elle été créée', 1)");
        db.execSQL("INSERT INTO " + TABLE_QUESTIONS + " (" + COLUMN_QUESTION + ", " + COLUMN_ANSWER + ") " +
                "VALUES ('Le Wydad Club a marqué le premier but de son histoire grâce à feu Abdelkader El-Khamiri, alors qui était l adversaire du Wydad dans ce match :', 1)");
        db.execSQL("INSERT INTO " + TABLE_QUESTIONS + " (" + COLUMN_QUESTION + ", " + COLUMN_ANSWER + ") " +
                "VALUES ('En quelle année le Wydad a-t-il joué contre Liverpool:', 1)");
        db.execSQL("INSERT INTO " + TABLE_QUESTIONS + " (" + COLUMN_QUESTION + ", " + COLUMN_ANSWER + ") " +
                "VALUES ('Qui est le joueur honoré lors du match contre Liverpool:', 1)");
        db.execSQL("INSERT INTO " + TABLE_QUESTIONS + " (" + COLUMN_QUESTION + ", " + COLUMN_ANSWER + ") " +
                "VALUES ('Combien s est terminé le match entre le Wydad Sports Club et Tottenham Hotspur en 1980:', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);

        // Create tables again
        onCreate(db);
    }
}

