package game.fighter;


import game.battle.auto.buff.BuffManager;
import game.battle.skill.SkillTemplet;

public class FighterBase{

	private String								name;
	private int									phyAttack;
	private int									phyDefend;
	private int	 								magicAttack;
	private int 								magicDefend;
	
	/**
	 * 所在阵型中的位置
	 */
	private byte								position;
	
	/**
	 * 最大血量
	 */
	private int									hpMax;
	
	/**
	 * 当前血量
	 */
	private int									hp;
	
	/**
	 * 是否允许出招
	 */
	private boolean 							isCanHit = true;
	
	/**
	 * 是否混乱，如果混乱，那么加血会加对方，而攻击会攻击己方，甚至有可能攻击自己，这里可能存在一些问题，最好和策划仔细讨论：
	 * 前端是否好做自己打自己的动画
	 * 
	 */
	private boolean								isChaos	= false;
	
	/*
	 * 是否处于战场的攻击方，手游中，下方通常是攻击方
	 */
	private  boolean							isBottom = true;
	
	private final BuffManager					buffManager = new BuffManager();

	private SkillTemplet 						skillTemplet;
	
	private int									dodge;
	
	private int									crit;
	private float								critMultiple;
	
	private int 								speed;

	
	public FighterBase() {
	}
	
	/**
	 * 拷贝构造函数
	 * @param fighter
	 */
	public FighterBase(FighterBase fighter) {
		this.setName(fighter.getName());
		this.position = fighter.getPosition();
		this.hp = this.hpMax = fighter.getHpMax();
		this.phyAttack = fighter.getPhyAttack();
		this.phyDefend = fighter.getPhyDefend();
		this.magicAttack = fighter.getMagicAttack();
		this.magicDefend = fighter.getMagicDefend();
		
		this.dodge = fighter.getDodge();
		this.crit = fighter.getCrit();
		this.critMultiple = fighter.getCritMultiple();
		this.setSpeed( fighter.getSpeed() );
		this.setBottom( fighter.isBottom() );
		
	}

	public SkillTemplet getSkillTemplet() {
		return skillTemplet;
	}

	public int getPhyAttack() {
		return phyAttack;
	}

	public void setPhyAttack(int phyAttack) {
		this.phyAttack = phyAttack;
	}

	public int getPhyDefend() {
		return phyDefend;
	}

	public void setPhyDefend(int phyDefend) {
		this.phyDefend = phyDefend;
	}

	public int getMagicAttack() {
		return magicAttack;
	}

	public void setMagicAttack(int magicAttack) {
		this.magicAttack = magicAttack;
	}

	public int getMagicDefend() {
		return magicDefend;
	}

	public void setMagicDefend(int magicDefend) {
		this.magicDefend = magicDefend;
	}

	public byte getPosition() {
		return position;
	}

	public void setPosition(byte position) {
		this.position = position;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public boolean isCanHit() {
		return isCanHit;
	}

	public void setCanHit( boolean isCanHit) {
		this.isCanHit = isCanHit;
	}

	public boolean isChaos() {
		return isChaos;
	}

	public void setChaos( boolean isChaos ) {
		this.isChaos = isChaos;
	}

	public boolean isBottom() {
		return isBottom;
	}

	public void setBottom(boolean isBottom) {
		this.isBottom = isBottom;
	}

	public BuffManager getBuffManager() {
		return buffManager;
	}

	public void setSkillTemplet(SkillTemplet skillTemplet) {
		this.skillTemplet = skillTemplet;
	}

	public boolean isDie() {
		return hp <= 0;
	}

	public BuffManager getBm() {
		return buffManager;
	}

	public boolean canSkill() {
		// TODO Auto-generated method stub
		return false;
	}

	public Object toSimpleString() {
		return "name=" + getName() + ", position=" + position;
	}

	public int getCrit() {
		return crit;
	}

	public void setCrit(int crit) {
		this.crit = crit;
	}

	public float getCritMultiple() {
		return critMultiple;
	}

	public void setCritMultiple(float critMultiple) {
		this.critMultiple = critMultiple;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public String toString() {
		return "FighterBase [phyAttack=" + phyAttack + ", phyDefend="
				+ phyDefend + ", magicAttack=" + magicAttack + ", magicDefend="
				+ magicDefend + ", position=" + position + ", hpMax=" + hpMax
				+ ", hp=" + hp + ", isCanHit=" + isCanHit + ", isChaos="
				+ isChaos + ", isBottom=" + isBottom + ", buffManager="
				+ buffManager + ", skillTemplet=" + skillTemplet + ", dodge="
				+ dodge + ", crit=" + crit + ", critMultple=" + critMultiple
				+ ", speed=" + speed + "]";
	}

	public String getName(){
		return name;
		
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
