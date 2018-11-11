package seedu.address.logic.parser.user;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.ViewTransactionsCommand;
import seedu.address.logic.commands.manager.AddDrinkCommand;
import seedu.address.logic.commands.manager.DeleteCommand;
import seedu.address.logic.commands.user.ChangePasswordCommand;
import seedu.address.logic.commands.user.CreateAccountCommand;
import seedu.address.logic.commands.user.DeleteAccountCommand;
import seedu.address.logic.commands.user.LogoutCommand;
import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.EditCommandParser;
import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.SelectCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.manager.AddDrinkCommandParser;

/**
 * Parses user input.
 */
public class ManagerParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        String commandWord = matcher.group("commandWord");

        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        //=====================drink command====================//
        case AddDrinkCommand.COMMAND_WORD:
            return new AddDrinkCommandParser ().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser ().parse(arguments);
        //====================login command=====================//
        case CreateAccountCommand.COMMAND_WORD:
            return new CreateAccountCommandParser().parse(arguments);

        case ChangePasswordCommand.COMMAND_WORD:
            return new ChangePasswordCommandParser().parse(arguments);

        case DeleteAccountCommand.COMMAND_WORD:
            return new DeleteAccountCommandParser ().parse (arguments);

        case LogoutCommand.COMMAND_WORD:
            return new LogoutCommand ();
        //==============command function command===================//
        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser ().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ViewTransactionsCommand.COMMAND_WORD:
            return new ViewTransactionsCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
