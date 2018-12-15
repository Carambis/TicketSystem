package by.tc.web.controller.imagePath;

import java.util.ResourceBundle;

public class PathProvider {
    private final static PathProvider instance = new PathProvider();
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("img");
    private final String pathToPoster = resourceBundle.getString(PathParameter.IMG_PORSTER);
    private final String pathToWideScreen = resourceBundle.getString(PathParameter.IMG_WIDESCREEN);
    private final String pathToUserPic = resourceBundle.getString(PathParameter.IMG_USER_PIC);


    private PathProvider(){

    }

    public String getPathToPoster() {
        return pathToPoster;
    }

    public String getPathToWideScreen() {
        return pathToWideScreen;
    }

    public String getPathToUserPic() {
        return pathToUserPic;
    }

    public static PathProvider getInstance() {
        return instance;
    }
}
