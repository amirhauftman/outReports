package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class MyInputField extends HBox {

	private static int spacing = 10;
	private static int LBL_WIDTH = 100;
	enum InputType {
		TEXT, NUMBER, PASSWORD
	}

	private Label lblMain;
	private TextField field;

	public MyInputField(String label, InputType type) {
		super();
		this.setSpacing(spacing);
		this.lblMain = new Label(label);
		this.lblMain.setPrefWidth(LBL_WIDTH);
		switch (type) {
		case TEXT:
			field = new TextField();
			break;
		case NUMBER:
			field = new TextField();
			field.textProperty().addListener(new ChangeListener<String>() {
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						field.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}
			});
			break;
		case PASSWORD:
			field = new PasswordField();
			field.setPromptText("Your password");
		}
		this.getChildren().addAll(lblMain, field);
		this.setAlignment(Pos.CENTER_LEFT);

	}

	
	public MyInputField(String label, InputType type, int labelWidth) {
		this(label, type);
		this.lblMain.setPrefWidth(labelWidth);
	}
	
	public String getValue() {
		return ((TextField) this.field).getText();
	}

	public TextField getField() {
		return this.field;
	}

}
