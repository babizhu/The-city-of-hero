package game.fighter.cfg;

import org.jdom2.Element;

public class HeroTemplet extends FighterTempletBase {

	/**
	 * 血量成长值
	 */
	public final int							stepHp;
	
	/**
	 * 物攻成长值
	 */
	public final int							stepPhyAttack;
	
	/**
	 * 物防成长值
	 */
	public final int							stepPhyDefend;
	
	/**
	 * 魔攻成长值
	 */
	public final int							stepMagicAttack;
	
	/**
	 * 魔防成长值
	 */
	public final int							stepMagicDefend;
	
	public HeroTemplet(Element element) {
		super(element);
		stepPhyAttack = Integer.parseInt( element.getChildText( "stepPhyAttack" ) );
		stepPhyDefend = Integer.parseInt( element.getChildText( "stepPhyDefend" ) );
		stepMagicAttack = Integer.parseInt( element.getChildText( "stepMagicAttack" ) );
		stepMagicDefend = Integer.parseInt( element.getChildText( "stepMagicDefend" ) );  
		stepHp = Integer.parseInt( element.getChildText( "stepHp" ) );    
	}
	
//	public  Hero createHero(){
//		Hero h = new Hero( this );
//		return h;
//	}
	
//	public int getStepPhyAttack() {
//		return stepPhyAttack;
//	}
//
//	public int getStepPhyDefend() {
//		return stepPhyDefend;
//	}
//
//	public int getStepMagicAttack() {
//		return stepMagicAttack;
//	}
//	
//	public int getStepMagicDefend() {
//		return stepMagicDefend;
//	}
//	
//	public int getStepHp() {
//		return stepHp;
//	}
	

}
