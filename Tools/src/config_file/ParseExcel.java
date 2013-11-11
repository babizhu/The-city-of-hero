package config_file;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import util.D;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 通过excel生成xml以及相应相应的java文件
 * User: Administrator
 * Date: 13-11-5
 * Time: 下午4:41
 */

public class ParseExcel {
    final String     excelFile;

    Sheet sheet;

    /**
     *path[0]为包名称，path[1]为文件名，比如excelFile为d:/game/resource/fighter/dogz,那么path[0]为fighter,path[1]为dogz
     */
    String[] path;


    public ParseExcel(String excelFile) {

        this.excelFile = D.RESOURCE_DIR + excelFile;
        int lastPointIndex = excelFile.lastIndexOf('.');
        String purePath = excelFile.substring( 0, lastPointIndex ); //fighter.hero
        path = purePath.split( "/" );
        openFile();
    }

    private void openFile(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream( excelFile );
            this.sheet =  new HSSFWorkbook( fis ).getSheetAt( 0 );

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    void gen(){

        new GenJavaSource( path, sheet).generate();
        new GenJavaSource1( path, sheet ).generate();

        new GenXml( path, sheet ).generate();
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir")  );
        String file = "fighter/dogz.xls";
        ParseExcel pe = new ParseExcel( file );
        pe.gen();


    }
}
