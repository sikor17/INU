package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
    public void start(Stage stage) {
 
        HBox root = new HBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
 
        Button button1 = new Button("Informacja");
        Button button2 = new Button("Ostrze�enie");
        Button button3 = new Button("B��d");
        Button button4 = new Button("Okno potwierdzenia");
 
        button1.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Information.showInfo();
            }
        });
 
        button2.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
           //     showAlertWithDefaultHeaderText();
            }
        });
 
        button3.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            //    showAlertWithoutHeaderText();
            }
        });
 
        button4.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
            //    showAlertWithoutHeaderText();
            }
        });
        
        root.getChildren().addAll(button1, button2, button3, button4);
 
        Scene scene = new Scene(root, 450, 250);
        stage.setTitle("Projekt INU");
        stage.setScene(scene);
 
        stage.show();
 
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
