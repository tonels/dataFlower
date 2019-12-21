package common.model;

import common.utils.excel.annotation.ExcelField;
import lombok.Data;

import java.io.Serializable;

@Data
public class PcPrdInfoChargeConfigImportVo implements Serializable {

    private static final long serialVersionUID = 588661424529430602L;
    /**
     * <b>Fields</b> id : 主键ID
     */
    private Long id;

    /**
     * <b>Fields</b> prdInfoId : 商品包ID
     */
    @ExcelField(title = "商品包ID", align = 2, sort = 1)
    private Long prdInfoId;

    /**
     * <b>Fields</b> chargeType : 计费类型 1:基本计费 2:促销计费
     */
    @ExcelField(title = "计费类型", align = 2, sort = 2, dictType = "pc_prd_info_charge_config_charge_type")
    private Integer chargeType;

    /**
     * <b>Fields</b> chargePointId : 计费ID
     */
    @ExcelField(title = "计费ID", align = 2, sort = 3)
    private Long chargePointId;

    /**
     * <b>Fields</b> equityId : 权益节点id
     */
    @ExcelField(title = "权益节点", align = 2, sort = 4)
    private Long equityId;

    /**
     * <b>Fields</b> channelPackageId : 渠道包ID
     */
    @ExcelField(title = "渠道包ID", align = 2, sort = 5)
    private Long channelPackageId;

}