package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class LogonDialog extends Application {

	/*
	 * private Dialog<Pair<Environment, String>> dialog; private
	 * ChoiceBox<Environment> cbxEnv; private ComboBox<String> cbxUsers; private
	 * PasswordField passField; private ButtonType loginBtnType; private
	 * ButtonType cancelBtnType;
	 */
	// private GridPane grid;

	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20));
		grid.setHgap(20);
		grid.setVgap(20);
		Scene scene = new Scene(root, 400, 350);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Logowanie");

		Label labelTitle = new Label("Logowanie do systemu STYLEman");
		GridPane.setHalignment(labelTitle, HPos.LEFT);
		grid.add(labelTitle, 0, 0, 3, 1);

		Image image = new Image(ClassLoader.getSystemResourceAsStream("application/Login_64x.png"));
		ImageView imageView = new ImageView(image);
		GridPane.setHalignment(imageView, HPos.RIGHT);
		grid.add(imageView, 2, 0);

		Line oxLine = new Line(0, 0, 350, 0);
		oxLine.setStrokeWidth(1);
		oxLine.setStroke(Color.BLACK);
		grid.add(oxLine, 0, 1, 3, 1);

		Label labelEnvironment = new Label("�rodowisko:");
		GridPane.setHalignment(labelEnvironment, HPos.LEFT);
		grid.add(labelEnvironment, 1, 2);

		Environment prod = new Environment("Produkcyjne");
		Environment test = new Environment("Testowe");
		Environment dev = new Environment("Deweloperskie");

		ObservableList<Environment> list = FXCollections.observableArrayList(prod, test, dev);
		ChoiceBox<Environment> cbxEnv = new ChoiceBox<Environment>(list);

		cbxEnv.setItems(list);
		cbxEnv.setPrefSize(200, 20);
		cbxEnv.getSelectionModel().select(1);
		GridPane.setHalignment(cbxEnv, HPos.RIGHT);
		grid.add(cbxEnv, 2, 2);

		Label labelUser = new Label("U�ytkownik:");
		GridPane.setHalignment(labelUser, HPos.LEFT);
		grid.add(labelUser, 1, 3);
		
		
		Label labelPass = new Label("Has�o:");
		GridPane.setHalignment(labelPass, HPos.LEFT);
		grid.add(labelPass, 1, 4);
		
		
		 ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		 Dialog<String> dialog = new Dialog<>();
		 dialog.getDialogPane().getButtonTypes().add(loginButtonType);
		 boolean disabled = false; // computed based on content of text fields, for example
		 dialog.getDialogPane().lookupButton(loginButtonType).setDisable(disabled);

		
		
		

		/*
		 * cbxEnv.valueProperty().addListener( (observable, oldVal, newVal) ->
		 * cbxEnv_Changed(newVal)); cbxUsers.valueProperty().addListener(
		 * (observable, oldVal, newVal) -> cbxUsers_Changed(newVal));
		 * passField.textProperty().addListener( (observable, oldVal, newVal) ->
		 * passField_Changed(newVal)); dialog.getDialogPane().setContent(grid);
		 */

		root.getChildren().add(grid);

		stage.show();

	}
	/*
	 * private Object passField_Changed(String newVal) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * private Object cbxUsers_Changed(String newVal) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * private Object cbxEnv_Changed(Environment newVal) { // TODO
	 * Auto-generated method stub return null; }
	 */

	public static void main(String[] args) {
		launch(args);
	}
}
