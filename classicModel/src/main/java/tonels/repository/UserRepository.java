package tonels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tonels.model.UserInfo;

import java.util.List;

public interface UserRepository extends JpaRepository<UserInfo, Long>, JpaSpecificationExecutor<UserInfo> {

    UserInfo findByUserCode(String userCode);

    List<UserInfo> findByDepartmentIdIn(List<Long> collect);

}
