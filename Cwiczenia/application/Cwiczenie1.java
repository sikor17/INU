package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Cwiczenie1 extends Application implements  EventHandler <ActionEvent>{
	public static void main(String[]args) {
		launch(args);
	}
	Stage primaryStage;

	final int step = 10;
	final int MAX_WIDTH = 800;
	final int MAX_HEIGHT = 800;
	final int MIN_WIDTH = 300;
	final int MIN_HEIGHT = 300;
	int width = MIN_WIDTH, height = MIN_HEIGHT; 
	Button btnEnlarge= new Button("Zwieksz");
	Button btnDecrease = new Button("Zmniejsz");
	
	public void start(Stage primaryStage){
		btnEnlarge.setOnAction(this);
		btnDecrease.setOnAction(this);
		
		FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL, 10, 10, btnEnlarge, btnDecrease);
		flowPane.setAlignment(Pos.CENTER);
		flowPane.setPadding(new Insets(20));
		
		Scene scene = new Scene(flowPane);
		
		this.primaryStage = primaryStage;
		primaryStage.setScene(scene);
		primaryStage.setTitle("Event Handler");
		primaryStage.centerOnScreen();
		primaryStage.setWidth(width);
		primaryStage.setHeight(height);
		primaryStage.show();
				
	}
	
	@Override
	public void handle(ActionEvent e){
		if (e.getSource()==btnEnlarge){
			width = Math.min(MAX_WIDTH, width + step);
			height = Math.min(MAX_HEIGHT, height + step);
		}
		else if (e.getSource()==btnDecrease){
			width = Math.max(MIN_WIDTH, width - step);
			height = Math.max(MIN_HEIGHT, height - step);
		}
		else
			System.out.println("Nierozpoznana kontrolka");
		
		primaryStage.setWidth(width);
		primaryStage.setHeight(height);
	}
}
