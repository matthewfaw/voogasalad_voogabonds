package gamePlayerView;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


/**
 * @author Guhan Muruganandam
 * 
 */

public class GridView {
	
	private GridPane myrect= new GridPane(); 
	
	public GridView(){
		 GridPane grid = new GridPane();
		    grid.setHgap(10);
		    grid.setVgap(10);
		    grid.setPadding(new Insets(0, 10, 0, 10));

		    // Category in column 2, row 1
		    Text category = new Text("Sales:");
		    category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		    grid.add(category, 1, 0); 

		    // Title in column 3, row 1
		    Text chartTitle = new Text("Current Year");
		    chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		    grid.add(chartTitle, 2, 0);

		    // Subtitle in columns 2-3, row 2
		    Text chartSubtitle = new Text("Goods and Services");
		    grid.add(chartSubtitle, 1, 1, 2, 1);

		    myrect=grid;
	}

	public Node getView() {
		return myrect;
	}
}
