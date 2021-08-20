package view;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;

public class CategoryButton extends MyButton {
	private final int RADIUS = 10;
	
	public CategoryButton(String text) {
		super(text);
		setGraphics();

	}

	public CategoryButton(String text, ImageView image) {
		super(text, image);
		setGraphics();
	}

	private void setGraphics() {
		this.setMinWidth(110);
		this.setMaxWidth(220);
		this.setHeight(34);
		this.setStyle(this.getStyle() + getNormalStyle());
		this.setRadius(10);
		this.setAlignment(Pos.CENTER);
		this.setOnMouseEntered(e -> {
			this.setStyle(this.getStyle() + getHoveredStyle());
		});
		this.setOnMouseExited(e -> {
			this.setStyle(this.getStyle() + getNormalStyle());
		});
	}

	private String getNormalStyle() {
		return "" + "-fx-background-color: white; " + "-fx-border-color: " + Util.PURPLE + ";" + "-fx-border-width: 2;"
				+ "-fx-font-family:Verdana;" + "-fx-font-size: 16;" + "-fx-text-alignment: center;"
				+ "-fx-cursor: cursor;"+"-fx-border-radius: "+RADIUS+";" + "-fx-text-fill: "+Util.PURPLE+";";
	}

	private String getHoveredStyle() {
		return "" + "-fx-background-color: " + Util.PURPLE + "; " + "-fx-border-color: " + Util.PURPLE + ";"
				+ "-fx-border-width: 2;" + "-fx-font-family:Verdana;" + "-fx-font-size: 16;" + "-fx-text-fill: "+Util.YELLOW+";"
				+ "-fx-text-alignment: center;" + "-fx-cursor: hand;"+"-fx-border-radius: "+RADIUS+";" + "-fx-background-radius: "+RADIUS+";";
	}

}
