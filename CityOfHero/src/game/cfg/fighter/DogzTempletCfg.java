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
 * 2013-11-12 17:51:49
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
			List<?> list = root.getChildren( "dogz" );

			for (Object o : list) {
				DogzTemplet templet = new DogzTemplet( (Element) o );
				DogzTemplet temp = dogzTemplets.put( templet.getId(), templet );
				if( temp != null ){
					throw new RuntimeException( "DogzTemplet id [" + temp.getId() + "] 重复了" );
				}

			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println( "DogzTemplet xml配置文件解析完毕" );
	}


	/**
	 * 通过id获取DogzTemplet的引用
	 * @param   templetId   id
	 * @return  返回一个引用
	 */
	public static DogzTemplet getDogzTempletById( int templetId ){
		return dogzTemplets.get( templetId );
	}

	/*自定义代码开始*//*自定义代码结束*/

	public static void main(String[] args) {

		int id = 1;
		System.out.println( getDogzTempletById( id ) );
	}
}
