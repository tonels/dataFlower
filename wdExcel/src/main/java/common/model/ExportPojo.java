package common.model;

import common.utils.excel.annotation.ExcelField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ExportPojo {

    @ExcelField(title = "商品包ID", align = 2, sort = 1)
    private Long prdInfoId;

    /**
     * <b>Fields</b> prdInfoId : 商品包名称
     */
    @ExcelField(title = "商品包名称", align = 2, sort = 2)
    private String prdInfoName;

    /**
     * <b>Fields</b> chargeType : 计费类型 1:基本计费 2:促销计费
     */
    @ExcelField(title = "计费方式", align = 2, sort = 3, dictType = "pc_prd_info_charge_config_charge_type")
    private Integer chargeType;


}