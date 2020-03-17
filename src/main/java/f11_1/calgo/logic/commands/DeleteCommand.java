package f11_1.calgo.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import f11_1.calgo.logic.parser.CliSyntax;
import f11_1.calgo.model.Model;
import f11_1.calgo.commons.core.Messages;
import f11_1.calgo.commons.core.index.Index;
import f11_1.calgo.logic.commands.exceptions.CommandException;
import f11_1.calgo.model.food.Food;

/**
 * Deletes a food identified using its displayed index from the food record.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the food identified by the name of the food in the displayed food record.\n"
            + "Parameters: " + CliSyntax.PREFIX_NAME + "NAME "
            + "Example: " + COMMAND_WORD + " pizza";

    public static final String MESSAGE_DELETE_FOOD_SUCCESS = "Deleted food: %1$s";
    public static final String MESSAGE_FOOD_NOT_FOUND = "This food does not exist in the Food Records";


    private final Food toDelete;

    public DeleteCommand(Food food) {
        requireNonNull(food);
        toDelete = food;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasFood(toDelete)) {
            throw new CommandException(MESSAGE_FOOD_NOT_FOUND);
        }

        Food foodToDelete = model.getExistingFood(toDelete);

        model.deleteFood(foodToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_FOOD_SUCCESS, foodToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && toDelete.equals(((DeleteCommand) other).toDelete)); // state check
    }
}