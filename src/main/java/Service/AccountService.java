package Service;
import  Model.Account;

import DAO.AccountDAO;
public class AccountService 
{
    AccountDAO accDAO;
    public AccountService()
    {
        accDAO = new AccountDAO();
    }
    public AccountService(AccountDAO accDAO)
    {
        this.accDAO = accDAO;
    }

    public Account registerAccount(Account acc)
    {
        if(accDAO.userExists(acc.getUsername()) == false && acc.getUsername().length() > 0 && acc.getPassword().length() > 4)
        {
            //accDAO.registerAccount(acc);
            return accDAO.registerAccount(acc);
        }
        else
        {
            return null;
        }
    }

    public Account loginUser(Account acc)
    {
        return accDAO.loginUser(acc);
    }

}
