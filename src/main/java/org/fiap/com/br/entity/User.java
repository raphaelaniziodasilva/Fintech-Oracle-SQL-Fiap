package org.fiap.com.br.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String name;
    private String surname;
    private String rg;
    private String cpf;
    private Date dateOfBirth;
    private String cellPhone;
    private String telephone;
    private String cellPhone1;
    private String telephone1;

    public User(int code, String name, String surname, String rg, String cpf, Date dateOfBirth, String cellPhone, String telephone, String cellPhone1, String telephone1) {
        this.code = code;
        this.name = name;
        this.surname = surname;
        this.rg = rg;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.cellPhone = cellPhone;
        this.telephone = telephone;
        this.cellPhone1 = cellPhone1;
        this.telephone1 = telephone1;
    }

    public User() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellPhone1() {
        return cellPhone1;
    }

    public void setCellPhone1(String cellPhone1) {
        this.cellPhone1 = cellPhone1;
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1;
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDateOfBirth = (dateOfBirth != null) ? dateFormat.format(dateOfBirth) : "N/A";

        return "User{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", rg='" + rg + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dateOfBirth=" + formattedDateOfBirth +
                ", cellPhone='" + cellPhone + '\'' +
                ", telephone='" + telephone + '\'' +
                ", cellPhone1='" + cellPhone1 + '\'' +
                ", telephone1='" + telephone1 + '\'' +
                '}';
    }
}

// Agora vamos utilizar um padrão de projeto chamado DAO
// Esse padrão de projeto tira toda a complexibilidade da camada de visão, ou da camada de negócio para fazer um acesso
// ao banco de dados e executar através desse acesso, comando SQL.
// crie uma pasta chamada dao e dentro uma interface UserDAO