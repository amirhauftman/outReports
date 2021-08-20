package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MyLabel extends Label {

	public static final Font SMALL_FONT = Font.font("Verdana", FontWeight.LIGHT, FontPosture.REGULAR, 11);
	public static final Font MEDIUM_FONT = Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16);
	public static final Font MEDIUM_LARGE_FONT = Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 22);
	public static final Font LARGE_FONT = Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 26);

	public enum LabelType {
		FIELD, SUB_HEADER, HEADER, DATA
	}

	private LabelType type;

	public MyLabel(String text, LabelType type) {
		super(text);
		this.type = type;
		this.setWrapText(true);
		switch (this.type) {
		
		case HEADER:
			this.setFont(LARGE_FONT);
			this.setPadding(new Insets(0, 0, 40, 0));
			break;
		case DATA:
			this.setStyle("-fx-backgrond: " + "white" + ";" + "-fx-background-radius: " + "10" + ";" + "-fx-border-color: "+Util.BLUE+ ";"+"-fx-border-width: 2;" +
		"-fx-border-radius:"+"10"+";" );
			this.setPrefWidth(110);
			this.setPrefHeight(90);	
			this.setAlignment(Pos.CENTER);
		case SUB_HEADER:
			this.setFont(MEDIUM_LARGE_FONT);
			this.setPadding(new Insets(0, 0, 20, 0));
			break;
		case FIELD:
			this.setFont(MEDIUM_FONT);
			break;

		default:
			break;
		}
	}

}
