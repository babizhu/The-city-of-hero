package config_file;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import util.D;
import util.Util;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-5
 * Time: 下午4:32
 */
class GenXml {

    private final List<FieldElement> fields;
    private final String className;
    private final String packageName;
    private final Sheet sheet;

    public GenXml(String[] path, Sheet sheet) {
        fields = new FieldElimentManager(sheet).getFields();
        className = path[1];
        packageName = path[0];
        this.sheet = sheet;
    }

    void generate() {

        StringBuilder sb = new StringBuilder( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" );
        sb.append("<").append(className).append("s").append(">");
        for( Row row : sheet ){
            if( row.getRowNum() < D.EXCEL_HEAD_COUNT ){
                continue;
            }
            sb.append("<").append(className).append(">").
                    append(genContent(row)).append("</").append(className).append(">");
        }
        sb.append("</").append(className).append("s").append(">");
        //System.out.println( sb.toString() );

        String path = D.XML_RESOURCE_DIR + packageName + "/" + Util.firstToLowCase( className ) + ".xml";
        Util.writeFile( path, sb.toString() );
//        06911523
//
//                主险保单号  51021173898000019940
//                保单号      51021103698000018283
//                投保单号     06911523
//                           512221441214002x
//
//                95519  1.2.8


    }

    private String genContent( Row row ) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for( FieldElement element : fields ){
            sb.append( "<" ).append( element.name ).append( ">" );
            String data = row.getCell(i++).toString();
            if( element.type.equals( "int" ) ){
                int pointPos = data.indexOf( '.' );
                if( pointPos != -1 ){
                    data = data.substring( 0, pointPos );//去掉末尾的.0
                }
            }

            //System.out.println( row.getCell(i++) );
            sb.append( data );
            sb.append( "</" ).append( element.name ).append( ">" );

        }
        return sb.toString();
    }
}
