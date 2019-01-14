package application;

import javafx.scene.control.Label;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Cwiczenie3 extends Application{
	public static void main(String[] args){
		launch(args);
	}
	
	Button btnFile = new Button("Open file...");
	Label lblFile = new Label("");
	Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		btnFile.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				FileChooser fileChooser = new FileChooser();
				File file = fileChooser.showOpenDialog(primaryStage);
				if (file != null)
					try {
						lblFile.setText(file.getCanonicalPath());
					}
					catch (IOException ex) {
						lblFile.setText("B��d wyboru!");
					}
				else lblFile.setText("");
			}
		});
		
		FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL, 10, 10, btnFile, lblFile);		
		flowPane.setPadding(new Insets(10));
		flowPane.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(flowPane, 600, 200);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Anonymous Class");
		primaryStage.show();
	}

}