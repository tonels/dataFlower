package model;

import lombok.Data;
import tonels.util.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DbVo1 {
    private String tableName;
    private String field;
    private String fieldName;
    private String type;
    private String falseIs;
    private String comment;

    private LocalDate ymdTime;
    private LocalDateTime ymdhmsTime;

    public DbVo1(TwoTType vo) {
        this.tableName = vo.getTableName();
        this.field = vo.getField();
        this.fieldName = vo.getFieldName();
        this.type = vo.getType();
        this.falseIs = vo.getFalseIs();
        this.comment = vo.getComment();
        this.ymdTime = DateUtils.asLocalDate(vo.getYmdTime());
        this.ymdhmsTime = DateUtils.asLocalDateTime(vo.getYmdhmsTime());
    }
}
