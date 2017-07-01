package com.iam.here.hereiam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iam.here.basedados.cadastroTurmaBase;
import com.iam.here.basedados.cadastroTurmaDAO;

public class cadastrarTurma extends Activity {

    /**
     * Variáveis para cadastro de uma nova turma
     */
    EditText nomeTurma;
    EditText diaAulas;
    EditText horarioAulas;
    EditText chaveAcesso;
    EditText local;


    cadastroTurmaDAO cadastroTurmaDAO;

    /**
     * Pega os dados do Layout e atribui as variáveis
     * Valores das variáveis serão gardados no banco de Dados
     * Instancia o Botão btSalvar do Layout
     * Grava os dados No Banco de Dados
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_turma);

        cadastroTurmaDAO = new cadastroTurmaDAO(this);

        nomeTurma = (EditText) findViewById(R.id.nomeTurma);
        diaAulas = (EditText) findViewById(R.id.diaAulas);
        horarioAulas = (EditText) findViewById(R.id.horarioAulas);
        chaveAcesso = (EditText) findViewById(R.id.chaveAcesso);
        local = (EditText) findViewById(R.id.local);

        Button btSalvar = (Button) findViewById(R.id.btSalvar);

        btSalvar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                /**
                 * Variáveis para inserção de dados
                 */
                String nomeTurma = "";
                String diaAulas = "";
                String horarioAulas = "";
                String chaveAcesso = "";
                String local = "";

                nomeTurma = nomeTurma.getBytes().toString();
                diaAulas = diaAulas.getBytes().toString();
                horarioAulas = horarioAulas.getBytes().toString();
                chaveAcesso = chaveAcesso.getBytes().toString();
                local = local.getBytes().toString();

                /**
                 * Cria Nova Turma
                 * Passa os Parametros para inserir os dados no banco de Dados
                 */
                cadastroTurmaBase cadastroTurmaBase = new cadastroTurmaBase(nomeTurma, diaAulas, horarioAulas, chaveAcesso, local);

                /**
                 * Abre uma nova Conexão
                 * No banco de Dados
                 */
                cadastroTurmaDAO.open();

                /**
                 * Insere os dados
                 * E salva a Turma cadastrada
                 */
                cadastroTurmaDAO.novoCadastro(cadastroTurmaBase);

                /**
                 * Fecha a conexão com o banco de dados
                 */
                cadastroTurmaDAO.close();

                /**
                 * chama o metodo limpar
                 * Para limpar os campos preenchidos e já salvos
                 */
                limparCampos();
            }
        });
    }

    /**
     * Metodo Responsável por Limpar os campos após uma inserção de dadaos
     * Limpa os campos se a operação de gravação for efetivada no banco de dados
     */
    private void limparCampos(){
        nomeTurma.setText("");
        diaAulas.setText("");
        horarioAulas.setText("");
        chaveAcesso.setText("");
        local.setText("");
    }



    /**
     * Faz com que o Botão "Voltar" em Cadastro Turma redirecione para a tela Inicial
     * Metado chama outra Activity e faz a mudança de tela
     * @param view
     */
    public void telaInicial(View view){

        Intent intent1 = new Intent(getApplicationContext(), listagemTurmas.class);
        startActivity(intent1);
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
}