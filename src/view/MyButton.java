package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MyButton extends Button {

	
	
	public MyButton(String text) {
		super(text);
		this.setAlignment(Pos.CENTER_LEFT);
		this.setOnMouseEntered(e -> this.setStyle(this.getStyle() + "-fx-cursor: hand; "));
		this.setOnMouseExited(e -> this.setStyle(this.getStyle() + "-fx-cursor: cursor; "));
	}
	
	public MyButton(String text, ImageView image) {
		super(text, image);
		this.setAlignment(Pos.CENTER_LEFT);
		this.setOnMouseEntered(e -> this.setStyle(this.getStyle() + "-fx-cursor: hand; "));
		this.setOnMouseExited(e -> this.setStyle(this.getStyle() + "-fx-cursor: cursor; "));
	}
	
	public void setRadius(int radius) {
		this.setStyle(this.getStyle()+"-fx-border-radius: "+radius+";");
	}
	
	public void setHeight(int height){
		this.setPrefHeight(height);
	}
	
	public void setWidth(int width){
		this.setPrefWidth(width);
	}
	
	
}
