package com.iam.here.hereiam;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.Date;

public class MainActivity extends AppCompatActivity {

    /**
     * Activity Lifecycle - Clico de vida da aplicação
     * Cria a aplicação para ser visivel ao usuário
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *Inicia a aplicação se a mesma já foi criada
     */
    @Override
    protected void onStart(){
        super.onStart();
    }

    /**
     * O onStop() é chamado quando a Activity não está mais visível
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * é chamado depois que uma Activity foi interrompida
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * o onDestroy() é chamado por dois motivos, primeiro se você solicitou a finalização da Activity utilizando o método finish() O
     * Ou se o sistema operacional destruiu a instância Activity para economizar espaço
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Classe do banco de Dados SQLiteOpenHelper
     * Definição da classe para o banco de dados
     * E implementação dos metodos da Classe SQLiteOpenHelper
     * Classe possui os atributos da Tabela de turmas
     * Tabela de turma ficará armazenada as turmas criadas pelo professor
     */
    public class Banco extends SQLiteOpenHelper{
        private static final String Nome_Banco = "banco.db";
        private static final String Tabela_turmas = "turmas";
        private static final String ID = "_id";
        private static final String Nome_turma = "NomeTurma";
        private static final String Data = "diaAula";
        private static final String Horario = "horario";
        private static final String Chave_Acesso = "chaveAcesso";
        private static final String Local = "Local";

        /**
         * Construtor que passa as informações a a super classe criada
         * As informações é do -> local, and, -> versão do banco
         * @param context
         * @param name
         * @param factory
         * @param version
         */
        public Banco(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        /**
         * Cria a tabela com os atributos definidos
         * @param db
         */
        @Override
        public void onCreate(SQLiteDatabase db) {

            String sql = "CREATE TABLE"+Tabela_turmas+"("
                    + ID + "integer primary key autoincrement,"
                    + Nome_turma + "text,"
                    + Data + "text,"
                    + Horario + "text,"
                    + Chave_Acesso + "text,"
                    + Local + "text"
                    +")";
            db.execSQL(sql);
        }

        /**
         * Apaga a tabela se a mesma existir;
         * E chama novamente o metodo para recria-la com as alterações feitas
         * @param db
         * @param oldVersion
         * @param newVersion
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS" + Tabela_turmas);
            onCreate(db);

        }

    }
}
