package cn.snow.emsp.accts.persistence.mapper;

import cn.snow.emsp.accts.persistence.model.DbAccount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {
    int insert(@Param("account") DbAccount account);

    DbAccount selectByEmail(@Param("email") String email);

    int updateStatus(@Param("account") DbAccount account, @Param("oldVersion") Integer oldVersion);
}
