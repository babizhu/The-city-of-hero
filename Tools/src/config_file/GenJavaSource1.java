package config_file;

import org.apache.poi.ss.usermodel.Sheet;
import util.D;
import util.Util;

import java.text.DateFormat;
import java.util.Date;

/**
 * 通过xml生成相应的配置文件
 * User: Administrator
 * Date: 13-11-5
 * Time: 下午4:41
 * To change this template use File | Settings | File Templates.
 */

class GenJavaSource1 extends AbstractGenJava{

    private final String templetClass;
    private final String xmlNode;

    /**
     * @param path  仅包括包名和类名
     * @param sheet excel的sheet
     */
    public GenJavaSource1(String[] path, Sheet sheet) {
        super(path, sheet);
        int pos = className.indexOf( "Cfg" );
        templetClass = className.substring( 0, pos );
        xmlNode = path[1];
    }

    @Override
    protected void generate() {
        genMisc();
        //System.out.println( javaContent );
        writeFile();

    }

    private void genMisc(){
        String packageInFile = D.CFG_DIR.replace('/', '.') + packageName ;
        //HeroTemplets===>hero
        String xmlPath = templetClass.substring( 0, templetClass.indexOf( "Templet" ) );
        xmlPath = Util.firstToLowCase( xmlPath ) + ".xml";
        xmlPath = D.XML_RESOURCE_DIR + packageName + "/" + xmlPath;

        javaContent = javaContent.
                replace( D.DATE_TAG, DateFormat.getDateTimeInstance().format( new Date() ) ).
                replace( D.CLASS_NAME_TAG, className ).
                replace( D.TEPMLET_CLASS_NAME_TAG, templetClass ).
                replace( D.XML_PATH_TAG, xmlPath ).
                replace( D.MAP_NAME_TAG, Util.firstToLowCase( templetClass ) ).
                replace( D.XMl_NODE_TAG, xmlNode ).
                replace( D.PACAKAGE_NAME_TAG, packageInFile );

    }

    @Override
    protected String getTemplet() {
        return D.JAVA_TEMPLET_CFG_FILE;
    }

    @Override
    protected String buildClassName(String name) {
        return Util.firstToUpperCase(name) + "TempletCfg";
    }

//    public static void main(String[] args) {
//        GenJavaSource1 g = new GenJavaSource1();
//        g.generate();
//    }

}
