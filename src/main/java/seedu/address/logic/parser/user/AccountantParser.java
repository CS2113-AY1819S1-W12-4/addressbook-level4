//@@author liu-tianhang
package seedu.address.logic.parser.user;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.accountant.AnalyseCostsCommand;
import seedu.address.logic.commands.accountant.AnalyseProfitCommand;
import seedu.address.logic.commands.accountant.AnalyseRevenueCommand;
import seedu.address.logic.commands.user.ChangePasswordCommand;
import seedu.address.logic.commands.user.LogoutCommand;
import seedu.address.logic.parser.accountant.AnalyseCostsCommandParser;
import seedu.address.logic.parser.accountant.AnalyseProfitCommandParser;
import seedu.address.logic.parser.accountant.AnalyseRevenueCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AccountantParser {

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
        //===========login command========================//
        case ChangePasswordCommand.COMMAND_WORD:
            return new ChangePasswordCommandParser().parse(arguments);
        //===========accountant only command=============//

        case AnalyseCostsCommand.COMMAND_WORD:
            return new AnalyseCostsCommandParser ().parse (arguments);

        case AnalyseProfitCommand.COMMAND_WORD:
            return new AnalyseProfitCommandParser ().parse (arguments);

        case AnalyseRevenueCommand.COMMAND_WORD:
            return new AnalyseRevenueCommandParser ().parse (arguments);
        //=======general command=================//
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case LogoutCommand.COMMAND_WORD:
            return new LogoutCommand ();
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
