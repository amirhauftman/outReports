package view;

import javafx.scene.image.ImageView;

public class SideBarButton extends MyButton {

	public SideBarButton(String text) {
		super(text);
		this.setPrefWidth(240);
		this.setStyle("-fx-text-fill: white; -fx-background-color: "+Util.PURPLE+";-fx-line-spacing: 33px ;-fx-font-size: 22px ;-fx-background-radius:0;");
		this.setOnMouseEntered(e -> {this.setStyle(this.getStyle()+"-fx-text-fill: "+Util.PURPLE+"; -fx-background-color: white;-fx-cursor: hand; ");});
		this.setOnMouseExited(e -> {this.setStyle(this.getStyle()+"-fx-text-fill: white; -fx-background-color: "+Util.PURPLE+";-fx-cursor: cursor; ");});
	}
	
	public SideBarButton(String text, ImageView image) {
		super(text, image);
		this.setPrefWidth(240);
		this.setStyle("-fx-graphic-text-gap: 15px; -fx-padding: 10px 20px ; -fx-text-fill: white; -fx-background-color: "+Util.PURPLE+";-fx-line-spacing: 33px ;-fx-font-size: 22px ;-fx-background-radius:0;");
		this.setOnMouseEntered(e -> {this.setStyle(this.getStyle()+"-fx-text-fill: "+Util.PURPLE+"; -fx-background-color: white;-fx-cursor: hand; ");});
		this.setOnMouseExited(e -> {this.setStyle(this.getStyle()+"-fx-text-fill: white; -fx-background-color: "+Util.PURPLE+";-fx-cursor: cursor; ");});
	}
	
}
