package co.edu.unbosque.beans;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class CarouselBean implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> images = Arrays.asList(
        "images/bannner1.png",
        "images/bannner2.png",
        "images/bannner3.png",
        "images/bannner4.png"
    );

    public List<String> getImages() {
        return images;
    }
}

