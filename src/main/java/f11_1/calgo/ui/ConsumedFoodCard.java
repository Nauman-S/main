package f11_1.calgo.ui;

import f11_1.calgo.model.food.ConsumedFood;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class ConsumedFoodCard extends UiPart<Region> {

    private static final String FXML = "DailyListCard.fxml";

    public final ConsumedFood consumedFood;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label portion;
    @FXML
    private Label totalCalorie;

    public ConsumedFoodCard(ConsumedFood consumedFood, int displayedIndex) {
        super((FXML));
        this.consumedFood = consumedFood;
        id.setText(displayedIndex + ". ");
        name.setText(consumedFood.getName().fullName);
        portion.setText(String.valueOf(consumedFood.getPortion()));
        double calorieFromPortions = consumedFood.getPortion() * Double.parseDouble(consumedFood.getCalorie().value);
        totalCalorie.setText(String.valueOf(calorieFromPortions));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ConsumedFoodCard)) {
            return false;
        }

        // state check
        ConsumedFoodCard card = (ConsumedFoodCard) other;
        return id.getText().equals(card.id.getText())
                && consumedFood.equals(card.consumedFood);
    }

}