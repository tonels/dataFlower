package common.vo;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

public class PrdContKafkaVo implements Serializable {

    /**
     * <b>Fields</b> serialVersionUID :
     */
    private static final long serialVersionUID = 2136933672390642270L;

    /**
     * 节目ID
     */
    @NotNull(message = "发送kafka的节目编码不能为空")
    private Long prdContId;

    /**
     * 操作类型     1 - 发布   2 - 撤回   3 - 撤回+删除   4 - 自动发布
     * <p>
     * 播控强制下线： 和撤回不一样的地方就是需要传播控状态字段
     */
    @NotNull(message = "发送kafka的节目操作类型不能为空")
    private Integer operate;


    /**
     * 节目上线下操作人(发布新操作人)
     */
    private String operator;

    /**
     * 类构造方法
     * <p><b>Description:</b> 有参 </p>
     */
    public PrdContKafkaVo(@NotNull(message = "发送kafka的节目编码不能为空") Long prdContId,
                          @NotNull(message = "发送kafka的节目操作类型不能为空") Integer operate, String operator) {
        super();
        this.prdContId = prdContId;
        this.operate = operate;
        this.operator = operator;
    }

    /**
     * 类构造方法
     * <p><b>Description:</b> 无参 </p>
     */
    public PrdContKafkaVo() {
        super();
    }


    public Long getPrdContId() {
        return prdContId;
    }


    public void setPrdContId(Long prdContId) {
        this.prdContId = prdContId;
    }


    public Integer getOperate() {
        return operate;
    }


    public void setOperate(Integer operate) {
        this.operate = operate;
    }


    public String getOperator() {
        return operator;
    }


    public void setOperator(String operator) {
        this.operator = operator;
    }
}
