package nc.vo.pu.newoaryxx;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加累的描述信息
 * </p>
 *  创建日期:2016-11-30
 * @author yonyouBQ
 * @version NCPrj ??
 */
 
public class NewoaryxxHeadVO extends SuperVO {
	
/**
*时间戳
*/
public static final String TS="ts";;
    
    
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2016-11-30
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return (UFDateTime)this.getAttributeValue(NewoaryxxHeadVO.TS);
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2016-11-30
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.setAttributeValue(NewoaryxxHeadVO.TS,ts);
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("pu.newoaryxx");
    }
   }
    