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
 * To change this template use File | Settings | File Templates.
 */
public class GenXml {
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
        sb.append( "<" + className + "s" + ">" );
        for( Row row : sheet ){
            if( row.getRowNum() < 3 ){
                continue;
            }
            sb.append( "<" + className + ">" );
            sb.append( genContent(row) );
            sb.append( "</" + className + ">" );
        }
        sb.append( "</" + className + "s" + ">" );
        System.out.println( sb.toString() );

        String path = D.XML_RESOURCE_DIR + packageName + "/" + className + ".xml";
        System.out.println( path );

        Util.writeFile( path, sb.toString() );

    }

    private String genContent( Row row ) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for( FieldElement element : fields ){
            sb.append( "<" ).append( element.name ).append( ">" );

            sb.append( row.getCell(i++) );
            sb.append( "</" ).append( element.name ).append( ">" );

        }
        return sb.toString();
    }
}
