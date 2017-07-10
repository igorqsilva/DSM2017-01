package com.iam.here.bancodados;

/**
 * Created by eliez on 09/07/2017.
 */

public class Turma {
    int codigo;
    String turma;
    String horario;
    String dia;
    String chave;
    String local;

    public Turma(){

    }

    public Turma(int _codigo, String _turma, String _horario, String _dia, String _chave, String _local){
        this.codigo = _codigo;
        this.turma = _turma;
        this.horario = _horario ;
        this.dia = _dia;
        this.chave = _chave;
        this.local = _local;
    }

    public Turma(String _turma, String _horario, String _dia, String _chave, String _local){
        this.turma = _turma;
        this.horario = _horario ;
        this.dia = _dia;
        this.chave = _chave;
        this.local = _local;
    }
    //----------------------------------------------------------------------------------------------


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}