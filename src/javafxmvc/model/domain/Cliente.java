package javafxmvc.model.domain;

import java.io.Serializable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Cliente implements Serializable {

    private int codCliente;
    private String nome;
    private String cpf;
    private String telefone;
    private BooleanProperty chamada = new SimpleBooleanProperty(true);

    public Cliente() {
    }

    public Cliente(int cdCliente, String nome, String cpf, String telefone) {
        this.codCliente = cdCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int cdCliente) {
        this.codCliente = cdCliente;
    }

    public String getNome() {
        return nome.toString();
    }
    
    public void setNome(String nome) {
        this.nome = nome;

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isChamada() {
        return chamada.get();
    }

    public void setChamada(boolean chamada) {
        this.chamada.set(chamada);
    }

    public BooleanProperty chamadaProperty() {
        return chamada;
    }

}
