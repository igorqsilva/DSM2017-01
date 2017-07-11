package com.iam.here.bancodados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eliezer on 09/07/2017.
 */

public class BancoDados extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_TURMA = "bd_cliente";

    private static final String TABELA_TURMA = "tb_cliente";

    private static final String COLUNA_CODIGO = "codigo";
    private static final String COLUNA_TURMA = "turma";
    private static final String COLUNA_HORARIO = "horario";
    private static final String COLUNA_DIA = "dia";
    private static final String COLUNA_CHAVE = "chave";
    private static final String COLUNA_LOCAL = "local";


    public BancoDados(Context context) {

        super(context, BANCO_TURMA, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_COLUNA = "CREATE TABLE " + TABELA_TURMA + "("
                + COLUNA_CODIGO + " INTEGER PRIMARY KEY, " +
                COLUNA_TURMA + " TEXT, " +
                COLUNA_HORARIO + " TEXT, " +
                COLUNA_DIA + " TEXT, " +
                COLUNA_CHAVE + " TEXT, " +
                COLUNA_LOCAL + " TEXT)";

        db.execSQL(QUERY_COLUNA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void addTurma(Turma turma){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_TURMA, turma.getTurma());
        values.put(COLUNA_HORARIO, turma.getHorario());
        values.put(COLUNA_DIA, turma.getDia());
        values.put(COLUNA_CHAVE, turma.getChave());
        values.put(COLUNA_LOCAL, turma.getLocal());

        db.insert(TABELA_TURMA, null, values);
        db.close();
    }

    public void apagarTurma(Turma turma){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_TURMA, COLUNA_CODIGO + " = ?", new String[] { String.valueOf(turma.getCodigo())});

        db.close();
    }

    Turma selecionarTurma(int codigo){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_TURMA, new String[]{COLUNA_CODIGO,
                        COLUNA_TURMA, COLUNA_HORARIO, COLUNA_DIA, COLUNA_CHAVE, COLUNA_LOCAL}, COLUNA_CODIGO + " = ?",
                new String[] {String.valueOf(codigo)}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Turma turma = new Turma(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return turma;
    }

    public void atualizaTurma(Turma turma){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_TURMA, turma.getTurma());
        values.put(COLUNA_HORARIO, turma.getHorario());
        values.put(COLUNA_DIA, turma.getDia());
        values.put(COLUNA_CHAVE, turma.getChave());
        values.put(COLUNA_LOCAL, turma.getLocal());

        db.update(TABELA_TURMA, values, COLUNA_CODIGO + " = ?",
                new String[] { String.valueOf(turma.getCodigo()) });
    }

    public List<Turma> listaTodasTurmas(){

        List<Turma> listaTurmas = new ArrayList<Turma>();

        String query = "SELECT * FROM " + TABELA_TURMA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Turma turma = new Turma();
                turma.setCodigo(Integer.parseInt(c.getString(0)));
                turma.setTurma(c.getString(1));
                turma.setHorario(c.getString(2));
                turma.setDia(c.getString(3));
                turma.setChave(c.getString(4));
                turma.setLocal(c.getString(5));

                listaTurmas.add(turma);

            } while (c.moveToNext()) ;
        }
        return listaTurmas;
    }
}