package lincy_ellermeijer.doggywalk;

import java.sql.Blob;

/**
 * Created by lincypc on 7-3-2016.
 */
public class Route {
    // private variables
    int _routeId;
    String _routeTitle;
    String _routeDescription;
    byte[] _routeImage;

    // Empty constructor
    public Route() {

    }

    // constructor
    public Route(int keyId, String routeTitle, String routeDescription, byte[] routeImage) {
        this._routeId = keyId;
        this._routeTitle = routeTitle;
        this._routeDescription = routeDescription;
        this._routeImage = routeImage;

    }

    // constructor
    public Route(String routeTitle, String routeDescription, byte[] routeImage) {
        this._routeTitle = routeTitle;
        this._routeDescription = routeDescription;
        this._routeImage = routeImage;
    }

    // getting ID
    public int getID() {
        return this._routeId;
    }

    // setting id
    public void setID(int keyId) {
        this._routeId = keyId;
    }

    // getting route title
    public String getRouteTitle() {
        return this._routeTitle;
    }

    // setting route title
    public void setRouteTitle(String routeTitle) {
        this._routeTitle = routeTitle;
    }

    // getting route description
    public String getRouteDescription() { return this._routeDescription; }

    // setting route description
    public void setRouteDescription(String routeDescription) {
        this._routeDescription = routeDescription;
    }

    // getting image
    public byte[] getRouteImage() {
        return this._routeImage;
    }

    // setting image
    public void setRouteImage(byte[] routeImage) {
        this._routeImage = routeImage;
    }
}