package view;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.MyLabel.LabelType;
import view.WindowButton.WindowButtonType;

public class MyStage extends Stage {
	private static double xOffset = 0;
	private static double yOffset = 0;
	private static Insets PADDING = new Insets(10);

	private ArrayList<Node> items;
	private VBox center;
	private BorderPane root;

	public MyStage(ArrayList<Node> items) {
		this(items, 200);
	}

	public MyStage(ArrayList<Node> items, int height) {
		this.setItems(items);
		this.initStyle(StageStyle.UNDECORATED);

		root = new BorderPane();
		root.setStyle(
				"-fx-background-color: white; -fx-background-radius: 50 0 50 0; -fx-border-width: 2; -fx-border-color: black;");

		HBox hbTop = agentsPanel(this);
		WindowButton exit = new WindowButton(WindowButtonType.EXIT);
		exit.getButton().setOnAction(e -> this.close());

		hbTop.setAlignment(Pos.CENTER_RIGHT);
		hbTop.setStyle("-fx-background-color:" + Util.PURPLE + ";");

		hbTop.getChildren().addAll(exit);

		center = new VBox(3);
		center.setPadding(PADDING);
		center.setStyle(" -fx-background-radius: 30 0 0 0;");

		for (Node node : items) {
			center.getChildren().add(node);
		}

		root.setTop(hbTop);
		root.setCenter(center);

		Scene scene = new Scene(root, 300, height);

		this.setScene(scene);
		this.show();
	}

	public ArrayList<Node> getItems() {
		return items;
	}

	public void setItems(ArrayList<Node> items) {
		this.items = items;
	}

	public void addItem(Node node) {
		center.getChildren().add(node);
	}

	public void setWindowTitle(String title) {
		MyLabel lblTop = new MyLabel(title, LabelType.FIELD);
		lblTop.setTextFill(Color.WHITE);
		lblTop.setTranslateX(-100);
		((HBox)root.getTop()).getChildren().add(((HBox)root.getTop()).getChildren().size()-1, lblTop);
	}
	
	public static HBox agentsPanel(final Stage primaryStage) {

		HBox thb = new HBox(2);
		thb.setPadding(new Insets(5));

		thb.setOnMousePressed(e -> {
			xOffset = e.getSceneX();
			yOffset = e.getSceneY();
		});
		thb.setOnMouseDragged(e -> {
			primaryStage.setX(e.getScreenX() - xOffset);
			primaryStage.setY(e.getScreenY() - yOffset);
		});

		return thb;

	}

}
