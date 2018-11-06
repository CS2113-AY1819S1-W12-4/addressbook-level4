package seedu.address.logic.parser.user;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SelectCommand;
import seedu.address.logic.commands.ViewTransactionsCommand;
import seedu.address.logic.commands.stocktaker.BuyDrinkCommand;
import seedu.address.logic.commands.stocktaker.SellDrinkCommand;
import seedu.address.logic.commands.user.ChangePasswordCommand;
import seedu.address.logic.commands.user.LogoutCommand;
import seedu.address.logic.parser.stocktaker.BuyDrinkCommandParser;
import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.SelectCommandParser;
import seedu.address.logic.parser.stocktaker.SellDrinkCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses user input.
 */
public class StockTakerParser {

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


        case ChangePasswordCommand.COMMAND_WORD:
            return new ChangePasswordCommandParser().parse(arguments);


        case LogoutCommand.COMMAND_WORD:
            return new LogoutCommand ();

        // ========= stock taker commands ==================
        case SellDrinkCommand.COMMAND_WORD:
            return new SellDrinkCommandParser ().parse(arguments);

        case BuyDrinkCommand.COMMAND_WORD:
            return new BuyDrinkCommandParser ().parse(arguments);

        case ViewTransactionsCommand.COMMAND_WORD:
            return new ViewTransactionsCommand();

        //============general commands==========================

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();


        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
