package game.fighter;

import game.battle.auto.buff.BuffManager;
import game.battle.skill.SkillTemplet;
import game.fighter.cfg.FighterTempletBase;

public class FighterBase implements IFighter {

	private final FighterTempletBase			templet;
	
	
	
	/**
	 * 当前血量
	 */
	private int									hp;
	
	
	/**
	 * 是否允许出招
	 */
	private boolean 						isCanHit = true;
	
	/**
	 * 是否混乱，如果混乱，那么加血会加对方，而攻击会攻击己方，甚至有可能攻击自己，这里可能存在一些问题，最好和策划仔细讨论：
	 * 前端是否好做自己打自己的动画
	 * 
	 */
	private boolean								isChaos	= false;
	
	/*
	 * 是否处于战场的攻击方
	 */
	private final boolean						isAttack;
	
	private final BuffManager					buffManager;

		
	/**
	 * 拷贝构造函数，通常用于战斗前的准备工作
	 * @param f
	 */
	public FighterBase( FighterBase f ) {
		templet = f.getTemplet();		
//		position = f.getPosition();		
		hp = templet.getHpBase();
		isAttack = f.isAttack;
		buffManager = new BuffManager();
	}
	
	public int getSpeed(){
		return templet.getSpeed();
	}

//	public FighterBase() {
//	}

////	@Override
////	public ErrorCode dress(long oldPropId, long newPropId) {
////		
////		
////		IEquipment equipment = PropManager.getEquipmentById( newPropId );
////		if( equipment == null ){
////			return ErrorCode.PROP_NOT_FOUNTD;
////		}
////		
////		if( canDress( equipment ) == ErrorCode.SUCCESS ){
////			
////		}
////		
////		return ErrorCode.SUCCESS;
////	}
//	
//	/**
//	 * 测试是否可以装备此道具
//	 * @param equipment
//	 * @return
//	 */
//	private ErrorCode canDress( IEquipment equipment ){
//		EquipmentTemplet t = (EquipmentTemplet) equipment.getTemplet();
//		if( t.getRequiredLevel() > getLevel() ){
//			return ErrorCode.FIGHTER_LEVEL_NOT_ENOUGH;
//		}
//		
//		//其他检测
//		return ErrorCode.SUCCESS;
//	}

	

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getPhyAttack() {
		return templet.getPhyAttackBase();
	}


	public BuffManager getBm() {
		return buffManager;
	}
	
	/**
	 * 物防
	 * @return
	 */
	public int getPhyDefend() {
		return phyDefend;
	}

	/**
	 * 战士是否位于战场的攻击方，也就是下方
	 * @return
	 */
	public boolean isBottom() {
		return isAttack;
	}

	public void setBottom(boolean isBottom) {
		isAttack = isBottom;
	}

	public void setCanHit(boolean isCanHit) {
		this.isCanHit = isCanHit;
	}

	public boolean isCanHit() {
		return isCanHit;
	}

	public byte getPosition() {
		return position;
	}

	public void setPosition( byte position ) {
		this.position = position;
	}

	


	public void setDodge(int dodge) {
		this.dodge = dodge;
	}


	/**
	 * 闪避率
	 * @return
	 */
	public int getDodge() {
		return dodge;
	}

	

	/**
	 * 暴击
	 * @return
	 */
	public int getCrit() {
		return crit;
	}


	public void setPhyDefend(int phyDefend) {
		this.phyDefend = phyDefend;
	}

	public void setCrit(int crit) {
		this.crit = crit;
	}


	public boolean isDie() {
		return hp <= 0;
	}

	public SkillTemplet getSkillTemplet() {
		return skillTemplet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public boolean isChaos() {
		return isChaos;
	}

	/**
	 * @see #isChaos
	 * @param isHunluan
	 */
	public void setChaos(boolean isHunluan) {
		this.isChaos = isHunluan;
	}


	public String toSimpleString() {
		return "name=" + name + ", position=" + position;
		
	}

	public boolean CanSkill() {
		// TODO Auto-generated method stub
		return false;
	}

	public float getCritMultiple() {
		return critMultiple;
	}

	public FighterTempletBase getTemplet() {
		return templet;
	}


	
	
}
