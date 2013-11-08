package config_file;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-8
 * Time: 下午4:54
 * To change this template use File | Settings | File Templates.
 */

import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * 保存一个filed的相关信息
 */

class FieldElement{
    String      announce;//field的注释
    String      name;
    String      type;
}
public class FieldElimentManager {

    private final Sheet sheet;

    public List<FieldElement> getFields() {
        return fields;
    }

    final List<FieldElement> fields = new ArrayList<FieldElement>();

    public FieldElimentManager(Sheet sheet) {
        this.sheet = sheet;
        genFiledElements();

    }

    private void genFiledElements(){
        int maxCol = sheet.getRow( 0 ).getLastCellNum();
        for( int col = 0; col < maxCol; col++ ) {
            FieldElement fe = new FieldElement();
            fe.announce = sheet.getRow( 0 ).getCell( col ).toString();
            fe.type = sheet.getRow( 1 ).getCell( col ).toString();
            fe.name = sheet.getRow( 2 ).getCell( col ).toString();
            fields.add( fe );
        }
    }
}
