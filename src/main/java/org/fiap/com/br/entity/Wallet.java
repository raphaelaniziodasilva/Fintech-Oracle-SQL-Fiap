package org.fiap.com.br.entity;

import java.io.Serializable;

public class Wallet implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private int userCode;
    private String name;
    private double balance;
    private double expenses;

    public Wallet(int code, int userCode, String name, double balance, double expenses) {
        this.code = code;
        this.userCode = userCode;
        this.name = name;
        this.balance = balance;
        this.expenses = expenses;
    }

    public Wallet() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "code=" + code +
                ", userCode=" + userCode +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", expenses=" + expenses +
                '}';
    }
}
