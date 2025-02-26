package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account addAccount(Account account){
        if(account.getUsername() != "" && account.getPassword().length() >= 4 && accountDAO.getAccountByUsername(account.getUsername()) == null){
            return accountDAO.insertAccount(account);
        }
        return null;
    }

    public Account loginAccount(Account account){
        Account newAccount = accountDAO.getAccountByUsername(account.getUsername());
        if (account.getPassword() == newAccount.getPassword()) {
            return newAccount;
        }
        return null;
    }
}
