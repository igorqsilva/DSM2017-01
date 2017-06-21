package com.iam.here.hereiam;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
     * Cria o Banco de Dados
     */
    public class Banco extends SQLiteOpenHelper{
        private static final String Nome_Banco = "banco.db";
        private static final String Tabela_turmas = "turmas";
        private static final String ID = "_id";
        private static final String Nome_turma = "NomeTurma";
        private static final String Data = "diaAula";
        private static final String Horario = "horario";
        private static final String Chave_Acesso = "chaveAcesso";
        private static final String Local = "local";
        private static final int VERSAO = 1;

        /**
         * Construtor que passa as informações a a super classe criada
         * As informações é do -> local, and, -> versão do banco
         * @param context
         */
        public Banco(Context context) {
            super(context, Nome_Banco,null,VERSAO);
        }

        /**
         * Cria a tabela com os atributos definidos
         * A tabela será usada para realizar Inserção de dados
         * A tabela será usada também para manipulação de dados
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
         * Atualiza as informações que serão inseridas na Tabela criada
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

    /**
     * Classe é Responsásvel pela Inserção de Dados no Banco
     * Classe Responsavel pelo controle do banco de dados
     * O atributo db recebe os atributos do metodo getWritableDatabase, que diz que o banco fará leitura e escrita de dados
     * O metodo insert recebe os parâmetros da tabela que será feito o manipulamento dos dados
     * Ao finalizar as operações é correto finalizar a conexão do banco de dados
     */
    public class BancoController{
        private SQLiteDatabase db;
        private Banco banco;

        public BancoController(Context context){
            banco = new Banco(context);
        }

        public String insereDado(String NomeTurma, String diaAula, String horario, String chaveAcessso, String local){
            ContentValues valores;
            long resultado;

            db = banco.getWritableDatabase();
            valores = new ContentValues();
            valores.put(Banco.Nome_turma, NomeTurma);
            valores.put(Banco.Data, diaAula);
            valores.put(Banco.Horario, horario);
            valores.put(Banco.Chave_Acesso, chaveAcessso);
            valores.put(Banco.Local, local);

            resultado = db.insert(Banco.Tabela_turmas, null, valores);
            db.close();

            if(resultado==-1)
                return "Erro ao inserir registro";
            else
                return "Registro Inserido com sucesso";
        }
    }

    /**
     * Classe ambém faz controle do Banco de Dados
     * Os Dados inseridos Serão convertios em String
     * O metodo insereDados passa por parametro as informações que serão adicionadas
     * Ao final da operação será exibito uma mesagem (Toast) mostrando se a operação foi realizada com sucesso ou não
     */
    public class InsereDado extends Activity{

        @Override
        protected void onCreate(Bundle saveInstanceState){
           super.onCreate(saveInstanceState);
           setContentView(R.layout.activity_main);

            Button botao = (Button)findViewById(R.id.all);

            botao.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    BancoController crud = new BancoController(getBaseContext());
                    EditText NomeTurma = (EditText)findViewById(R.id.editText);
                    EditText diaAula = (EditText)findViewById(R.id.editText2);
                    EditText horario = (EditText)findViewById(R.id.editText3);
                    EditText chaveAcesso = (EditText)findViewById(R.id.editText4);
                    EditText local = (EditText)findViewById(R.id.editText5);
                    String NomeTurmaString = NomeTurma.getText().toString();
                    String diaAulaString = diaAula.getText().toString();
                    String horarioString = horario.getText().toString();
                    String chaveAcessoString = chaveAcesso.getText().toString();
                    String localString = local.getText().toString();
                    String resultado;

                    resultado = crud.insereDado(NomeTurmaString,diaAulaString,horarioString,chaveAcessoString,localString);

                    Toast.makeText(getApplicationContext(),resultado, Toast.LENGTH_LONG).show();
                }

            });
        }
    }
}

