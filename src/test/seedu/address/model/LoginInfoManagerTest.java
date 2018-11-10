package seedu.address.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.commons.core.LoginInfo;
import seedu.address.model.user.AuthenticationLevel;
import seedu.address.model.user.Password;
import seedu.address.model.user.UserName;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginInfoManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private LoginInfoManager loginInfoManager = new LoginInfoManager ();

    private UserName expectedUserName = new UserName ("tester");
    private Password expectedPassword = new Password ("Gcf70h4aWQ1T9NMxE03XM3nq3nCmFGihnO4xMzHMgP0=");
    private AuthenticationLevel expectedAuthenticationLevel = new AuthenticationLevel ("ADMIN");
    private LoginInfo expectedLoginInfo = new LoginInfo (expectedUserName, expectedPassword, expectedAuthenticationLevel);

    @Test
    public void getLoginInfoTest(){

        assertEquals (loginInfoManager.getLoginInfo (expectedUserName).toString (), expectedLoginInfo.toString ());
    }

    @Test
    public void isUserNameExistTest(){
        UserName nonExistUserName = new UserName ("DoNotExist");
        assertFalse (loginInfoManager.isUserNameExist (nonExistUserName));

        assertTrue (loginInfoManager.isUserNameExist (expectedUserName));
    }
}
