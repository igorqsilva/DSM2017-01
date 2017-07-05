package com.iam.here.hereiam;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;

import com.iam.here.hereiam.model.FormProblemException;
import com.iam.here.hereiam.data.EasySharedPreferences;
import com.iam.here.hereiam.web.WebLogin;
import com.iam.here.hereiam.model.User;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class loginActivity extends AppCompatActivity {

    private final int MIN_PASSWORD = 6;
    MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setStringFromEdit(R.id.matricula, EasySharedPreferences.getStringFromKey(
                this, EasySharedPreferences.KEY_MATRICULA));
    }

    /**
     * Inicia a aplicação se a mesma já foi criada
     */
    @Override
    protected void onStart() {
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
     *
     * @param v
     */
    public void login(View v) {

        hideKeyboard();

        try {
            checkMatricula();
            checkSenha();
        } catch (FormProblemException e) {
            showAlert(e.getMessage());
            return;
        }

        String password = getStringFromEdit(R.id.senha);
        String email = getStringFromEdit(R.id.matricula);

        showDialogWithMessage(getString(R.string.load_login));

        tryLogin(password, email);
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

    public void startCadastroActivity(View view) {

        Intent secondActivity = new Intent(this, cadastroActivity.class);
        startActivity(secondActivity);
    }

    public void startAluno(View view) {

        Intent secondActivity = new Intent(this, aluno.class);
        startActivity(secondActivity);
    }

    public void startProfessor(View view) {

        Intent secondActivity = new Intent(this, listagemTurmas.class);
        startActivity(secondActivity);
    }


    /**
     *  verifica se o campo da senha não está vazio e se a senha tem o tamanho minimo
     * @throws FormProblemException
     */
    private void checkSenha() throws FormProblemException {
        String senha = getStringFromEdit(R.id.senha);
        if ("".equals(senha)) {
            throw new FormProblemException(getString(R.string.error_password_empty));
        }

        if (senha.length() < MIN_PASSWORD) {
            throw new FormProblemException(getString(R.string.error_password_small));
        }
    }

    /**
     *
     * @throws FormProblemException
     */
    private void checkMatricula() throws FormProblemException {
        String senha = getStringFromEdit(R.id.senha);
        if ("".equals(senha)) {
            throw new FormProblemException(getString(R.string.error_password_empty));
        }
    }

    private void tryLogin(String matricula, String senha) {
        WebLogin webLogin = new WebLogin(matricula, senha);
        webLogin.call();
    }

    private void storeCredentials(User user) {
        EasySharedPreferences.setStringFromKey(this, EasySharedPreferences.KEY_MATRICULA, user.getEmail());
        EasySharedPreferences.setStringFromKey(this, EasySharedPreferences.KEY_TOKEN, user.getToken());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Exception exception) {
        dismissDialog();
        showAlert(exception.getMessage());
    }

    public String getStringFromEdit(int id) {
        EditText input = (EditText) findViewById(id);
        return input.getText().toString();
    }

    public void showAlert(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showFixedBottom(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void setStringFromEdit(int id, String text) {
        EditText input = (EditText) findViewById(id);
        input.setText(text);
    }

    public void showDialogWithMessage(String message) {
        dialog = new MaterialDialog.Builder(this)
                .content(message)
                .progress(true, 0)
                .show();
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
