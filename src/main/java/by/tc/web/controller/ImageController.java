package by.tc.web.controller;

import by.tc.web.controller.imagePath.PathProvider;
import by.tc.web.service.FilmService;
import by.tc.web.service.exception.ServiceException;
import by.tc.web.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;

public class ImageController extends HttpServlet {
    private static final FilmService filmService = ServiceFactory.getInstance().getFilmService();
    private static final String NAME_IMAGE = "nameImage";
    private static final String TYPE_IMAGE = "typeImage";
    private static final String POSTER = "poster";
    private static final String WIDE_SCREEN = "wideScreen";
    private static final String USER_PIC = "userPic";
    private static final String UTF_8 = "UTF-8";
    private static final String CONTENT_TYPE = "application/octet-stream";
    private static final String JPG = "jpg";
    private static final String DOT = ".";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestedFile = req.getParameter(NAME_IMAGE);
        String typeImage = req.getParameter(TYPE_IMAGE);
        String filePath = null;
        if (typeImage.equalsIgnoreCase(POSTER)) {
            filePath = PathProvider.getInstance().getPathToPoster();
        } else if (typeImage.equalsIgnoreCase(WIDE_SCREEN)) {
            filePath = PathProvider.getInstance().getPathToWideScreen();
        } else if (typeImage.equalsIgnoreCase(USER_PIC)) {
            filePath = PathProvider.getInstance().getPathToUserPic();
        }
        File file = new File(filePath, URLDecoder.decode(requestedFile, UTF_8));
        String contentType = getServletContext().getMimeType(file.getName());
        if (contentType == null) {
            contentType = CONTENT_TYPE;
        }
        resp.reset();
        resp.setContentType(contentType);
        BufferedOutputStream output = null;
        output = new BufferedOutputStream(resp.getOutputStream());
        byte[] buffer = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(buffer);
        fileInputStream.close();
        output.write(buffer);
        output.flush();
        output.close();
    }

    public static String saveImage(String typeName, byte[] data, String name) {
        BufferedImage image = null;
        String path = null;
        int count = 0;
        try {
            count = filmService.getAlig(name);
        } catch (ServiceException e) {
            Logger logger = Logger.getLogger(ImageController.class);
            logger.error(e.getMessage());
        }
        if (count != 0) {
            StringBuilder str = new StringBuilder(name);
            str.insert(str.indexOf(DOT), count);
            name = str.toString();
        }
        try {
            image = ImageIO.read(new ByteArrayInputStream(data));
            path = selectPath(typeName);
            ImageIO.write(image, JPG, new File(path + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static void deleteImage(String typeName, String fileName) {
        String path = null;
        path = selectPath(typeName);
        File file = new File(path + fileName);
        file.delete();
    }

    private static String selectPath(String typeName){
        if (typeName.equalsIgnoreCase(POSTER)) {
            return PathProvider.getInstance().getPathToPoster();
        } else if (typeName.equalsIgnoreCase(WIDE_SCREEN)) {
            return PathProvider.getInstance().getPathToWideScreen();
        } else if (typeName.equalsIgnoreCase(USER_PIC)) {
            return  PathProvider.getInstance().getPathToUserPic();
        }
        return null;
    }
}
