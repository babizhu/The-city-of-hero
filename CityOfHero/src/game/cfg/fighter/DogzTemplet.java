package game.cfg.fighter;

import org.jdom2.Element;

/**
 * 模版
 * @author liukun
 * 2013-11-8 18:30:29
 */
public class DogzTemplet {

    /**
	 * id
	 */
    private int id;

	/**
	 * id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * id
	 */
	public int getId() {
		return id;
	}
/**
	 * 名称
	 */
    private String name;

	/**
	 * 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 名称
	 */
	public String getName() {
		return name;
	}
/**
	 * 重量
	 */
    private int weight;

	/**
	 * 重量
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * 重量
	 */
	public int getWeight() {
		return weight;
	}
/**
	 * 攻击力
	 */
    private int attack;

	/**
	 * 攻击力
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}

	/**
	 * 攻击力
	 */
	public int getAttack() {
		return attack;
	}
/**
	 * 防御力
	 */
    private int defend;

	/**
	 * 防御力
	 */
	public void setDefend(int defend) {
		this.defend = defend;
	}

	/**
	 * 防御力
	 */
	public int getDefend() {
		return defend;
	}
/**
	 * 每升一级增加的攻击力
	 */
    private int attackStep;

	/**
	 * 每升一级增加的攻击力
	 */
	public void setAttackStep(int attackStep) {
		this.attackStep = attackStep;
	}

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
}
