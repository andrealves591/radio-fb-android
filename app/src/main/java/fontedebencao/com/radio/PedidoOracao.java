package fontedebencao.com.radio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PedidoOracao extends AppCompatActivity {

    private EditText etNome;
    private EditText etEmail;
    private EditText etTelefone;
    private EditText etMensagem;
    private Button btEnviar;
    private Mensagem msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_oracao);

        etNome = findViewById(R.id.ap_et_nome);
        etEmail = findViewById(R.id.ap_et_email);
        etTelefone = findViewById(R.id.ap_et_telefone);
        etMensagem = findViewById(R.id.ap_et_mensagem);
        btEnviar = findViewById(R.id.ap_bt_enviar);
        msg = new Mensagem();

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.setNome(etNome.getText().toString());
                msg.setTelefone(etTelefone.getText().toString());
                msg.setEmail(etEmail.getText().toString());
                msg.setMensagem(etMensagem.getText().toString());
                sendMail();
            }
        });
    }

    private void sendMail() {
        String destinatario = "pr.mauriciojalves@hotmail.com";
        String[] destinatarios = destinatario.split(",");
        String assunto = "PEDIDO DE ORAÇÃO";
        String mensagem = msg.toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, destinatarios);
        intent.putExtra(Intent.EXTRA_SUBJECT, assunto);
        intent.putExtra(Intent.EXTRA_TEXT, mensagem);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Escolha o seu cliente de email"));
    }
}