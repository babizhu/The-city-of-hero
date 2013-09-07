package game.battle.auto;

import game.battle.Pet;
import game.battle.formation.ChooseFighters;
import game.battle.formation.IFormation;
import game.fighter.FighterBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.text.MessageFormat.format;


/**
 * 手机游戏阵营，前排4个，后排4个，攻击规则：
 * 1、对位攻击
 * 2、从左到右	攻击第一排
 * 3、从左到右	攻击第二排
 *
 * 备注：
 *
 * 		程序把left作为bottom处理，right当作top处理
 *
 *
 * @author Administrator
 * 2013-5-22 下午2:46:51
 */
@SuppressWarnings("JavaDoc")
public class Formation implements IFormation{

    /**
     * 阵型的总人数
     */
    public static final byte				TOTAL_COUNT = 8;
    private static final int 				COUNT_PER_ROW = 4;

    private List<FighterBase> 				fighters;
    private Pet								pet;

    /**
     * 按照位置从低到高进行排序，否则计算被攻击战士的时候可能出错
     */
    public static final Comparator<FighterBase> posComparator = new Comparator<FighterBase>(){
        @Override
        public int compare( FighterBase f1, FighterBase f2 ) {
            return f1.getPosition() - f2.getPosition();
        }
    };


    /**
     * 复制一份实例用于战斗，通常用于复制主线通关的mission的阵型，位于阵型的左边
     * @param formation
     * @return
     */
    public Formation( IFormation formation ){
        List<FighterBase> oldList = formation.getAllFighters();
        List<FighterBase> clonesList = new ArrayList<FighterBase>();
        for( FighterBase f : oldList ){
            FighterBase clone = new FighterBase( f );
            clonesList.add( clone );
        }

        fighters = clonesList;
        pet = formation.getPet();//TODO 需要克隆一份
    }

    /**
     * @param fightersList
     * @param isBottom
     * @param pet
     *
     *	如有必要，请在调用处对 fightersList 进行克隆处理
     */
    public Formation( List<FighterBase> fightersList, boolean isBottom, Pet pet ) throws IllegalArgumentException {
        if( fightersList == null || fightersList.size() == 0 ){
            throw new IllegalArgumentException(format("{0}战士列表为空或者数量为0", isBottom ? "攻方" : "守方"));
        }


        fighters = fightersList;
        if (!isBottom) {
            for( FighterBase bf : fighters ){
                formatForDefender( bf );
            }
            //由于存在镜面翻转，这里需要重新排序
        }
        Collections.sort( fighters, posComparator );
        this.pet = pet;
        //TODO有必要的话应该克隆

    }

    @Override
    public Pet getPet() {
        return pet;
    }


    /**
     * 对防守方进行一系列改造，包括<br>
     * 所有位置+8(手机战斗)<br>
     * 防守方镜面翻转<br>
     * 重新按照位置信息排序<br>
     * 这个代码就应该在这里执行，而不应该放到BaseBattle中，因为这个是和阵型密切相关的
     */
    private void formatForDefender( FighterBase fighter ){

        byte position = fighter.getPosition();
        position += TOTAL_COUNT;

        fighter.setPosition( position );//上下水平翻转
        fighter.setBottom(false);
    }
    @Override
    /**
     * 是否所有的战士都已经死亡
     * @return
     */
    public boolean isAllDie(){
        for( FighterBase f : fighters ){
            if( !f.isDie() ){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<FighterBase> getAllFighters() {
        List<FighterBase> ret = new ArrayList<FighterBase>();
        for( FighterBase f : fighters ){
            if( !f.isDie() ){
                ret.add( f );
            }
        }
        return ret;
    }

    @Override
    public FighterBase getBaseDefender( FighterBase attacker ) {

        int col = getCol( attacker.getPosition() );

        List<FighterBase> colList = getFightersByCol( col );
        if( !colList.isEmpty() ){
            return colList.get( 0 );//取前面一个
        }
        for( FighterBase f : fighters ){
            if( !f.isDie() ){
                return f;
            }
        }
        System.out.println( "未找到被攻击者");
        return null;
    }

    private List<FighterBase> getFightersByCol(int col) {
        List<FighterBase> ret = new ArrayList<FighterBase>();
        for( FighterBase f : fighters ){
            if(  !f.isDie() && getCol( f.getPosition() ) == col ){
                ret.add( f );
            }
        }
        return ret;
    }

    /**
     * 按行获取战士
     * @param   attacker    攻击者
     * @return
     */
    /**
     *
     * @param attacker
     * @return
     */
    private List<FighterBase> getFightersByRow( FighterBase attacker ) {
        FighterBase defender = getBaseDefender( attacker );
        int row = getRow( defender.getPosition() );
        return getFightersByRow( row );
    }

    /**
     * 按行获取战士
     * @param row
     * @return
     */
    private List<FighterBase> getFightersByRow( int row ) {
        List<FighterBase> ret = new ArrayList<FighterBase>();
        for( FighterBase f : fighters ){
            if( !f.isDie() && getRow( f.getPosition() ) == row ){
                ret.add( f );
            }
        }
        return ret;
    }

    /**
     * 获取自己
     * @param fighter
     * @return
     */
    private List<FighterBase> getFightersBySelf( FighterBase fighter ) {
        List<FighterBase> ret = new ArrayList<FighterBase>();
        ret.add( fighter );
        return ret;
    }

    /**
     * 技能攻击模式下，找出普通攻击下被攻击的战士对象
     * @param attacker
     * @return
     */
    private List<FighterBase> getFightersByNormal(FighterBase attacker) {
        List<FighterBase> ret = new ArrayList<FighterBase>();
        ret.add( getBaseDefender( attacker ) );
        return ret;
    }

    /**
     * 找出血量最少的战士
     * @return
     */
    private List<FighterBase> getFighterByMinHp() {
        List<FighterBase> ret = new ArrayList<FighterBase>();
        FighterBase minHp = null;
        for( FighterBase f : fighters ){
            if( f.getHp() > 0 ){
                if( minHp == null ){
                    minHp = f;
                }
                else if( f.getHp() < minHp.getHp() ){
                    minHp = f;
                }
            }
        }
        ret.add( minHp );
        return ret;
    }

    private int getRow(byte position) {
        int row = position / COUNT_PER_ROW;
        row = row < COUNT_PER_ROW ? row : row - COUNT_PER_ROW;
        return row;
    }

    private int getCol( byte position ) {
        return position % COUNT_PER_ROW;
    }

    @Override
    public List<FighterBase> getFighterOnEffect(FighterBase attacker, ChooseFighters type, List<FighterBase> all) {
        if( type == null ){
            return null;
        }
        switch( type ){
            case ROW:
                return getFightersByRow( attacker );
            case SELF:
                return getFightersBySelf( attacker );
            case MIN_HP:
                return getFighterByMinHp();
            case NORMAL_ATTACK:
                return getFightersByNormal( attacker );
            case ALL:
                return getAllFighters();
            case NEXT:
                return getFightByNext( attacker, all );
            default:
                break;
        }
        return null;
    }


    /**
     * 获取当前攻击者的下一个出手战士
     *
     * @param attacker      攻击者
     * @param all           按出手顺序排列的所有战士
     * @return              下一个出手的战士列表
     */
    private List<FighterBase> getFightByNext( FighterBase attacker, List<FighterBase> all ) {
        int pos = all.indexOf( attacker );

        List<FighterBase> ret = new ArrayList<FighterBase>();
        while( true ){
            pos++;
            FighterBase f = all.get( pos % all.size() );
            if( !f.isDie() && fighters.contains( f ) ){
                ret.add( f );
                return ret;
            }

        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder( "{" );
        for( FighterBase f : fighters ){
            sb.append( f.toSimpleString() );
            sb.append( "|" );
        }
        sb.append(" pet=").append(pet).append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        FighterBase f = new FighterBase();
        List<FighterBase> fighters = new ArrayList<FighterBase>();
        fighters.add( f );
        Formation ff = new Formation(fighters, false, null);
        System.out.println( ff.getFighterOnEffect( null, null, null ));
    }
}
