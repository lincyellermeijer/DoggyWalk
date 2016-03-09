package lincy_ellermeijer.doggywalk;

/**
 * Created by lincypc on 7-3-2016.
 */
public class Plant {
    private long id;
    private String plantTitle;
    private String plantDescription;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getPlantTitle() {
        return plantTitle;
    }

    public void setPlantTitle(String plantTitle) {
        this.plantTitle = plantTitle;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return plantTitle + '\n' + plantDescription;
    }

}
