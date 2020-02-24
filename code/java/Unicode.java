import java.io.*;
import java.nio.charset.Charset;

public class Unicode {
    public static void main(String[] args) throws IOException {
        File f = null;
        // FileOutputStream os = new FileOutputStream(f);
        FileWriter writer = null;
        for (var i = 0; i < Character.MAX_VALUE; i++) {
            if (i % 1000000 == 0) {
                var name = Integer.toHexString(i);
                System.out.println(name);
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
                f = null;
                writer = null;
                f = new File("./c/" + name + ".txt");
                writer = new FileWriter(f, Charset.forName("UTF-8"));
            }
            writer.append((char) i);
            if (i % 100 == 0) {
                writer.append("\n");
            }
        }
    }
}