package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-5
 * Time: 下午6:24
 * To change this template use File | Settings | File Templates.
 */
public class Util {
    /**
     * 将第一个字符转换成大写
     * @param src
     * @return
     */
    public static final String firstToUpperCase(String src) {
        return src.replaceFirst(src.substring(0, 1), src.substring(0, 1).toUpperCase());
    }

    public static String firstToLowCase( String src ) {
        return src.replaceFirst(src.substring(0, 1), src.substring(0, 1).toLowerCase() );
    }

    public static void writeFile(String path, String content) {
//        OutputStream fos = null;
//        OutputStreamWriter osw = null;
//        try {
//            File f = new File(file);
//            if(!f.exists())	{
//                File path = new File(file.substring(0, file.lastIndexOf(File.separator)));
//                path.mkdirs();
//                f.createNewFile();
//            }
//
//            fos = new FileOutputStream(f);
//            osw = new OutputStreamWriter(fos,"UTF-8");
//            osw.write( javaContent );
//            osw.flush();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            Closer.close(fos);
//        }

        FileOutputStream out = null;
//
//        FileOutputStream outSTr = null;
//
//        BufferedOutputStream Buff=null;
//
//        FileWriter fw = null;
        //String path = D.SRC_DIR + D.CFG_DIR + packageName + "\\" + className + ".java";
        //System.out.println("real is " + path);
        try {
            File f = new File(path);
            if (!f.exists()) {
                File p = new File(path.substring(0, path.lastIndexOf('/')));
                p.mkdirs();
                //f.createNewFile();
            }

            out = new FileOutputStream(new File(path));
            out.write(content.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
