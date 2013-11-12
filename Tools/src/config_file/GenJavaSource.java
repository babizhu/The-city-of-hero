package config_file;

import org.apache.poi.ss.usermodel.Sheet;
import util.D;
import util.Util;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * 通过xml生成相应的配置文件
 * User: Administrator
 * Date: 13-11-5
 * Time: 下午4:41
 * To change this template use File | Settings | File Templates.
 */



class GenJavaSource extends AbstractGenJava {

    private List<FieldElement> fields;

    /**
     *
     * @param path      仅包括包名和类名
     * @param sheet     execl的sheet
     */
    public GenJavaSource (String[] path, Sheet sheet) {
        super( path, sheet );
        fields = new FieldElimentManager( sheet ).getFields();
    }

    public void generate(){
        genMisc();

        genFields();
        genToString();
        genConstruct();
        writeFile();
    }

    @Override
    protected String getTemplet() {
        return D.JAVA_TEMPLET_FILE;
    }

    @Override
    protected String buildClassName(String name) {
        return Util.firstToUpperCase(name) + "Templet";

    }



    /**
     * 生成toString方法
     */
    private void genToString() {

        StringBuilder sb = new StringBuilder();
        for( FieldElement fe : fields ){
            String temp = fe.name + " = \" + " + fe.name + " + \",";
            sb.append( temp );
        }

        javaContent = javaContent.replace( D.TO_STRING_TAG, sb.substring( 0, sb.length() - 5 ) );
    }

    private void genMisc() {
        String packageInFile = D.CFG_DIR.replace('/', '.') + packageName ;
        javaContent = javaContent.
                replace( D.DATE_TAG, DateFormat.getDateTimeInstance().format( new Date() ) ).
                replace( D.CLASS_NAME_TAG, className ).
                replace( D.PACAKAGE_NAME_TAG, packageInFile );

    }

    private void genFields() {
        StringBuilder sb = new StringBuilder();
        for( FieldElement fe : fields ){
            sb.append( genField( fe ) );
        }

//        for (Row row : sheet) {
//            if( index++ > 4 ){
//                break;
//            }
//            for (Cell cell : row) {
//
//                System.out.print(cell.toString() + "\t");
//
//            }
//            System.out.println();
//        }
        javaContent = javaContent.replace( D.FIELD_AREA_TAG, sb.toString() );
    }

    private void genConstruct( )
    {
        StringBuilder sb = new StringBuilder();
        for( FieldElement fe : fields ){
            sb.
                append(fe.name).append( " = ").
                append(parseJavaType(fe)).
                append("\").trim()");
            if( !fe.type.equals("String") ){
                sb.append( " )" );
            }
            sb.
                append(";").
                append(System.lineSeparator());
        }

//        zoneId = Short.parseShort( element.getChildText( "zone_id" ) );
        javaContent = javaContent.replace( D.CONSTRUCT_TAG, sb.toString() );

    }



    private String genField( FieldElement fe )
    {
        String ret = new TempletFile(D.FIELD_TEMPLET_FILE).getTempletStr();
        ret = ret
                .replace( D.ANNOTATION_TAG, fe.announce )
                .replace( D.FIELD_TYPE_TAG, fe.type )
                .replace( D.FIELD_TAG, fe.name )
                .replace(D.METHOD_NAME_TAG, Util.firstToUpperCase(fe.name));

        return ret;
    }


    public static void main(String[] args) {

    }




}
