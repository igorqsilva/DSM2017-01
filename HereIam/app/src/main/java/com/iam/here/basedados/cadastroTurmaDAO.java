package com.iam.here.basedados;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.iam.here.basedados.baseDAO;
import com.iam.here.basedados.cadastroTurmaBase;

/**
 * Created by eliez on 28/06/2017.
 */

/**
 * Classe Responsável pela Conexão com o Banco de Dados
 */
public class cadastroTurmaDAO {

    private SQLiteDatabase database;
    private baseDAO baseDAO;

    public cadastroTurmaDAO(Context context){
        baseDAO = new baseDAO(context);
    }

    public void open() throws SQLException {
        database = baseDAO.getWritableDatabase();
    }

    public void close(){
        baseDAO.close();
    }

    /**
     * Insere um novo cadastro de uma turma
     * @param cadastroTurmaBase
     * @return
     */
    public long novoCadastro(cadastroTurmaBase cadastroTurmaBase){

        ContentValues values = new ContentValues();

        values.put(baseDAO.campoNomeTurma, cadastroTurmaBase.getNomeTurma());
        values.put(baseDAO.campoDiaAulas, cadastroTurmaBase.getDiaAulas());
        values.put(baseDAO.campoHorarioAulas, cadastroTurmaBase.getDiaAulas());
        values.put(baseDAO.campoChaveAcesso, cadastroTurmaBase.getChaveAcesso());
        values.put(baseDAO.campoLocal, cadastroTurmaBase.getLocal());

        return database.insert(baseDAO.tabela, null, values);
    }

    /**
     * Cria uma Lista de Turmas cadastradas
     * Mostrar os cadastros de Turma Realizados
     * @return
     */
    public List<cadastroTurmaBase> lerCadastro(){

        Cursor c = database.rawQuery("SELECT * FROM " + baseDAO.tabela, null);
        List<cadastroTurmaBase> cadastros = new ArrayList<>();

        if (c.moveToFirst()){
            while (!c.isAfterLast()){
                cadastroTurmaBase cadastroTurmaBase = new cadastroTurmaBase(
                        c.getLong(c.getColumnIndex(baseDAO.campoID)),
                        c.getString(c.getColumnIndex(baseDAO.campoNomeTurma)),
                        c.getString(c.getColumnIndex(baseDAO.campoDiaAulas)),
                        c.getString(c.getColumnIndex(baseDAO.campoHorarioAulas)),
                        c.getString(c.getColumnIndex(baseDAO.campoChaveAcesso)),
                        c.getString(c.getColumnIndex(baseDAO.campoLocal)));
                cadastros.add(cadastroTurmaBase);
                c.moveToNext();
            }
        }
        c.close();

        return cadastros;
    }

    /**
     * Classe responsável pela atualização de cadastro de uma Turma
     * @param cadastroTurmaBase
     * @return
     */
    public int atualizarCadastro(cadastroTurmaBase cadastroTurmaBase){

        long id = cadastroTurmaBase.getId();
        ContentValues values = new ContentValues();

        values.put(baseDAO.campoNomeTurma, cadastroTurmaBase.getNomeTurma());
        values.put(baseDAO.campoDiaAulas, cadastroTurmaBase.getDiaAulas());
        values.put(baseDAO.campoHorarioAulas, cadastroTurmaBase.getHorarioAulas());
        values.put(baseDAO.campoChaveAcesso, cadastroTurmaBase.getChaveAcesso());
        values.put(baseDAO.campoLocal, cadastroTurmaBase.getLocal());

        return database.update(baseDAO.tabela, values, baseDAO.campoID + " = " + id, null);
    }

    /**
     * Classe Realiza remossão de
     * uma turma cadastrada
     * @param cadastroTurmaBase
     */
    public void apagarCadastro(cadastroTurmaBase cadastroTurmaBase){
        long id = cadastroTurmaBase.getId();

        database.delete(baseDAO.tabela, baseDAO.campoID + " = " + id, null);
    }
}
