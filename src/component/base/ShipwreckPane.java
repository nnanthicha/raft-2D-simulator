package component.base;

import game.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import object.Shipwreck;

public class ShipwreckPane extends BorderPane {
	private Label label;
	private Button repairButton;

	public ShipwreckPane(String repairPart, Player player, Shipwreck shipwreck) {
		super();
		label = new Label();
		setLabel(repairPart);
		repairButton = new Button("Repair");
		setButtonAccess(repairPart, shipwreck);
		repairButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				boolean complete = shipwreck.repair(repairPart, player);
				if (complete) {
					repairButton.setText("complete");
					repairButton.setDisable(true);
					if (shipwreck.checkWinCondition()) {
						showWinning();
					}
				} else {
					showWarning();
				}
			}
		});
		setLeft(label);
		setRight(repairButton);
	}

	private void setLabel(String repairPart) {
		String label = repairPart;
		switch (repairPart) {
		case "Wood":
			label += " 50";
			break;
		case "Hinge":
			label += " 10";
			break;
		case "Nail":
			label += " 15";
			break;
		case "Rope":
			label += " 20";
			break;
		}
		this.label.setText(label);
	}

	private void setButtonAccess(String repairPart, Shipwreck shipwreck) {
		switch (repairPart) {
		case "Wood":
			if (!shipwreck.haswood())
				return;
			break;
		case "Hinge":
			if (!shipwreck.hasHinge())
				return;
			break;
		case "Nail":
			if (!shipwreck.hasNail())
				return;
			break;
		case "Rope":
			if (!shipwreck.hasRope())
				return;
			break;
		case "Canvas":
			if (!shipwreck.hasCanvas())
				return;
			break;
		case "Steering":
			if (!shipwreck.hasSteering())
				return;
			break;
		case "Engine":
			if (!shipwreck.hasEngine())
				return;
			break;
		case "Petrol":
			if (!shipwreck.hasPetrol())
				return;
			break;
		case "Titanium":
			if (!shipwreck.hasTitanium())
				return;
			break;
		case "Receiver":
			if (!shipwreck.hasReceiver())
				return;
			break;
		case "Antenna":
			if (!shipwreck.hasAntenna())
				return;
			break;
		}
		repairButton.setText("complete");
		repairButton.setDisable(true);
	}

	private void showWarning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Fail to repair shipwreck");
		alert.setHeaderText(null);
		alert.setContentText("Please check your inventory.");
		alert.showAndWait();
	}

	public void showWinning() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setContentText("YOU WIN !!!");
		alert.showAndWait();
	}

}
