package lincy_ellermeijer.doggywalk;

/**
 * Created by lincypc on 15-2-2016.
 */
public class ListItem {
    private String title;
    private String description;
    private int imageResource;

    //Constructor
    public ListItem(String title, String description, int imageResource) {
        this.title = title;
        this.description = description;
        this.imageResource = imageResource;
    }

    //Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResource;
    }
}