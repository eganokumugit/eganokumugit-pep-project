package Service;
import  Model.Account;

import DAO.AccountDAO;
public class AccountService 
{
    AccountDAO accDAO;

    public AccountService(AccountDAO accDAO)
    {
        this.accDAO = accDAO;
    }

    public Account registerAccount(Account acc)
    {
        if(accDAO.userExists(acc.getUsername()) == true)
        {
            return null;
        }
        else
        {
            accDAO.registerAccount(acc);
            return acc;
        }
    }

    public Account loginUser(String username, String password)
    {
        return accDAO.loginUser(username, password);
    }

}
