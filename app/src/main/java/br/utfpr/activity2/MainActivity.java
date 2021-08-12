package br.utfpr.activity2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final int PEDIR_NOTA = 1;
    private EditText editTextNome;
    private CheckBox checkBoxPossuiCarro;
    private RadioGroup radioGroupTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        checkBoxPossuiCarro = findViewById(R.id.checkBoxPossuiCarro);
        radioGroupTipo = findViewById(R.id.radioGroupTipo);
    }

    public void enviarComNota(View view){
        chamaTela2(true);
    }

    public void enviarSemPedirNota(View view){
        chamaTela2(false);
    }

    public void chamaTela2(Boolean pedirNota){

        Intent intent = new Intent(this, MostrarDadosctivity.class);

        String nome = editTextNome.getText().toString();
        if(nome != null || !nome.equals("")){
            intent.putExtra(MostrarDadosctivity.NOME, nome);
        }

        intent.putExtra(MostrarDadosctivity.POSSUI_CARRO, checkBoxPossuiCarro.isChecked());

        int id = radioGroupTipo.getCheckedRadioButtonId();

        if(id != -1){
            intent.putExtra(MostrarDadosctivity.TIPO, id);
        }

        if(pedirNota) {
            intent.putExtra(MostrarDadosctivity.MODO_ABERTURA_ACTIVITY, PEDIR_NOTA);
            startActivityForResult(intent, PEDIR_NOTA);
        }else{
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PEDIR_NOTA && resultCode == Activity.RESULT_OK){

            Bundle bundle = data.getExtras();

            if (bundle != null) {
                int nota = bundle.getInt(MostrarDadosctivity.NOTA);

                Toast.makeText(this,
                        getString(R.string.enviar_com_nota) + nota,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}