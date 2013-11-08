package config_file;

import org.apache.poi.ss.usermodel.Sheet;
import util.D;
import util.Util;


public abstract class AbstractGenJava {


    /**
     * 包名，例如game.cfg.fighter.HeroTemplet.java文件的包名fighter
     */
    String packageName;

    String className;

    final Sheet sheet;
    String javaContent;


    //private List<FieldElement> fields = new ArrayList<FieldElement>();

    /**
     * @param path  仅包括包名和类名
     * @param sheet excel的sheet
     */
    public AbstractGenJava(String[] path, Sheet sheet) {

        this.sheet = sheet;

        className = buildClassName(path[1]);
        packageName = path[0];
        javaContent = new TempletFile( getTemplet() ).getTempletStr();
    }


    protected abstract void generate();

    protected abstract String getTemplet();


    protected String parseJavaType(FieldElement fe) {
        String type = fe.type;
        if (type.equals("int")) {
            return "Integer.parseInt( element.getChildText(\"" + fe.name;
        } else if (type.equals("short")) {
            return "Short.parseShort( element.getChildText(\"" + fe.name;
        } else if (type.equals("float")) {
            return "Float.parseFloat( element.getChildText(\"" + fe.name;
        } else if (type.equals("String")) {
            return "element.getChildText(\"" + fe.name;
        } else if (type.equals("double")) {
            return "Double.parseDouble( element.getChildText(\"" + fe.name;
        } else if (type.equals("byte")) {
            return "Byte.parseByte( element.getChildText(\"" + fe.name;
        } else if (type.equals("char")) {
            return "(char)" + "Integer.parseInt( element.getChildText(\"" + fe.name;
        } else if (type.equals("boolean")) {
            return "Boolean.parseBoolean( element.getChildText(\"" + fe.name;
        } else if (type.equals("long")) {
            return "Long.parseLong( element.getChildText(\"" + fe.name;
        } else {
            return "???";
        }
    }


    /**
     * 通过文件名生成类名
     *
     * @param name 文件名
     * @return 类名
     */
    abstract protected String buildClassName(String name);

    protected void writeFile(){
        String path = D.SRC_DIR + D.CFG_DIR + packageName + "\\" + className + ".java";
        Util.writeFile(path, javaContent);
    }

}


