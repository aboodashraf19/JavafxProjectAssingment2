/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Abood
 */
import Model.Account1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private List<Account1> accounts;

    public AccountDAO() {
        this.accounts = new ArrayList<>();
    }

    public void createAccount(Account1 account) {
        accounts.add(account);
        
         try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "username", "password")) {
            String insertQuery = "INSERT INTO accounts (id, name, balance) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setInt(1, account.getId());
            statement.setString(2, account.getName());
            statement.setDouble(3, (double) account.getBalance());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(int id, Account1 updatedAccount) {
        Account1 accountToUpdate = getAccountById(id);
        if (accountToUpdate != null) {
            accountToUpdate.setName(updatedAccount.getName());
            accountToUpdate.setBalance(updatedAccount.getBalance());
        }
         try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "username", "password")) {
            String updateQuery = "UPDATE accounts SET name = ?, balance = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, account1.getName());
            statement.setDouble(2, account1.getBalance());
            statement.setInt(3, account1.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(int id) {
        Account1 accountToDelete = getAccountById(id);
        if (accountToDelete != null) {
            accounts.remove(accountToDelete);
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "username", "password")) {
            String deleteQuery = "DELETE FROM accounts WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            int accountId = 0;
            statement.setInt(1, accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

      public List<Account1> getAllAccounts() {
        List<Account1> accounts = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "username", "password")) {
            String selectQuery = "SELECT * FROM accounts";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double balance = resultSet.getDouble("balance");

                Account1 account = new Account1(id, name, balance);
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    public Account1 getAccountById(int accountId) {
        Account1 account = null;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "username", "password")) {
            String selectQuery = "SELECT * FROM accounts WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, accountId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double balance = resultSet.getDouble("balance");

                account = new Account1(id, name, balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }
}
