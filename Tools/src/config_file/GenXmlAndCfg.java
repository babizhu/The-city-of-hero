package config_file;

import util.D;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-12
 * Time: 下午4:54
 */

public class GenXmlAndCfg {


    void genAll( String path ){
        File dir = new File( path );
        File[] files = dir.listFiles();

        if (files == null)
            return;
        for (File file : files) {
            if (file.isDirectory()) {
                genAll(file.getAbsolutePath());
            } else {
                String strFileName = file.getAbsolutePath();
                genOne(strFileName);
                System.out.println("处理完毕 " + strFileName);

            }
        }
    }

    private void genOne( String realPath ){
        ParseExcel pe = new ParseExcel( realPath );
        pe.gen();
    }

    public static void main(String[] args) {
        GenXmlAndCfg gen = new GenXmlAndCfg();

        gen.genAll( D.EXECEL_DIR );
    }
}
