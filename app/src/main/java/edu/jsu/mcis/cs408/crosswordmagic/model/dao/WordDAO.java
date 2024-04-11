package edu.jsu.mcis.cs408.crosswordmagic.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;

import edu.jsu.mcis.cs408.crosswordmagic.model.Puzzle;
import edu.jsu.mcis.cs408.crosswordmagic.model.Word;

public class WordDAO {

    private final DAOFactory daoFactory;

    WordDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public int create(HashMap<String, String> params) {

        /* use this method if there is NOT already a SQLiteDatabase open */

        SQLiteDatabase db = daoFactory.getWritableDatabase();
        int result = create(db, params);
        db.close();
        return result;

    }

    public int create(SQLiteDatabase db, HashMap<String, String> params) {

        /* use this method if there IS already a SQLiteDatabase open */

        String puzzleid = daoFactory.getProperty("sql_field_puzzleid");
        String row = daoFactory.getProperty("sql_field_row");
        String column = daoFactory.getProperty("sql_field_column");
        String box = daoFactory.getProperty("sql_field_box");
        String direction = daoFactory.getProperty("sql_field_direction");
        String word = daoFactory.getProperty("sql_field_word");
        String clue = daoFactory.getProperty("sql_field_clue");

        ContentValues values = new ContentValues();
        values.put(puzzleid, Integer.parseInt(params.get(puzzleid)));
        values.put(row, Integer.parseInt(params.get(row)));
        values.put(column, Integer.parseInt(params.get(column)));
        values.put(box, Integer.parseInt(params.get(box)));
        values.put(direction, Integer.parseInt(params.get(direction)));
        values.put(word, params.get(word));
        values.put(clue, params.get(clue));

        int key = (int)db.insert(daoFactory.getProperty("sql_table_words"), null, values);
        Log.d("test", "Key: " + key);

        return key;

    }

    public Word find(int id) {

        /* use this method if there is NOT already a SQLiteDatabase open */

        SQLiteDatabase db = daoFactory.getWritableDatabase();
        Word result = find(db, id);
        db.close();
        return result;

    }

    public Word find(SQLiteDatabase db, int id) {

        /* use this method if there IS already a SQLiteDatabase open */

        Word result = null;

        String query = daoFactory.getProperty("sql_get_word");
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {

            HashMap<String, String> params = new HashMap<>();

            params.put("_id", cursor.getString(0));
            params.put("puzzleid", cursor.getString(1));
            params.put("row", cursor.getString(2));
            params.put("column", cursor.getString(3));
            params.put("box", cursor.getString(4));
            params.put("direction", cursor.getString(5));
            params.put("word", cursor.getString(6));
            params.put("clue", cursor.getString(7));

            // populate parameter map with field values from the database, added as strings

            result = new Word(params);

        }

        cursor.close();

        return result;

    }

}
