package tonels.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tonels.excel.UserExport;
import tonels.model.UserInfo;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd")
    UserExport userToExport(UserInfo info);

}

