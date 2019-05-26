package miraiscanner.facom.ufu.br.miraiscanner.Network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Post {

    @SerializedName("enderecoIp")
    @Expose
    private String enderecoIp;

    @SerializedName("fabricanteMac")
    @Expose
    private String fabricanteMac;

    @SerializedName("enderecoMac")
    @Expose
    private String enderecoMac;

    @SerializedName("porta23")
    @Expose
    private String porta23;

    @SerializedName("porta2323")
    @Expose
    private String porta2323;

    @SerializedName("porta48101")
    @Expose
    private String porta48101;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    public String getEnderecoIp() {
        return enderecoIp;
    }

    public void setEnderecoIp(String enderecoIp) {
        this.enderecoIp = enderecoIp;
    }

    public String getFabricanteMac() {
        return fabricanteMac;
    }

    public void setFabricanteMac(String fabricanteMac) {
        this.fabricanteMac = fabricanteMac;
    }

    public String getEnderecoMac() {
        return enderecoMac;
    }

    public void setEnderecoMac(String enderecoMac) {
        this.enderecoMac = enderecoMac;
    }

    public String getPorta23() {
        return porta23;
    }

    public void setPorta23(String porta23) {
        this.porta23 = porta23;
    }

    public String getPorta2323() {
        return porta2323;
    }

    public void setPorta2323(String porta2323) {
        this.porta2323 = porta2323;
    }

    public String getPorta48101() {
        return porta48101;
    }

    public void setPorta48101(String porta48101) {
        this.porta48101 = porta48101;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getSuporte() {
        return suporte;
    }

    public void setSuporte(String suporte) {
        this.suporte = suporte;
    }

    @SerializedName("senha")
    @Expose
    private String senha;


    @SerializedName("resposta")
    @Expose
    private String resposta;

    @SerializedName("suporte")
    @Expose
    private String suporte;











}
