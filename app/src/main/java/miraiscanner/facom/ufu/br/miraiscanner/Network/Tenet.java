package miraiscanner.facom.ufu.br.miraiscanner.Network;

/**
 * Created by mirandagab on 28/11/18.
 */

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
//import java.io.OutputStream;
//import java.io.PrintStream;

import miraiscanner.facom.ufu.br.miraiscanner.Model.Credenciais;

class Telnet {
    TelnetClient telnet;
    private InputStream in;
    private OutputStreamWriter out;
    private String prompt;
    private Credenciais credenciais;
    private boolean conectado;
    private String usuario;
    private String senha;

    public Telnet(){
        this.telnet = new TelnetClient();
        this.in = null;
        this.out = null;
        this.prompt = ">";
        this.credenciais = new Credenciais();
        this.conectado = false;
        this.usuario = "Não descoberto";
        this.senha = "Não descoberto";
    }

    public  void logar(String ip, int porta) {
        for (int i = 0; i < 60; i++){
            try {
                // Connect to the specified server
                this.telnet.connect(ip, porta);

                // Get input and output stream references
                this.in = telnet.getInputStream();
                this.out = new OutputStreamWriter(telnet.getOutputStream());
                // Log the user on
                System.out.println("[TELNET logando] Usuário: " + this.credenciais.cred[i][0]
                        + " / senha: " + this.credenciais.cred[i][1]);
                readUntil("login: ");
                this.out.write(this.credenciais.cred[i][0] + "\r\n");
                this.out.flush();
                readUntil("password: ");
                this.out.write(this.credenciais.cred[i][1] + "\r\n");
                this.out.flush();

                if (estaConectado(this.prompt + " ")){
                    System.out.println("[estaCONECTADO] TRUE");
                    this.usuario = this.credenciais.cred[i][0];
                    this.senha = this.credenciais.cred[i][1];
                    try {
                        this.in.close();
//                    this.out.close();
                        this.telnet.disconnect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                } else {
                    try {
                        this.in.close();
//                    this.out.close();
                        this.telnet.disconnect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        } //FIM DO FOR
//        try {
//            // Connect to the specified server
//            this.telnet.connect(ip, 23);
//
//            // Get input and output stream references
//            this.in = telnet.getInputStream();
//            this.out = new OutputStreamWriter(telnet.getOutputStream());
//            // Log the user on
//            System.out.println("[TELNET logando] Usuário: " + this.credenciais.cred[0][0]
//                    + " / senha: " + this.credenciais.cred[0][1]);
//            readUntil("login: ");
//            this.out.write(this.credenciais.cred[0][0] + "\r\n");
//            this.out.flush();
//            readUntil("password: ");
//            this.out.write(this.credenciais.cred[0][1] + "\r\n");
//            this.out.flush();
//
//            if (estaConectado(this.prompt + " ")) {
//                System.out.println("[estaCONECTADO] TRUE");
//                try {
//                    this.in.close();
////                    this.out.close();
//                    this.telnet.disconnect();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }else {
//                System.out.println("[estaCONECTADO] FALSE");
//                for(int i = 1; i < 60 ; i++) {
//                    this.out.write(this.credenciais.cred[i][0] + "\r\n");
//                    this.out.flush();
//                    readUntil("password: ");
//                    this.out.write(this.credenciais.cred[i][1] + "\r\n");
//                    this.out.flush();
//                    if (estaConectado(this.prompt + " ")){
//                        try {
//                            this.in.close();
//                            this.telnet.disconnect();
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                        break;
//                    }
//                }
//            }
//
////                 Advance to a prompt
////                readUntil(this.prompt + " ");
////                this.out.write("exit\r\n");
////                this.out.flush();
////                System.out.println("[TELNET LOGOU] Usuário: " + this.credenciais.cred[i][0] + " / senha: "
////                        + this.credenciais.cred[i][1]);
////                break;
//        } catch (Exception e) {
//            System.out.println("[TELNET] Algo errado aconteceu: " + e);
//        }
    }
//
//    public void su(String password) {
//        try {
//            write("su");
//            readUntil("Password: ");
//            write(password);
//            prompt = "#";
//            readUntil(prompt + " ");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
//            StringBuilder sb = new StringBuilder();
            boolean found = false;
            char ch = (char) in.read();
            while (true) {
//                System.out.print("[READUNTIL1] ");
                System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
//                        System.out.print("[READUNTIL2] " + sb.toString());
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // este método verifica se houve sucesso na conexão com o servidor telnet
    // OBS.: as mensagens estão configuradas de acordo com o servidor Telnet do Windows
    // para outros servidores Telnet, verificar as mensagens padrão de conexão e falha de login
    public boolean estaConectado(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
//            StringBuilder sb = new StringBuilder();
            boolean found = false;
            char ch = (char) in.read();
            while (true) {
//                System.out.print("[READUNTIL1] ");
                System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
//                        System.out.print("[READUNTIL2] " + sb.toString());
                        return true;
                    } else if (sb.toString().endsWith("login: ")){
                        return false;
                    } else if (sb.toString().endsWith("account")){
                        return false;
                    }
                }
                ch = (char) in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    public void write(String value) {
//        try {
//            out.write(value);
//            out.flush();
//            System.out.println(value);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String sendCommand(String command) {
//        try {
//            write(command);
//            return readUntil(prompt + " ");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public void disconnect() {
//        try {
//            telnet.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

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
}