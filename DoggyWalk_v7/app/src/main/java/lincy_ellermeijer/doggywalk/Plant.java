package lincy_ellermeijer.doggywalk;

import java.sql.Blob;

/**
 * Created by lincypc on 7-3-2016.
 */
public class Plant {
    // private variables
    int _plantId;
    String _plantTitle;
    String _plantDescription;
    byte[] _plantImage;

    // Empty constructor
    public Plant() {

    }

    // constructor
    public Plant(int keyId, String plantTitle, String plantDescription, byte[] plantImage) {
        this._plantId = keyId;
        this._plantTitle = plantTitle;
        this._plantDescription = plantDescription;
        this._plantImage = plantImage;

    }

    // constructor
    public Plant(String plantTitle, String plantDescription, byte[] plantImage) {
        this._plantTitle = plantTitle;
        this._plantDescription = plantDescription;
        this._plantImage = plantImage;
    }

    // getting ID
    public int getID() {
        return this._plantId;
    }

    // setting id
    public void setID(int keyId) {
        this._plantId = keyId;
    }

    // getting plant title
    public String getPlantTitle() {
        return this._plantTitle;
    }

    // setting plant title
    public void setPlantTitle(String plantTitle) {
        this._plantTitle = plantTitle;
    }

    // getting plant description
    public String getPlantDescription() {
        return this._plantDescription;
    }

    // setting plant description
    public void setPlantDescription(String plantDescription) {
        this._plantDescription = plantDescription;
    }

    // getting image
    public byte[] getPlantImage() {
        return this._plantImage;
    }

    // setting image
    public void setPlantImage(byte[] plantImage) {
        this._plantImage = plantImage;
    }
}