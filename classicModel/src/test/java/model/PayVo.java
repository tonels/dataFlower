package model;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class PayVo {
    //    支付方式
    private String payType;
    //    支付方式金额
    private Long amount;

    private Integer id;

    private String product_code;
}