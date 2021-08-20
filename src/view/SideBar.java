package view;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class SideBar extends VBox {

	private Label lblHeader;
	private ArrayList<SideBarButton> buttons;

	public SideBar(int Spacing, String label, Map<String, ImageView> buttons) {
		this.setSpacing(Spacing);
		this.lblHeader = new Label(label);
		this.lblHeader.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 22));
		this.lblHeader.setStyle("-fx-text-fill: white;");
		this.lblHeader.setPadding(new Insets(5, 40, 20, 40));
		this.getChildren().add(lblHeader);
		this.buttons = new ArrayList<>();
		for (Entry<String, ImageView> entry: buttons.entrySet()) {
			this.buttons.add(new SideBarButton(entry.getKey(), entry.getValue()));
			this.getChildren().add(this.buttons.get(this.buttons.size() - 1));
		}
		this.setStyle("-fx-background-color: " + Util.PURPLE);

	}

	public ArrayList<SideBarButton> getButtons(){
		return this.buttons;
	}
}
