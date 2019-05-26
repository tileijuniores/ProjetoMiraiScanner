package miraiscanner.facom.ufu.br.miraiscanner.Model;

import android.text.BoringLayout;

import java.io.Serializable;

/**
 * Created by mirandagab and MarceloPrado on 12/03/2018.
 */

public class Dispositivo implements Serializable{
    private String nome;
    private String ip;
    private String mac;
    private String tipo;
    private String fabricante;

    //portas 23, 2323 e 48101
    //estas variaveis indicam se as portas estão abertas ou fechadas
    private Boolean porta23Aberta;
    private Boolean porta48101Aberta;
    private Boolean porta2323Aberta;

    // estas variáveis indicam as credenciais de acesso caso a porta 23 ou 2323
    // estejam abertas e caso as credenciais estejam na lista do Mirai
    private String usuario;
    private String senha;

    public Dispositivo(String ip, String mac){
        this.nome = "Genérico";
        this.ip = ip;
        this.mac = mac.toUpperCase();
        this.tipo = "Genérico";
        this.fabricante = "Indisponível";
        this.porta23Aberta = false;
        this.porta2323Aberta = false;
        this.porta48101Aberta = false;
        this.usuario = "";
        this.senha = "";
    }

    public Dispositivo(String nome, String ip, String mac, String tipo, String fabricante,
                       Boolean porta23Aberta, Boolean porta2323Aberta, Boolean porta48101Aberta,
                       String usuario, String senha){
        this.nome = nome;
        this.ip = ip;
        this.tipo = tipo;
        this.mac = mac.toUpperCase();
        this.fabricante = fabricante;
        this.porta23Aberta = porta23Aberta;
        this.porta2323Aberta = porta2323Aberta;
        this.porta48101Aberta = porta48101Aberta;
        this.usuario = usuario;
        this.senha = senha;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIp() { return ip; }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() { return mac; }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Boolean getPorta23Aberta(){ return this.porta23Aberta; }

    public void setPorta23Aberta(Boolean porta23Aberta) { this.porta23Aberta = porta23Aberta; }

    public Boolean getPorta2323Aberta(){ return this.porta2323Aberta; }

    public void setPorta2323Aberta(Boolean porta23Aberta) { this.porta2323Aberta = porta23Aberta; }

    public Boolean getPorta48101Aberta(){ return this.porta48101Aberta; }

    public void setPorta48101Aberta(Boolean porta48101Aberta) { this.porta48101Aberta = porta48101Aberta; }

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }
}
