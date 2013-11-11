package game.cfg.fighter;

import org.jdom2.Element;

/**
 * 模版
 * @author liukun
 * 2013-11-11 17:36:36
 */
public class DogzTemplet {

    /**
	 * id
	 */
    private final int id;



	/**
	 * id
	 */
	public int getId() {
		return id;
	}
/**
	 * 名称
	 */
    private final String name;



	/**
	 * 名称
	 */
	public String getName() {
		return name;
	}
/**
	 * 重量
	 */
    private final int weight;



	/**
	 * 重量
	 */
	public int getWeight() {
		return weight;
	}
/**
	 * 攻击力
	 */
    private final int attack;



	/**
	 * 攻击力
	 */
	public int getAttack() {
		return attack;
	}
/**
	 * 防御力
	 */
    private final int defend;



	/**
	 * 防御力
	 */
	public int getDefend() {
		return defend;
	}
/**
	 * 每升一级增加的攻击力
	 */
    private final int attackStep;



	/**
	 * 每升一级增加的攻击力
	 */
	public int getAttackStep() {
		return attackStep;
	}


	public DogzTemplet( Element element ) {
		id = Integer.parseInt( element.getChildText("id").trim() );
name = element.getChildText("name").trim();
weight = Integer.parseInt( element.getChildText("weight").trim() );
attack = Integer.parseInt( element.getChildText("attack").trim() );
defend = Integer.parseInt( element.getChildText("defend").trim() );
attackStep = Integer.parseInt( element.getChildText("attackStep").trim() );

	}

	@Override
	public String toString() {
		return "DogzTemplet [id = " + id + ",name = " + name + ",weight = " + weight + ",attack = " + attack + ",defend = " + defend + ",attackStep = " + attackStep + "]";
	}

	/*自定义代码开始*/
    private int skillId = 90;
    public int hp = 100;
	/*自定义代码结束*/
}
