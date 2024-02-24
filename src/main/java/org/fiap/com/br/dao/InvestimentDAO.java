package org.fiap.com.br.dao;

import org.fiap.com.br.entity.Investment;

import java.util.List;

public interface InvestimentDAO {
    void create (Investment investment);
    List<Investment> investmentList();
    Investment searchInvestmentCode (int code);
    void update (Investment investment);
    void remove (int code);
}
