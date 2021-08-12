package br.utfpr.activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MostrarDadosctivity extends AppCompatActivity {

    public static final String NOME = "NOME";
    public static final String POSSUI_CARRO = "POSSUI_CARRO";
    public static final String TIPO = "TIPO";
    public static final String MODO_ABERTURA_ACTIVITY = "MODO_ABERTURA_ACTIVITY";
    public static final String NOTA = "NOTA" ;

    private int modo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_dadosctivity);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        if(bundle != null){
            String nome = bundle.getString(NOME, getString(R.string.nao_cadastrado));
            boolean possuiCarro = bundle.getBoolean(POSSUI_CARRO);
            int id = bundle.getInt(TIPO, -1);

            String saida = getString(R.string.nome_mensagem)+ nome + "\n";

            saida += getString(R.string.possui_carro) + "? " + "\n";

            saida += possuiCarro ? getString(R.string.sim) : getString(R.string.nao) + "\n";

            switch (id){
                case R.id.radioButtonAluno:
                    saida += getString(R.string.aluno);
                    break;
                case R.id.radioButtonProfessor:
                    saida += getString(R.string.professor);
                    break;
                default:
                    saida += getString(R.string.nenhum_tipo_selecionado);
            }

            TextView textViewDados = findViewById(R.id.textViewDados);

            textViewDados.setText(saida);

            modo = bundle.getInt(MODO_ABERTURA_ACTIVITY, 0);

        }
        setTitle(getString(R.string.dados_cadastrado));
    }

    private void finalizar(){

        if (modo == MainActivity.PEDIR_NOTA){
            Intent intent = new Intent();
            intent.putExtra(NOTA, 1000);
            setResult(Activity.RESULT_OK, intent);
        }

        finish();
    }

    @Override
    public void onBackPressed() {
        finalizar();
    }
}