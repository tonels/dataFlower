package common.model;

import common.utils.GroupType;
import common.utils.excel.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p><b>PcPrdInfoChargeConfigVo Description:</b> PcPrdInfoChargeConfigExportVo类，商品包基本计费导出类Vo</p>
 *
 * @author anyouz
 * <p><b>DATE</b> 2019-08-12 11:20:52</p>
 */
@Data
public class PcPrdInfoChargeConfigExportVo implements Serializable {

    private static final long serialVersionUID = 5064621016354299254L;
    /**
     * <b>Fields</b> id : 主键ID
     */
//    @NotNull(message = "id不能为空", groups = {GroupType.Update.class}) //修改
    @NotNull(message = "ID不能为空", groups = {GroupType.Update.class})
    private Long id;

    /**
     * <b>Fields</b> prdInfoId : 商品包ID
     */
//    @NotNull(message = "商品包ID不能为空", groups = {Default.class})
    @ExcelField(title = "商品包ID", align = 2, sort = 1)
    private Long prdInfoId;

    /**
     * <b>Fields</b> prdInfoName : 商品包名称
     */
    @ExcelField(title = "商品包名称", align = 2, sort = 2)
    private String prdInfoName;

    /**
     * <b>Fields</b> chargeType : 计费类型 1:基本计费 4:权益节点
     */
    @ExcelField(title = "计费方式", align = 2, sort = 3)
    private Integer chargeType;

    /**
     * <b>Fields</b> price : 价格
     */
//    @NotBlank(message = "价格不能为空", groups = {Default.class})
//    @Length(message = "价格长度不能超过512个字节", max = 512, groups = {Default.class, GroupType.Update.class})
    @ExcelField(title = "售价", align = 2, sort = 4)
    private String price;

    /**
     * <b>Fields</b> chargePointId : 计费ID 可多选
     */
//    @NotBlank(message = "计费ID 可多选不能为空", groups = {Default.class})
//    @Length(message = "计费ID 可多选长度不能超过512个字节", max = 512, groups = {Default.class, GroupType.Update.class})
    @ExcelField(title = "计费ID", align = 2, sort = 5)
    private String chargePointId;

    /**
     * <b>Fields</b> sellingPriceName : 售价名称
     */
//    @NotBlank(message = "售价名称不能为空", groups = {Default.class})
//    @Length(message = "售价名称长度不能超过32个字节", max = 32, groups = {Default.class, GroupType.Update.class})
    @ExcelField(title = "计费名称", align = 2, sort = 6)
    private String sellingPriceName;

    /**
     * <b>Fields</b> equityId : 权益节点id 可多选
     */
//    @NotBlank(message = "权益节点id 可多选不能为空", groups = {Default.class})
//    @Length(message = "权益节点id 可多选长度不能超过512个字节", max = 512, groups = {Default.class, GroupType.Update.class})
    @ExcelField(title = "权益节点id", align = 2, sort = 7)
    private String equityId;

    /**
     * <b>Fields</b> equityCode : 权益节点编码
     */
    @ExcelField(title = "权益节点编码", align = 2, sort = 8)
    private String equityCode;

    /**
     * <b>Fields</b> groupName : 产品组名称
     */
    @ExcelField(title = "产品组名称", align = 2, sort = 9)
    private String groupName;

    /**
     * <b>Fields</b> channelPackageId : 渠道包 可多选
     */
//    @NotBlank(message = "渠道包 可多选不能为空", groups = {Default.class})
//    @Length(message = "渠道包 可多选长度不能超过512个字节", max = 512, groups = {Default.class, GroupType.Update.class})
    @ExcelField(title = "渠道包ID", align = 2, sort = 10)
    private String channelPackageId;

}