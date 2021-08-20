package view;

import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class WindowButton extends HBox {

	public enum WindowButtonType {
		EXIT, MINIMIZE
	}

	private MyButton btn;

	public WindowButton(WindowButtonType type) {
		switch (type) {
		case EXIT:
			btn = new MyButton("X");

			btn.setOnAction(e -> {
				Platform.exit();
			});
			break;
		case MINIMIZE:
			btn = new MyButton("_");

			btn.setOnAction(e -> {
				((Stage)((MyButton)e.getSource()).getScene().getWindow()).setIconified(true);
			});
			break;
		default:
			break;
		}

		btn.setFont(Font.font("Verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 11));
		btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white");

		btn.setOnMouseEntered(e -> this.setStyle(this.getStyle() + "-fx-cursor: hand; "));
		btn.setOnMouseExited(e -> this.setStyle(this.getStyle() + "-fx-cursor: cursor; "));

		this.getChildren().add(btn);
	}
	
	public MyButton getButton() {
		return this.btn;
	}
}
