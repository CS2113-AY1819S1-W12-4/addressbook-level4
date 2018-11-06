package seedu.address.authentication;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import seedu.address.model.LoginInfoManager;


public class LoginUtilsTest {
    private LoginUtils loginUtilsUserNameEmpty;
    private LoginUtils loginUtilsUserPasswordEmpty;
    private LoginUtils validUser;
    @Before
    public void typicalAccount() {
        loginUtilsUserNameEmpty = new LoginUtils ("", "123" , new LoginInfoManager ());
        loginUtilsUserPasswordEmpty = new LoginUtils ("tester", "" , new LoginInfoManager ());
        validUser = new LoginUtils ("tester", "123" , new LoginInfoManager ());
    }
    @Test
    public void isUsernameEmptyTest() {
        assertTrue(loginUtilsUserNameEmpty.isUsernameEmpty ());

        assertFalse(validUser.isUsernameEmpty ());
    }
    @Test
    public void isPasswordEmptyTest() {
        assertTrue (loginUtilsUserPasswordEmpty.isPasswordEmpty ());

        assertFalse (validUser.isPasswordEmpty ());
    }
    @Test
    public void isPasswordAndUserNameValidTest() {
        assertFalse (loginUtilsUserNameEmpty.isPasswordAndUserNameValid ());
        assertFalse (loginUtilsUserPasswordEmpty.isPasswordAndUserNameValid ());

        assertTrue (validUser.isPasswordAndUserNameValid ());

    }

}
