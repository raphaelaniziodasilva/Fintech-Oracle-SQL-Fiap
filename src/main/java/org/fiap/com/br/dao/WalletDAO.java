package org.fiap.com.br.dao;

import org.fiap.com.br.entity.Wallet;
import java.util.List;

public interface WalletDAO {
    void create (Wallet wallet);
    List<Wallet> walletList();
    Wallet searchWalletCode (int code);
    void update (Wallet user);
    void remove (int code);
}
