package game.cfg.fighter;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模版配置
 * @author liukun
 * 2013-11-8 18:30:29
 */
public class DogzTempletCfg {
	private static final Map<Integer,DogzTemplet> dogzTemplets = new HashMap<Integer, DogzTemplet>();


	static{
		init();

	}
	private static final String FILE = "./CityOfHero/resource/fighter/dogz.xml";



	private static void init(){

		SAXBuilder builder = new SAXBuilder();
		Document document;
		try {
			document = builder.build( FILE );
			Element root = document.getRootElement();
			List<?> list = root.getChildren( "npc" );

			for (Object o : list) {
				DogzTemplet templet = new DogzTemplet( (Element) o );
				DogzTemplet temp = dogzTemplets.put( templet.getId(), templet );
				if( temp != null ){
					throw new RuntimeException( "DogzTemplet" + temp.getId() + "重复了" );
				}

			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println( "DogzTemplet 配置文件解析完毕" );
	}


	/**
	 * 通过id获取DogzTemplet的引用
	 * @param   templetId   id
	 * @return  返回一个引用
	 */
	public static DogzTemplet getDogzTempletById( int templetId ){
		return dogzTemplets.get( templetId );
	}
	public static void main(String[] args) {

		int id = 101101;
		System.out.println( getDogzTempletById( 1 ) );
	}

}
