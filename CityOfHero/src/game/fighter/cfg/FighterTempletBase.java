
package game.fighter.cfg;

import org.jdom2.Element;

import game.battle.skill.SkillTemplet;
import game.battle.skill.cfg.SkillTempletCfg;

/**
 * 战士模版
 * @author Administrator
 * 2013-5-24 上午10:49:26
 */
public class FighterTempletBase {
	
	public final int							templetId;
	/**
	 * 名字
	 */
	public final String							name;
	
	/**
	 * 描述
	 */
	public final String							desc;
	
	/**
	 * 基础血量
	 */
	public final int							hpBase;
		
	/**
	 * 速度
	 */
	public final int							speed;
	
	/**
	 * 基础物攻
	 */
	public final int							phyAttackBase;
		
	
	/**
	 * 基础物防
	 */
	public final int							phyDefendBase;
	
		
	/**
	 * 基础魔攻
	 */
	public final 	int							magicAttackBase;
	
	
	
	/**
	 * 基础魔防
	 */
	public final 	int							magicDefendBase;
		
	
	/**
	 * 闪避
	 */
	public final 	int							dodge;	
	
	/**
	 * 暴击
	 */	
	public final 	int							crit;
	
	/**
	 * 暴击倍数
	 */	
	public final 	float						critMultiple;
	
	/**
	 * 技能模板
	 */
	public final SkillTemplet					skillTemplet;
	
	public FighterTempletBase( Element element ) {
		templetId = Integer.parseInt( element.getChildText( "id" ) ) ;
		name = element.getChildText( "name" );
		desc = element.getChildText( "desc" );
		hpBase = Integer.parseInt( element.getChildText( "hpBase" ) );
		
		speed = Integer.parseInt( element.getChildText( "speed" ) );  
		
		phyAttackBase = Integer.parseInt( element.getChildText( "phyAttackBase" ) );
		phyDefendBase = Integer.parseInt( element.getChildText( "phyDefendBase" ) );
		magicAttackBase = Integer.parseInt( element.getChildText( "magicAttackBase" ) );  
		magicDefendBase = Integer.parseInt( element.getChildText( "magicDefendBase" ) );

		dodge = Integer.parseInt( element.getChildText( "dodge" ) );
		crit = Integer.parseInt( element.getChildText( "crit" ) ); 
		critMultiple = Float.parseFloat( element.getChildText( "critMultiple" ) ); 

		skillTemplet = SkillTempletCfg.getSkillTempletById( Integer.parseInt( element.getChildText( "skillTempletId" ) ) );
	}

	@Override
	public String toString() {
		return "FighterTempletBase [templetId=" + templetId + ", name=" + name
				+ ", desc=" + desc + ", hpBase=" + hpBase + ", speed=" + speed
				+ ", phyAttackBase=" + phyAttackBase + ", phyDefendBase="
				+ phyDefendBase + ", magicAttackBase=" + magicAttackBase
				+ ", magicDefendBase=" + magicDefendBase + ", dodge=" + dodge
				+ ", crit=" + crit + ", critMultiple=" + critMultiple
				+ ", skillTemplet=" + skillTemplet + "]";
	}
	
	
	
}
