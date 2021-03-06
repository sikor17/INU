package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	private Label labelTitle, labelMesage, labelIcon, labelButtons;
	private static TextField textTitle;
	private static TextArea textMessage;
	private Button showWindow, close;
	private static Button btnYes, btnNo, btnCancel, btnAbort, btnRetry, btnIgnore;
	private static ComboBox<MessageBoxIcon> comboBoxIcon;
	private static ComboBox<MessageBoxButton> comboBoxButton;
	private static BorderPane border;
	private static FlowPane flow;
	private static Stage newStage;
	private static Image imageViewYes = new Image(
			ClassLoader.getSystemResourceAsStream("application/images/StatusOK_32x.png"));
	private static Image imageViewNo = new Image(
			ClassLoader.getSystemResourceAsStream("application/images/StatusNo_32xLG.png"));

	@Override
	public void start(Stage stage) {

		// panel vbox
		VBox root = new VBox();
		root.setPadding(new Insets(10));
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);

		// panel hbox1
		HBox hbox1 = new HBox();
		hbox1.setPadding(new Insets(10));
		hbox1.setSpacing(10);
		hbox1.setAlignment(Pos.TOP_LEFT);
		labelTitle = new Label("Tytu� okna");
	
		textTitle = new TextField("Tytu� okna");
		textTitle.setMinWidth(120);
		hbox1.getChildren().addAll(labelTitle, textTitle);

		// panel hbox2
		HBox hbox2 = new HBox();
		hbox2.setPadding(new Insets(10));
		hbox2.setSpacing(10);
		hbox2.setAlignment(Pos.TOP_LEFT);
		labelIcon = new Label("Ikona");
		comboBoxIcon = new ComboBox<>();
		ObservableList<String> options =  FXCollections.observableArrayList(  "Option 1",  "Option 2", "Option 3" );
		comboBoxIcon = new ComboBox(options);
		comboBoxIcon.setItems(FXCollections.observableArrayList(MessageBoxIcon.values()));
		comboBoxIcon.setOnAction(e -> {
			MessageBoxIcon selected = comboBoxIcon.getSelectionModel().getSelectedItem();
			textTitle.setText(selected.toString());
		});
		hbox2.getChildren().addAll(labelIcon, comboBoxIcon);

		// panel hbox3
		HBox hbox3 = new HBox();
		hbox3.setPadding(new Insets(10));
		hbox3.setSpacing(10);
		hbox3.setAlignment(Pos.TOP_LEFT);
		labelMesage = new Label("Tre�� wiadomo�ci");
		textMessage = new TextArea("Tre�� wiadomo�ci do okienka");
		textMessage.setMaxWidth(200);
		textMessage.setMaxHeight(100);
		textMessage.setWrapText(true);

		hbox3.getChildren().addAll(labelMesage, textMessage);

		// panel hbox4
		HBox hbox4 = new HBox();
		hbox4.setPadding(new Insets(10));
		hbox4.setSpacing(10);
		hbox4.setAlignment(Pos.TOP_LEFT);
		labelButtons = new Label("Predefiniowany zestaw przycisk�w");
		comboBoxButton = new ComboBox<>();
		comboBoxButton.setItems(FXCollections.observableArrayList(MessageBoxButton.values()));
		hbox4.getChildren().addAll(labelButtons, comboBoxButton);

		// panel hbox5
		HBox hbox5 = new HBox();
		hbox5.setPadding(new Insets(10));
		hbox5.setSpacing(10);
		hbox5.setAlignment(Pos.CENTER);
		showWindow = new Button("Poka� okno");
		showWindow.setOnAction(e -> show());
		close = new Button("Zamknij");
		close.setOnAction(e->Platform.exit());
		hbox5.getChildren().addAll(showWindow, close);
		root.getChildren().addAll(hbox1, hbox2, hbox3, hbox4, hbox5);

		Scene scene = new Scene(root, 400, 400);
		stage.setTitle("Praca zaliczeniowa INU");
		stage.setScene(scene);
		stage.centerOnScreen();

		stage.show();

		MessageBoxButton.AbortRetryIgnore.getCount();
	}

	public static void main(String args[]) {
		launch(args);
	}

	public static void show() {

		newStage = new Stage();
		newStage.setWidth(400);
		newStage.setHeight(180);
		String title = textTitle.getText();
		newStage.setTitle(title);
		newStage.setAlwaysOnTop(true);
		newStage.centerOnScreen();
		newStage.setResizable(false);
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case ENTER:
					if (btnYes != null)
						btnYes.fire();
					break;
				case ESCAPE:
					if (btnNo != null)
						btnNo.fire();
					break;
				default:

				}
			}
		});
		border = new BorderPane();
		border.setPadding(new Insets(20, 30, 20, 30));

		MessageBoxIcon mbi = comboBoxIcon.getSelectionModel().getSelectedItem();
		setIcon(mbi);

		String text = textMessage.getText();
		Label textLabel = new Label(text);
		textLabel.setWrapText(true);
		border.setCenter(textLabel);
		textLabel.setPadding(new Insets(5, 5, 5, 5));
		BorderPane.setAlignment(textLabel, Pos.TOP_LEFT);

		MessageBoxButton mbb = comboBoxButton.getSelectionModel().getSelectedItem();
		setButtons(mbb);

		Scene scene = new Scene(border);
		newStage.setScene(scene);
		newStage.showAndWait();
	}

	private static void setButtons(MessageBoxButton buttonSet) {

		if (buttonSet == null)
			buttonSet = MessageBoxButton.OK;

		switch (buttonSet) {

		case AbortRetryIgnore:
			btnAbort = new Button(MessageBoxButton.AbortRetryIgnore.getText(0));
			btnAbort.setPrefWidth(90.0);
			btnAbort.setPrefHeight(40.0);
			btnAbort.setDefaultButton(true);
			btnAbort.requestFocus();
			btnAbort.setOnAction(e -> newStage.close());
			btnRetry = new Button(MessageBoxButton.AbortRetryIgnore.getText(1));
			btnRetry.setPrefWidth(90.0);
			btnRetry.setPrefHeight(40.0);
			btnRetry.setOnAction(e -> newStage.close());
			btnIgnore = new Button(MessageBoxButton.AbortRetryIgnore.getText(2));
			btnIgnore.setPrefWidth(90.0);
			btnIgnore.setPrefHeight(40.0);
			btnIgnore.setOnAction(e -> newStage.close());
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnAbort);
			flow.getChildren().add(btnRetry);
			flow.getChildren().add(btnIgnore);
			border.setBottom(flow);

			break;
		case OK:
		default:
			btnYes = new Button(MessageBoxButton.OK.getText(0));
			btnYes.setPrefWidth(90.0);
			btnYes.setPrefHeight(40.0);
			btnYes.setDefaultButton(true);
			btnYes.setGraphic(new ImageView(imageViewYes));
			btnYes.setGraphicTextGap(10);
			btnYes.requestFocus();
			btnYes.setOnAction(e -> newStage.close());
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnYes);
			border.setBottom(flow);
			break;

		case OKCancel:
			btnYes = new Button(MessageBoxButton.OKCancel.getText(0));
			btnYes.setPrefWidth(90.0);
			btnYes.setPrefHeight(40.0);
			btnYes.setDefaultButton(true);
			btnYes.setGraphic(new ImageView(imageViewYes));
			btnYes.setGraphicTextGap(10);
			btnYes.requestFocus();
			btnYes.setOnAction(e -> newStage.close());
			btnCancel = new Button(MessageBoxButton.OKCancel.getText(1));
			btnCancel.setPrefWidth(90.0);
			btnCancel.setPrefHeight(40.0);
			btnCancel.setCancelButton(true);
			btnCancel.setOnAction(e -> newStage.close());
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnYes);
			flow.getChildren().add(btnCancel);
			border.setBottom(flow);
			break;
		case RetryCancel:
			btnRetry = new Button(MessageBoxButton.RetryCancel.getText(0));
			btnRetry.setPrefWidth(90.0);
			btnRetry.setPrefHeight(40.0);
			btnRetry.setDefaultButton(true);
			btnRetry.requestFocus();
			btnRetry.setOnAction(e -> newStage.close());
			btnCancel = new Button(MessageBoxButton.RetryCancel.getText(1));
			btnCancel.setPrefWidth(90.0);
			btnCancel.setPrefHeight(40.0);
			btnCancel.setCancelButton(true);
			btnCancel.setOnAction(e -> newStage.close());
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnRetry);
			flow.getChildren().add(btnCancel);
			border.setBottom(flow);
			break;
		case YesNo:
			btnYes = new Button(MessageBoxButton.YesNo.getText(0));
			btnYes.setPrefWidth(90.0);
			btnYes.setPrefHeight(40.0);
			btnYes.setDefaultButton(true);
			btnYes.setGraphic(new ImageView(imageViewYes));
			btnYes.setGraphicTextGap(10);
			btnYes.requestFocus();
			btnYes.setOnAction(e -> newStage.close());
			btnNo = new Button(MessageBoxButton.YesNo.getText(1));
			btnNo.setPrefWidth(90.0);
			btnNo.setPrefHeight(40.0);
			btnNo.setCancelButton(true);
			btnNo.setGraphic(new ImageView(imageViewNo));
			btnNo.setGraphicTextGap(10);
			btnNo.setOnAction(e -> newStage.close());
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnYes);
			flow.getChildren().add(btnNo);
			border.setBottom(flow);
			break;
		case YesNoCancel:
			btnYes = new Button(MessageBoxButton.YesNoCancel.getText(0));
			btnYes.setPrefWidth(90.0);
			btnYes.setPrefHeight(40.0);
			btnYes.setDefaultButton(true);
			btnYes.setGraphic(new ImageView(imageViewYes));
			btnYes.setGraphicTextGap(10);
			btnYes.requestFocus();
			btnYes.setOnAction(e -> newStage.close());
			btnNo = new Button(MessageBoxButton.YesNoCancel.getText(1));
			btnNo.setPrefWidth(90.0);
			btnNo.setPrefHeight(40.0);
			btnNo.setCancelButton(true);
			btnNo.setGraphic(new ImageView(imageViewNo));
			btnNo.setGraphicTextGap(10);
			btnNo.setOnAction(e -> newStage.close());
			btnCancel = new Button(MessageBoxButton.YesNoCancel.getText(2));
			btnCancel.setPrefWidth(90.0);
			btnCancel.setPrefHeight(40.0);
			btnCancel.setCancelButton(true);
			btnCancel.setOnAction(e -> newStage.close());
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnYes);
			flow.getChildren().add(btnNo);
			flow.getChildren().add(btnCancel);
			border.setBottom(flow);
			break;

		}
	}

	private static void setIcon(MessageBoxIcon iconSet) {
		if (iconSet == null)
			iconSet = MessageBoxIcon.Information;

		switch (iconSet) {

		case Information:
			Image imageInfo = new Image(
					ClassLoader.getSystemResourceAsStream("application/images/StatusInformation_64x.png"));
			ImageView imageViewInfo = new ImageView(imageInfo);
			border.setLeft(imageViewInfo);
			break;
		case Warning:
			Image imageWarn = new Image(
					ClassLoader.getSystemResourceAsStream("application/images/StatusWarning_64x.png"));
			ImageView imageViewWarn = new ImageView(imageWarn);
			border.setLeft(imageViewWarn);
			break;
		case Alert:
			Image imageAlert = new Image(
					ClassLoader.getSystemResourceAsStream("application/images/StatusAlert_64x.png"));
			ImageView imageViewAlert = new ImageView(imageAlert);
			border.setLeft(imageViewAlert);
			break;
		case CriticalError:
			Image imageCriticalError = new Image(
					ClassLoader.getSystemResourceAsStream("application/images/StatusCriticalError_64x.png"));
			ImageView imageViewCriticalError = new ImageView(imageCriticalError);
			border.setLeft(imageViewCriticalError);
			break;
		}
	}

}