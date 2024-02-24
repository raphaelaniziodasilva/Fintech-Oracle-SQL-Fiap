package org.fiap.com.br.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Investment implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private Date investmentDate;
    private String name;
    private double value;
    private int percentage;
    private double profit;

    public Investment(int code, Date investmentDate, String name, double value, int percentage) {
        this.code = code;
        this.investmentDate = investmentDate;
        this.name = name;
        this.value = value;
        this.percentage = percentage;
        this.profit = 0;
    }

    public Investment() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getInvestmentDate() {
        return investmentDate;
    }

    public void setInvestmentDate(Date investmentDate) {
        this.investmentDate = investmentDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDateOfBirth = (investmentDate != null) ? dateFormat.format(investmentDate) : "N/A";
        return "Investment{" +
                "code=" + code +
                ", investmentDate=" + formattedDateOfBirth +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", percentage=" + percentage +
                ", profit=" + profit +
                '}';
    }
}
