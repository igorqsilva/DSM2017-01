package com.iam.here.basedados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eliezer on 28/06/2017.
 */

/**
 * Classe que Contem o Banco de Dados
 * E tabela para o cadastro de Turmas
 * Classe responsável por:
 * Criar o banco de Dados
 * Atualizar O Banco de Dados
 */
public class baseDAO extends SQLiteOpenHelper {

    /**
     * Nome do Banco de Dados
     */
    public static final String nomeBanco = "hereIam";

    /**
     * Versão do Banco de Dados
     */
    public static final int VERSAO_SCHEMA = 1;

    /**
     * Nome da Tabela
     */
    public static final String tabela = "cadastroTurma";

    /**
     * Campos Da tabela
     */
    public static final String campoID = "_id";
    public static final String campoNomeTurma = "turma";
    public static final String campoDiaAulas = "dia";
    public static final String campoHorarioAulas = "horario";
    public static final String campoChaveAcesso = "chave";
    public static final String campoLocal = "local";

    /**
     * Tabela para o Cadastro de Turmas
     * E campos definidos
     */
    private static final String criaTabela = "CREATE TABLE "
            + tabela + " ("+ campoID + " INTEGER PRIMARY KEY, "
            + campoNomeTurma + " TEXT, "
            + campoDiaAulas + " TEXT, "
            + campoHorarioAulas + "TEXT, "
            + campoChaveAcesso + " TEXT, "
            + campoLocal + " TEXT)";

    public baseDAO(Context context) {

        super(context, nomeBanco, null, VERSAO_SCHEMA);
    }

    /**
     * Cria a Tabela para o Cadastro de Turmas
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(criaTabela);
    }

    /**
     * Atualiza a Tabela de Cadastro de Turmas
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}