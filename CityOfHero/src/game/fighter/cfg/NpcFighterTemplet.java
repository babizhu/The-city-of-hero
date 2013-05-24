package game.fighter.cfg;

import org.jdom2.Element;

import game.battle.skill.SkillTemplet;
import game.battle.skill.cfg.SkillTempletCfg;

/**
 * 战士模版
 * @author Administrator
 * 2013-5-24 上午10:49:26
 */
public class NpcFighterTemplet {
	
	private final short							templetId;
	/**
	 * 名字
	 */
	private final String						name;
	
	/**
	 * 描述
	 */
	private final String						desc;
	
	/**
	 * 基础血量
	 */
	private final int							hpBase;
	
	/**
	 * 血量成长值
	 */
	private final int							stepHp;
	
	/**
	 * 速度
	 */
	private final int							speed;
	
	/**
	 * 基础物攻
	 */
	private final int							phyAttackBase;
	
	/**
	 * 物攻成长值
	 */
	private final int							stepPhyAttack;
	
	/**
	 * 基础物防
	 */
	private final int							phyDefendBase;
	
	/**
	 * 物防成长值
	 */
	private final int							stepPhyDefend;	
	
	
	/**
	 * 基础魔攻
	 */
	private final 	int							magicAttackBase;
	
	/**
	 * 魔攻成长值
	 */
	private final 	int							stepMagicAttack;
	
	/**
	 * 基础魔防
	 */
	private final 	int							magicDefendBase;
	
	/**
	 * 魔防成长值
	 */
	private final 	int							stepMagicDefend;	
	
	/**
	 * 闪避
	 */
	private final 	int							dodge;	
	
	/**
	 * 暴击
	 */	
	private final 	int							crit;
	
	/**
	 * 暴击倍数
	 */	
	private final 	float						critMultiple;
	
	/**
	 * 技能模板
	 */
	SkillTemplet								skillTemplet;
	
	public NpcFighterTemplet( Element element ) {
		templetId = Short.parseShort( element.getChildText( "id" ) ) ;
		name = element.getChildText( "name" );
		desc = element.getChildText( "desc" );
		hpBase = Integer.parseInt( element.getChildText( "hpBase" ) );
		stepHp = Integer.parseInt( element.getChildText( "stepHp" ) );    
		speed = Integer.parseInt( element.getChildText( "speed" ) );  
		
		phyAttackBase = Integer.parseInt( element.getChildText( "phyAttackBase" ) );
		stepPhyAttack = Integer.parseInt( element.getChildText( "stepPhyAttack" ) );
		phyDefendBase = Integer.parseInt( element.getChildText( "phyDefendBase" ) );
		stepPhyDefend = Integer.parseInt( element.getChildText( "stepPhyDefend" ) );
		magicAttackBase = Integer.parseInt( element.getChildText( "magicAttackBase" ) );  
		stepMagicAttack = Integer.parseInt( element.getChildText( "stepMagicAttack" ) );
		magicDefendBase = Integer.parseInt( element.getChildText( "magicDefendBase" ) );
		stepMagicDefend = Integer.parseInt( element.getChildText( "stepMagicDefend" ) );  

		dodge = Integer.parseInt( element.getChildText( "dodge" ) );
		crit = Integer.parseInt( element.getChildText( "crit" ) ); 
		critMultiple = Float.parseFloat( element.getChildText( "critMultiple" ) ); 

		skillTemplet = SkillTempletCfg.getSkillTempletById( Byte.parseByte( element.getChildText( "skillTempletId" ) ) );
	}

	@Override
	public String toString() {
		return "FighterTemplet [templetId=" + templetId + ", name=" + name
				+ ", desc=" + desc + ", hpBase=" + hpBase + ", stepHp="
				+ stepHp + ", speed=" + speed + ", phyAttackBase="
				+ phyAttackBase + ", stepPhyAttack=" + stepPhyAttack
				+ ", phyDefendBase=" + phyDefendBase + ", stepPhyDefend="
				+ stepPhyDefend + ", magicAttackBase=" + magicAttackBase
				+ ", stepMagicAttack=" + stepMagicAttack + ", magicDefendBase="
				+ magicDefendBase + ", stepMagicDefend=" + stepMagicDefend
				+ ", dodge=" + dodge + ", crit=" + crit + ", critMultiple="
				+ critMultiple + ", skillTemplet=" + skillTemplet + "]";
	}

	public short getTempletId() {
		return templetId;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public int getHpBase() {
		return hpBase;
	}

	public int getStepHp() {
		return stepHp;
	}

	public int getSpeed() {
		return speed;
	}

	public int getPhyAttackBase() {
		return phyAttackBase;
	}

	public int getStepPhyAttack() {
		return stepPhyAttack;
	}

	public int getPhyDefendBase() {
		return phyDefendBase;
	}

	public int getStepPhyDefend() {
		return stepPhyDefend;
	}

	public int getMagicAttackBase() {
		return magicAttackBase;
	}

	public int getStepMagicAttack() {
		return stepMagicAttack;
	}

	public int getMagicDefendBase() {
		return magicDefendBase;
	}

	public int getStepMagicDefend() {
		return stepMagicDefend;
	}

	public int getDodge() {
		return dodge;
	}

	public int getCrit() {
		return crit;
	}

	public float getCritMultiple() {
		return critMultiple;
	}

	public SkillTemplet getSkillTemplet() {
		return skillTemplet;
	}
	
	
	
}
