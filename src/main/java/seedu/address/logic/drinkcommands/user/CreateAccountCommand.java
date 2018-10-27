package seedu.address.logic.drinkcommands.user;

import static java.util.Objects.requireNonNull;
import static seedu.address.authentication.AuthenticationLevelConstant.AUTH_ACCOUNTANT;
import static seedu.address.authentication.AuthenticationLevelConstant.AUTH_ADMIN;
import static seedu.address.authentication.AuthenticationLevelConstant.AUTH_MANAGER;
import static seedu.address.authentication.AuthenticationLevelConstant.AUTH_STOCK_TAKER;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AUTHENTICATION_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import seedu.address.authentication.PasswordUtils;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.drinkcommands.DrinkCommandResult;
import seedu.address.logic.drinkcommands.exceptions.DrinkCommandException;
import seedu.address.model.DrinkModel;
import seedu.address.model.LoginInfoManager;
import seedu.address.model.Model;
import seedu.address.model.user.AuthenticationLevel;
import seedu.address.model.user.Password;
import seedu.address.model.user.UserName;
/**
 * Adds a person to the address book.
 */
public class CreateAccountCommand extends UserCommand {

    public static final String COMMAND_WORD = "createAccount";
    public static final String MESSAGE_DUPLICATE_USERNAME = "This userName already exists";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": change current user password "
            + "Parameters: "
            + PREFIX_USERNAME + "username: "
            + PREFIX_PASSWORD + "Password: "
            + PREFIX_AUTHENTICATION_LEVEL + "authentication level: ";

    public static final String MESSAGE_SUCCESS = "New account has been created";
    public static final String MESSAGE_WRONG_AUTHENTICATION_LEVEL = "Wrong authentication level : %1$s";
    public static final String MESSAGE_AUTHENTICATION_LEVEL_FORMAT = " either " + AUTH_ADMIN
            + " or " + AUTH_MANAGER
            + " or " + AUTH_STOCK_TAKER
            + " or " + AUTH_ACCOUNTANT;
    private final UserName userName;
    private final Password password;
    private final AuthenticationLevel authenticationLevel;
    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public CreateAccountCommand (UserName userName, Password password, AuthenticationLevel authenticationLevel) {
        requireNonNull(userName);
        requireNonNull (password);
        requireNonNull (authenticationLevel);
        Password hashedPassword = new Password (PasswordUtils.generateSecurePassword (password.toString ()));

        this.userName = userName;
        this.password = hashedPassword;
        this.authenticationLevel = authenticationLevel;
    }
    @Override
    public DrinkCommandResult execute(LoginInfoManager loginInfoManager, CommandHistory history)
            throws DrinkCommandException {
        requireNonNull(loginInfoManager);

        if (loginInfoManager.isUserNameExist (userName.toString ())) {
            throw new DrinkCommandException(MESSAGE_DUPLICATE_USERNAME);
        }
        loginInfoManager.createNewAccount (userName, password, authenticationLevel);
        return new DrinkCommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public DrinkCommandResult execute (DrinkModel model , CommandHistory history) {
        return null;
    }



}