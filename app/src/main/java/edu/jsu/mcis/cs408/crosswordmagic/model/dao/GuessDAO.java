package edu.jsu.mcis.cs408.crosswordmagic.model.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;

public class GuessDAO {

    private final DAOFactory daoFactory;

    GuessDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public int create(int puzzleid, int wordid) {

        /* use this method if there is NOT already a SQLiteDatabase open */

        SQLiteDatabase db = daoFactory.getWritableDatabase();
        int result = create(db, puzzleid, wordid);
        db.close();
        return result;

    }

    public int create(SQLiteDatabase db, int puzzleid, int wordid) {

        /* use this method if there IS already a SQLiteDatabase open */

        String puzzleidField = daoFactory.getProperty("sql_field_puzzleid");
        String wordidField = daoFactory.getProperty("sql_field_wordid");

        ContentValues values = new ContentValues();
        values.put(puzzleidField, puzzleid);
        values.put(wordidField, wordid);

        int key = (int)db.insert(daoFactory.getProperty("sql_table_guesses"), null, values);
        Log.d("test", "Key: " + key);

        return key;

    }
}
