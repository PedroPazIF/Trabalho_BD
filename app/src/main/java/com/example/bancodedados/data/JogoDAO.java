package com.example.bancodedados.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class JogoDAO {
    private static JogoDAO instance;

    private SQLiteDatabase db;

    private JogoDAO(Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    //singleton
    public static JogoDAO getInstance(Context context) {
        if (instance == null) {
            instance = new JogoDAO(context.getApplicationContext());
        }
        return instance;
    }

    public List<Jogo> list() {

        String[] columns = {
                JogosContract.Columns._ID,
                JogosContract.Columns.NOME,
                JogosContract.Columns.VALOR
        };

        List<Jogo> jogos = new ArrayList<>();

        try (Cursor c = db.query(JogosContract.TABLE_NAME, columns, null, null, null, null, JogosContract.Columns.NOME)) {
            if (c.moveToFirst()) {
                do {
                    Jogo p = JogoDAO.fromCursor(c);
                    jogos.add(p);
                } while (c.moveToNext());
            }

            return jogos;
        }

    }

    private static Jogo fromCursor(Cursor c) {
        @SuppressLint("Range") int id = c.getInt(c.getColumnIndex(JogosContract.Columns._ID));
        @SuppressLint("Range") String nome = c.getString(c.getColumnIndex(JogosContract.Columns.NOME));
        @SuppressLint("Range") double valor = c.getDouble(c.getColumnIndex(JogosContract.Columns.VALOR));
        return new Jogo(id, nome, valor);
    }

    public void save(Jogo jogo) {
        ContentValues values = new ContentValues();
        values.put(JogosContract.Columns.NOME, jogo.getNome());
        values.put(JogosContract.Columns.VALOR, jogo.getNota());
        long id = db.insert(JogosContract.TABLE_NAME, null, values);
        jogo.setId((int) id);
    }

    public void update(Jogo jogo) {
        ContentValues values = new ContentValues();
        values.put(JogosContract.Columns.NOME, jogo.getNome());
        values.put(JogosContract.Columns.VALOR, jogo.getNota());
        db.update(JogosContract.TABLE_NAME, values, JogosContract.Columns._ID + " = ?", new String[]{ String.valueOf(jogo.getId()) });
    }

    public void delete(Jogo jogo) {
        db.delete(JogosContract.TABLE_NAME, JogosContract.Columns._ID + " = ?", new String[]{ String.valueOf(jogo.getId()) });
    }
}
