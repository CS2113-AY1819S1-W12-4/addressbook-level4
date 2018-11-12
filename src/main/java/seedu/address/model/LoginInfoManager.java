package seedu.address.model;

import java.util.ArrayList;
import java.util.Objects;

import seedu.address.commons.core.LoginInfo;
import seedu.address.model.user.AuthenticationLevel;
import seedu.address.model.user.Password;
import seedu.address.model.user.UserName;


/**
 * Represents the facade of loginInfo
 */
public class LoginInfoManager implements LoginInfoModel {
    private ArrayList< LoginInfo > loginInfoList;

    public LoginInfoManager () {
        this.loginInfoList = initialSetUpOfAccount();
    }

    public LoginInfoManager (ArrayList< LoginInfo > loginInfoList) {
        this.loginInfoList = loginInfoList;
    }
    public LoginInfo getLoginInfo(UserName userName) {
        for (LoginInfo loginInfo : loginInfoList) {
            if (loginInfo.isUserNameMatched (userName)) {
                return loginInfo;
            }
        }
        return null;
    }

    @Override
    public boolean isUserNameExist(UserName userName) {
        LoginInfo user = getLoginInfo (userName);
        if (user == null) {
            return false;
        }
        return true;
    }
    @Override
    public void changePassword(UserName userName, Password newHashedPassword) {
        for (int i = 0; i < loginInfoList.size (); i++) {
            if (loginInfoList.get (i).isUserNameMatched(userName)) {
                loginInfoList.get (i).setPassword (newHashedPassword);
            }
        }
    }
    @Override
    public void deleteAccount(UserName userName) {
        for (int i = 0; i < loginInfoList.size (); i++) {
            if (loginInfoList.get (i).isUserNameMatched(userName)) {
                loginInfoList.remove (i);
            }
        }
    }

    @Override
    public void createNewAccount(LoginInfo newAccount) {
        loginInfoList.add (newAccount);
    }

    @Override
    public ArrayList< LoginInfo > getLoginInfoList() {
        return loginInfoList;
    }

    /**
     * Returns a predefined list of account.
     */
    private ArrayList<LoginInfo> initialSetUpOfAccount() {
        ArrayList<LoginInfo> loginInfoList = new ArrayList<>();
        UserName userName = new UserName ("tester");
        Password password = new Password ("Gcf70h4aWQ1T9NMxE03XM3nq3nCmFGihnO4xMzHMgP0=");
        AuthenticationLevel authenticationLevel = new AuthenticationLevel ("ADMIN");
        LoginInfo loginInfo = new LoginInfo (userName, password, authenticationLevel);
        loginInfoList.add (loginInfo);

        userName = new UserName ("manager");
        password = new Password ("Gcf70h4aWQ1T9NMxE03XM3nq3nCmFGihnO4xMzHMgP0=");
        authenticationLevel = new AuthenticationLevel ("MANAGER");
        loginInfo = new LoginInfo (userName, password, authenticationLevel);
        loginInfoList.add (loginInfo);

        userName = new UserName ("stocktaker");
        password = new Password ("Gcf70h4aWQ1T9NMxE03XM3nq3nCmFGihnO4xMzHMgP0=");
        authenticationLevel = new AuthenticationLevel ("STOCKTAKER");
        loginInfo = new LoginInfo (userName, password, authenticationLevel);
        loginInfoList.add (loginInfo);

        userName = new UserName ("accountant");
        password = new Password ("Gcf70h4aWQ1T9NMxE03XM3nq3nCmFGihnO4xMzHMgP0=");
        authenticationLevel = new AuthenticationLevel ("ACCOUNTANT");
        loginInfo = new LoginInfo (userName, password, authenticationLevel);
        loginInfoList.add (loginInfo);
        return loginInfoList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginInfoList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (LoginInfo loginInfo: loginInfoList) {

            sb.append("\nuserName : " + loginInfo.getUserNameString ());
            sb.append ("\npassword : " + loginInfo.getPasswordString ());
            sb.append ("\nauthenticationLevel : " + loginInfo.getAuthenticationLevelString ());
        }
        return sb.toString();
    }

}
