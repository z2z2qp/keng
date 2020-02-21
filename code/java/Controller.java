package code.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Controller {

    public void getFile(HttpServletRequest request) {
        if (request instanceof AbstractMultipartHttpServletRequest) {
            var req = (AbstractMultipartHttpServletRequest) request;
            var files = req.getMultiFilesMap();
            var mFile = files.getFirst("key");
            var file = new File("file.jpg");
            mFile.transferTo(file);
        }
    }

    public void outFile(HttpServletResponse response) {
        var file = new File("file.jpg");
        try (var fis = new FileInputStream(file)) {
            int index = fis.available();
            byte[] buffer = new byte[index];
            fis.read(buffer);
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            response.getOutputStream(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}