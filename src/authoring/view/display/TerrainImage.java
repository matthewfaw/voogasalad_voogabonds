package authoring.view.display;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Christopher Lu
 * Creates a TerrainImage object so that imageview's image url's can be passed into imageGallery and gridToolbar.
 */

public class TerrainImage extends ImageView {
	
	private String imagePath;
	private Image image;
	private ImageGallery imgGal;
	
	public TerrainImage(String imgPath, ImageGallery gal) {
		this.imagePath = imgPath;
		this.imgGal= gal;
		this.image = new Image(imgPath);
		this.setImage(image);
		clickHandler();
	}

	private void clickHandler() {
		this.setOnMouseClicked(click -> {
			imgGal.setSelectedImagePath(imagePath);
		});
	}
	
}
