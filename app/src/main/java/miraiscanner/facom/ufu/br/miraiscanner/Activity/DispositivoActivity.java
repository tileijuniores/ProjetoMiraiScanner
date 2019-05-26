package miraiscanner.facom.ufu.br.miraiscanner.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import miraiscanner.facom.ufu.br.miraiscanner.Model.Dispositivo;
import miraiscanner.facom.ufu.br.miraiscanner.Model.DispositivoResponse;
import miraiscanner.facom.ufu.br.miraiscanner.Network.APIService;
import miraiscanner.facom.ufu.br.miraiscanner.Network.Post;
import miraiscanner.facom.ufu.br.miraiscanner.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DispositivoActivity extends AppCompatActivity {
    private Dispositivo dispositivo;
    private DispositivoResponse dispositivos;
    private String nomeRede;

    private String enderecoIp;

    private String fabricanteMac;


    private String enderecoMac;


    private String porta23;


    private String porta2323;


    private String porta48101;


    private String usuario;


    private String senha;
    private String suporte;
    private String resposta;
    private String testando;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivo);
        Intent it = this.getIntent();


        if(it.getSerializableExtra("dispositivos") != null) {
            DispositivoResponse dispositivoResponse = (DispositivoResponse)
                    it.getSerializableExtra("dispositivos");
            this.dispositivos = dispositivoResponse;

        }
        if(it.getStringExtra("nome_rede") != null) {
            this.nomeRede = it.getStringExtra("nome_rede");

        }
        if(it.getSerializableExtra("dispositivo") != null) {
            Dispositivo disp = (Dispositivo) it.getSerializableExtra("dispositivo");
            this.dispositivo = disp;

        }
        else{
            dispositivo = new Dispositivo("0.0.0.0", "00:00:00:00:00:00");

        }

        //Painel de informações
        TextView txt_nome = (TextView) findViewById(R.id.txt_nome);

        TextView txt_ip = (TextView) findViewById(R.id.txt_ip);
        TextView txt_fabricante = (TextView) findViewById(R.id.txt_fabricante);

        TextView txt_mac = (TextView) findViewById(R.id.txt_mac);

        txt_nome.setText(dispositivo.getNome());
        txt_ip.setText(dispositivo.getIp());
        txt_fabricante.setText(dispositivo.getFabricante());
        txt_mac.setText(dispositivo.getMac());


        enderecoIp = dispositivo.getIp();
        fabricanteMac = dispositivo.getFabricante();
        enderecoMac = dispositivo.getMac();

        //Painel de ameaças
        TextView txt_23 = (TextView) findViewById(R.id.txt_23);
        TextView txt_2323 = (TextView) findViewById(R.id.txt_2323);
        TextView txt_48101 = (TextView) findViewById(R.id.txt_48101);
        TextView txt_usuario = (TextView) findViewById(R.id.telnet_usuario);
        TextView txt_senha = (TextView) findViewById(R.id.telnet_senha);
        LinearLayout layout_telnet = (LinearLayout) findViewById(R.id.layout_telnet);

        boolean vulneravel = false;

        if(dispositivo.getPorta23Aberta()){

            porta23 = "\"Aberta, possiveis vulnerabilidades foram encontradas em seu dispositivo que podem estar relacionada ao Mirai \" +\n" +
                    "                    \"neste dispositivo.\"";


            txt_23.setText("Aberta,  Altere as senhas de acesso e reinicie o dispositivo.");
            txt_23.setTextColor(Color.parseColor("#ffbb33"));
            vulneravel = true;
            txt_usuario.setText(dispositivo.getUsuario());
            txt_senha.setText(dispositivo.getSenha());
            layout_telnet.setVisibility(View.VISIBLE);
        }
        else{
            txt_23.setText("Fechada");
            porta23 = "fechada";
        }

        if(dispositivo.getPorta2323Aberta()){
            porta2323 = "\"Aberta, possiveis vulnerabilidades foram encontradas em seu dispositivo que podem estar relacionada ao Mirai \" +\n" +
                    "                    \"neste dispositivo.\"";
            txt_2323.setText("Aberta,  Altere as senhas de acesso e reinicie o dispositivo.");
            txt_2323.setTextColor(Color.parseColor("#ffbb33"));
            vulneravel = true;
            if(!dispositivo.getPorta23Aberta()){
                txt_usuario.setText(dispositivo.getUsuario());
                txt_senha.setText(dispositivo.getSenha());
                layout_telnet.setVisibility(View.VISIBLE);
            }
        }
        else{

            txt_2323.setText("Fechada");
            porta2323 = "Fechada";
        }

        if(dispositivo.getPorta48101Aberta()){
            porta48101 = "\"Aberta, possiveis vulnerabilidades foram encontradas em seu dispositivo que podem estar relacionada ao Mirai \" +\n" +
                    "                    \"neste dispositivo.\"";
            txt_48101.setText("Aberta,  Altere as senhas de acesso e reinicie o dispositivo.");
            txt_48101.setTextColor(Color.parseColor("#ffbb33"));
            vulneravel = true;
        }
        else{
            txt_48101.setText("Fechada");
            porta48101 = "fechada";
        }

        TextView txt_ameacas = (TextView) findViewById(R.id.txt_ameacas);
        ImageView img_ameacas = (ImageView) findViewById(R.id.img_ameacas);

        if(vulneravel){
            suporte = "\"Foram encontradas vulnerabilidades em seu dispositivo que podem \" +\n" +
                    "                    \"ser exploradas pelo Mirai. Altere as senhas de acesso e reinicie o dispositivo.\"";
            txt_ameacas.setText("Foram encontradas vulnerabilidades em seu dispositivo que podem " +
                    "ser exploradas pelo Mirai. Altere as senhas de acesso e reinicie o dispositivo.");
            img_ameacas.setImageResource(R.drawable.twotone_warning_24);

        }
        else{
            suporte = "\"Não foi encontrada nenhuma vulnerabilidade relacionada ao Mirai \" +\n" +
                    "                    \"neste dispositivo.\"";
            txt_ameacas.setText("Não foi encontrada nenhuma vulnerabilidade relacionada ao Mirai " +
                    ".neste dispositivo");
            img_ameacas.setImageResource(R.drawable.twotone_check_circle_24);
            ImageViewCompat.setImageTintList(img_ameacas, ColorStateList.valueOf(ContextCompat
                    .getColor(DispositivoActivity.this, R.color.checked)));

        }


        //Botão voltar da barra superior
        ActionBar ts = getSupportActionBar();
        if(ts != null)
            ts.setDisplayHomeAsUpEnabled(true);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-miraiapp.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);


        Post post = new Post();
        post.setEnderecoIp(enderecoIp);
        post.setEnderecoMac(enderecoMac);
        post.setFabricanteMac(fabricanteMac);
        post.setPorta23(porta23);
        post.setPorta2323(porta2323);
        post.setPorta48101(porta48101);
        post.setSuporte(suporte);
        post.setSenha(senha);
        post.setUsuario(usuario);



        Call<Post> Post = service.savePost(post);
        Post.enqueue(new Callback<miraiscanner.facom.ufu.br.miraiscanner.Network.Post>() {
            @Override
            public void onResponse(Call<miraiscanner.facom.ufu.br.miraiscanner.Network.Post> call, Response<miraiscanner.facom.ufu.br.miraiscanner.Network.Post> response) {
                int status = response.code();

                Post resposta = response.body();

                Log.i("respossssta","On response" + status);
            }

            @Override
            public void onFailure(Call<miraiscanner.facom.ufu.br.miraiscanner.Network.Post> call, Throwable t) {

            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        System.out.printf("onOptionsItemSelected");
        if (id == android.R.id.home) {


            Intent intent = new Intent(DispositivoActivity.this, MainActivity.class);
            intent.putExtra("dispositivos", this.dispositivos);
            intent.putExtra("nome_rede", this.nomeRede);
            startActivity(intent);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
