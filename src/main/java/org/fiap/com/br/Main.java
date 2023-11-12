package org.fiap.com.br;
import org.fiap.com.br.dao.UserDAO;
import org.fiap.com.br.dao.UserDAOImpl;
import org.fiap.com.br.dao.WalletDAO;
import org.fiap.com.br.dao.WalletDAOImpl;
import org.fiap.com.br.database.ConnectionFactory;
import org.fiap.com.br.entity.User;
import org.fiap.com.br.entity.Wallet;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = ConnectionFactory.getConnection();

        // Criando a instância do userDAO e implementada UserDAOImpl de forma global
        UserDAO userDAO = new UserDAOImpl(connection);

        // Criando a instância do walletDAO e implementada WalletDAOImpl de forma global
        WalletDAO walletDAO = new WalletDAOImpl(connection);


        while(true) {
            System.out.println("Qual tabela do banco de dados você quer entrar:");
            System.out.println("1. USUÁRIO");
            System.out.println("2. CARTEIRA");
            System.out.println("3. SAIR");
            System.out.print("Digite uma das opções: ");
            int option = scanner.nextInt();
            System.out.println();

            switch (option) {
                case 1 -> {
                    while (true) {
                        System.out.println("Menu do que poderá fazer na tabela USUÁRIO:");
                        System.out.println("1. Adicionar");
                        System.out.println("2. Listar");
                        System.out.println("3. Listar um usuário");
                        System.out.println("4. Atualizar");
                        System.out.println("5. Deletar");
                        System.out.println("6. Sair");
                        System.out.print("Qual e a sua escolha?: ");
                        int choice = scanner.nextInt();
                        System.out.println();

                        switch (choice) {
                            case 1 -> {
                                // Criando usuario
                                User user = new User();
                                System.out.println("Preencha os dados do Usuário");

                                System.out.print("Código: ");
                                int code = scanner.nextInt();
                                scanner.nextLine();
                                user.setCode(code);

                                System.out.print("Nome: ");
                                String name = scanner.nextLine();
                                user.setName(name);

                                System.out.print("Sobrenome: ");
                                String surname = scanner.nextLine();
                                user.setSurname(surname);

                                System.out.print("RG: ");
                                String rg = scanner.nextLine();
                                user.setRg(rg);

                                System.out.print("CPF: ");
                                String cpf = scanner.nextLine();
                                user.setCpf(cpf);

                                // Formatando a data para o padrão brasil
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                System.out.print("Data de nascimento: ");
                                String date = scanner.nextLine();
                                try {
                                    user.setDateOfBirth(dateFormat.parse(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                System.out.print("Celular: ");
                                String cellPhone = scanner.nextLine();
                                user.setCellPhone(cellPhone);

                                System.out.print("Telefone: ");
                                String telephone = scanner.nextLine();
                                user.setTelephone(telephone);

                                System.out.print("Celular 1: ");
                                String cellPhone1 = scanner.nextLine();
                                user.setCellPhone1(cellPhone1);

                                System.out.print("Telefone 1: ");
                                String telephone1 = scanner.nextLine();
                                user.setTelephone1(telephone1);

                                // inserindo o usuário no banco de dados
                                userDAO.create(user);
                                System.out.println("Usuário adicionado com sucesso");
                            }
                            case 2 -> {
                                System.out.println("Lista de usários");

                                // Listando todos os usuários
                                List<User> users = userDAO.userList();
                                for (User u : users) {
                                    System.out.println(u.toString());
                                }
                            }
                            case 3 -> {
                                // Listando usuário pelo codigo
                                System.out.print("Digite o codigo do usuário: ");
                                int userCode = scanner.nextInt();
                                User searchUserCode = userDAO.searchUserCode(userCode);
                                System.out.println(searchUserCode.toString());
                            }
                            case 4 -> {
                                // Atualizando usuário
                                System.out.print("Digite o codigo do usuário que deseja atualizar: ");
                                int updateUserCode = scanner.nextInt();
                                scanner.nextLine();

                                // pegando o usuário para atualizar
                                User userToUpdate = userDAO.searchUserCode(updateUserCode);
                                if (userToUpdate != null) {
                                    // informações atualizadas do usuário
                                    System.out.print("Novo nome: ");
                                    String newName = scanner.nextLine();
                                    userToUpdate.setName(newName);

                                    // Atualizando as informações do usuário
                                    userDAO.update(userToUpdate);

                                    System.out.println("Usuário atualizado com sucesso");
                                } else {
                                    System.out.println("Usuário não encontrado.");
                                }

                                System.out.println();

                                // Lista atualizada
                                System.out.println("Lista atualizada");
                                List<User> newUsers = userDAO.userList();
                                for (User u : newUsers) {
                                    System.out.println(u.toString());
                                }
                            }
                            case 5 -> {
                                System.out.print("Digite o código do usuário que deseja remover: ");
                                int removeUserCode = scanner.nextInt();

                                // removendo usuário
                                userDAO.remove(removeUserCode);
                                System.out.println("Usuário removido com sucesso");

                                System.out.println();

                                // Lista atualizada
                                System.out.println("Lista atualizada");
                                List<User> newUsersList = userDAO.userList();
                                for (User u : newUsersList) {
                                    System.out.println(u.toString());
                                }
                            }
                            case 6 -> {
                                System.out.println("Saindo do programa!");
                                scanner.close();
                                System.exit(0);
                            }
                            default -> System.out.println("Escolha inválida. Insira uma opção válida.");
                        }
                        System.out.println();
                    }
                }
                case 2 -> {
                    while (true) {
                        System.out.println("Menu do que poderá fazer na tabela CARTEIRA:");
                        System.out.println("1. Adicionar");
                        System.out.println("2. Listar");
                        System.out.println("3. Listar uma carteira");
                        System.out.println("4. Atualizar");
                        System.out.println("5. Deletar");
                        System.out.println("6. Sair");
                        System.out.print("Qual e a sua escolha?: ");
                        int choice = scanner.nextInt();
                        System.out.println();

                        switch (choice) {
                            case 1 -> {
                                // Criando carteira
                                Wallet wallet = new Wallet();
                                System.out.println("Preencha os dados da Carteira");

                                System.out.print("Código: ");
                                int code = scanner.nextInt();
                                scanner.nextLine();
                                wallet.setCode(code);

                                System.out.print("Código do usuário: ");
                                int userCode = scanner.nextInt();
                                scanner.nextLine();
                                wallet.setUserCode(userCode);

                                System.out.print("Nome: ");
                                String name = scanner.nextLine();
                                wallet.setName(name);

                                System.out.print("Digite o valor do saldo: ");
                                double balance = scanner.nextDouble();
                                scanner.nextLine();
                                wallet.setBalance(balance);

                                System.out.print("Digite o valor das despesas: ");
                                double expenses = scanner.nextDouble();
                                scanner.nextLine();
                                wallet.setExpenses(expenses);

                                // inserindo a carteira no banco de dados
                                walletDAO.create(wallet);
                                System.out.println("Carteira adicionada com sucesso");
                            }
                            case 2 -> {
                                System.out.println("Lista de Caeteiras");

                                // Listando todas as carteiras
                                List<User> users = userDAO.userList();
                                List<Wallet> wallets = walletDAO.walletList();
                                for (Wallet w : wallets) {
                                    System.out.println(w.toString());
                                }
                            }
                            case 3 -> {
                                // Listando carteira pelo codigo
                                System.out.print("Digite o codigo da carteira: ");
                                int walletCode = scanner.nextInt();
                                Wallet searchWalletCode = walletDAO.searchWalletCode(walletCode);
                                System.out.println(searchWalletCode.toString());
                            }
                            case 4 -> {
                                // Atualizando carteira
                                System.out.print("Digite o codigo dao carteira que deseja atualizar: ");
                                int updateWalletCode = scanner.nextInt();
                                scanner.nextLine();

                                // pegando a carteira para atualizar
                                Wallet walletToUpdate = walletDAO.searchWalletCode(updateWalletCode);
                                if (walletToUpdate != null) {
                                    // informações atualizadas do usuário
                                    System.out.print("Novo nome: ");
                                    String newName = scanner.nextLine();
                                    walletToUpdate.setName(newName);

                                    // Atualizando as informações da carteira
                                    walletDAO.update(walletToUpdate);

                                    System.out.println("Carteira atualizado com sucesso");
                                } else {
                                    System.out.println("Carteira não encontrado.");
                                }

                                System.out.println();

                                // Lista atualizada
                                System.out.println("Lista atualizada");
                                List<Wallet> newWallets = walletDAO.walletList();
                                for (Wallet w : newWallets) {
                                    System.out.println(w.toString());
                                }
                            }
                            case 5 -> {
                                System.out.print("Digite o código da carteira que deseja remover: ");
                                int removeWalletCode = scanner.nextInt();

                                // removendo carteira
                                walletDAO.remove(removeWalletCode);
                                System.out.println("Carteira removida com sucesso");

                                System.out.println();

                                // Lista atualizada
                                System.out.println("Lista atualizada");
                                List<Wallet> newWallets = walletDAO.walletList();
                                for (Wallet w : newWallets) {
                                    System.out.println(w.toString());
                                }
                            }
                            case 6 -> {
                                System.out.println("Saindo do programa!");
                                scanner.close();
                                System.exit(0);
                            }
                            default -> System.out.println("Escolha inválida. Insira uma opção válida.");

                        }
                        System.out.println();
                    }
                }
                case 3 -> {
                    System.out.println("Saindo do programa!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Escolha inválida. Insira uma opção válida.");

            }

        }

    }

}