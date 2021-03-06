package application;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Pair;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Optional<Pair<Environment, String>> result = (new LogonDialog("Logowanie", "Logowanie do systemu STYLEman"))
				.showAndWait();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Logowanie");
		if (result.isPresent()) {
			alert.setHeaderText("�rodowisko '" + result.get().getKey() + "'");
			alert.setContentText("U�ytkownik '" + result.get().getValue() + "' zosta� pomy�lnie zalogowany.");
		} else {
			alert.setAlertType(AlertType.WARNING);
			alert.setHeaderText(null);
			alert.setContentText("U�ytkownik nie zosta� zalogowany.");
		}
		alert.showAndWait();
	}
}
