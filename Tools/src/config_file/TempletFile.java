package config_file;

import util.Util;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-5
 * Time: 下午5:30
 * To change this template use File | Settings | File Templates.
 */
public class TempletFile {
    private final String PATH = "D:\\work\\The-city-of-hero\\Tools\\source_templet\\";
    private final String templetPath;

    public TempletFile(String templetPath) {
        this.templetPath = PATH + templetPath;
    }

    public String getTempletStr() {

        return Util.readFile(templetPath);
    }

    public static void main(String[] args) {
        String file = "xTemplet.txt";
        TempletFile t = new TempletFile(file);
        System.out.println(t.getTempletStr());

        String a = "fsafa#adfds";
        a = a.replace("#a", "xxxxx");
        System.out.println(a);
    }

}
