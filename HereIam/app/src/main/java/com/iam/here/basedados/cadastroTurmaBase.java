package com.iam.here.basedados;

import java.io.Serializable;

/**
 * Created by eliezer on 28/06/2017.
 */

/**
 * Classe responsável pela Modelagem dos Dados do  Banco de dados
 */
public class cadastroTurmaBase implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private String nomeTurma;
    private String diaAulas;
    private String horarioAulas;
    private String chaveAcesso;
    private String local;

    public cadastroTurmaBase(){

    }

    public cadastroTurmaBase(long id, String nomeTurma, String diaAulas, String horarioAulas, String chaveAcesso, String local){
        this.id = id;
        this.nomeTurma = nomeTurma;
        this.diaAulas = diaAulas;
        this.horarioAulas = horarioAulas;
        this.chaveAcesso = chaveAcesso;
        this.local = local;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getNomeTurma(){
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma){
        this.nomeTurma = nomeTurma;
    }

    public String getDiaAulas(){
        return diaAulas;
    }

    public void setDiaAulas(String diaAulas){
        this.diaAulas = diaAulas;
    }

    public String getHorarioAulas(){
        return horarioAulas;
    }

    public void setHorarioAulas(String horarioAulas){
        this.horarioAulas = horarioAulas;
    }

    public String getChaveAcesso(){
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso){
        this.chaveAcesso = chaveAcesso;
    }

    public String getLocal(){
        return local;
    }

    public void setLocal(String local){
        this.local = local;
    }
}
