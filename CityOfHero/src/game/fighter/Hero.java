package game.fighter;

import game.ITransformStream;
import game.fighter.cfg.HeroTemplet;
import game.partner.PartnerBase;
import game.prop.ICalculateAddtion;
import game.prop.equipment.EquipmentBase;
import game.prop.equipment.EquipmentType;
import game.prop.templet.EquipmentTemplet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import user.UserInfo;
import util.ErrorCode;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * 英雄卡牌，类似于玩家的伙伴，以前的PartnerBase类在适当的时候会被废除掉
 * @author Administrator
 * 2013-5-28 下午5:05:04
 */
public class Hero extends FighterBase implements ICalculateAddtion, ITransformStream {
	
	private final HeroTemplet					templet;
	
	/**
	 * 英雄唯一id
	 */
	private long								id;
	
	/**
	 * 战士等级
	 */
	private short 								level;
	
	private Map<EquipmentType,EquipmentBase>	equipments = new HashMap<EquipmentType, EquipmentBase>();
	
	private final static Logger 				logger = LoggerFactory.getLogger(PartnerBase.class);

	/**
	 * 通常用于从数据库初始化一个英雄
	 * @param heroTemplet
	 * @param level
	 * @param position
	 */
	public Hero( HeroTemplet templet, short level, byte position ) {
		this.templet = templet;
		this.level = level;
		setPosition( position );
		init();
	}
	
	/**
	 * 拷贝构造函数
	 * @param heroTemplet
	 * @param level
	 * @param position
	*/
	public Hero( Hero fighter ) {
		super( fighter );
		this.templet = fighter.templet;
		this.level = fighter.level;
	}
	 
	/**
	 * 通过模板和战士等级全面初始化各项基础属性
	 */
	private void init() {
		setHpMax(templet.hpBase + level * templet.stepHp);
		setHp( getHpMax() );
		setPhyAttack( templet.phyAttackBase + level * templet.stepPhyAttack );
		setPhyDefend( templet.phyDefendBase + level * templet.stepPhyDefend );
		setMagicAttack( templet.magicAttackBase + level * templet.stepMagicAttack );
		setMagicDefend( templet.magicDefendBase + level * templet.stepMagicDefend );
		
		setDodge( templet.dodge );
		setCrit( templet.crit );
		setCritMultiple( templet.critMultiple );
		setSpeed( templet.speed );
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}
	
//	public HeroTemplet getTemplet(){
//		return templet;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void buildTransformStream(ByteBuffer buf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calcAddtion() {
		init();
		//TODO 道具加成
		
	}
	
	/**
	 * 获取用逗号分割的装备id字符串用于数据库存储
	 * @return
	 */
	public String getEquipmentStr() {
		if( equipments.size() == 0 ){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for( EquipmentBase e : equipments.values() ){
			sb.append( e.getId() );
			sb.append( "," );
		}
		return sb.substring( 0, sb.length() - 1 );
	}

	public void setEquipmentFromStr( String str, UserInfo user ) {
		String[] estr = str.split( "," );
		for( String s : estr ){
			long id = Long.parseLong( s );
			EquipmentBase equipment = user.getPropManager().getEquipmentById( id );
			if( equipment != null ){
				equipment.setInBag( false );
				equipments.put( ((EquipmentTemplet) equipment.getTemplet()).getEquipmentType(), equipment );
			}
			else{
				logger.error( "玩家" + user.getName() + "道具" + id + "不存在！" );
			}
		}		
	}
	
	/**
	 * 如果存在旧装备，那么应该把此装备放入背包，这样的话如何与客户端通信呢，解决方法一般为：
	 * 1、主动刷新整个背包	：浪费资源
	 * 2、告知背包某个物品进入了背包：实现难度太高
	 * 
	 * @param newEquipment
	 * @return
	 */
	public ErrorCode dress( EquipmentBase newEquipment ){
		EquipmentTemplet t = (EquipmentTemplet) newEquipment.getTemplet();
		if( getLevel() < t.getRequiredLevel() ){
			return ErrorCode.PARTNER_LEVEL_NOT_ENOUGH;
		}
		
		EquipmentBase old = equipments.get( t.getEquipmentType() );
		if( old != null ){
			old.setInBag( true );			
		}
		
		equipments.put( t.getEquipmentType(), newEquipment );
		calcAddtion();
		return ErrorCode.SUCCESS;
	}

}
