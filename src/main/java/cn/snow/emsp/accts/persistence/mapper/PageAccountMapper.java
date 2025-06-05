package cn.snow.emsp.accts.persistence.mapper;

import cn.snow.emsp.accts.persistence.model.DbAccountCard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PageAccountMapper {

    /**
     * 分页查询
     * 比如打算查第 3210页，每页 30 条数据
     * 那么 startRowNum = （3210-1） * 30 = 96270
     * pageSize = 30
     * @param startRowNum
     * @param pageSize
     * @return
     */
    List<DbAccountCard> selectPageAccountCard(@Param("dateTimeAfter") LocalDateTime dateTimeAfter, @Param("startRowNum") Integer startRowNum, @Param("pageSize") Integer pageSize);

    /**
     * 分页查询需要知道总行数
     * @param dateTimeAfter
     * @return
     */
    int countPageAccountCard(@Param("dateTimeAfter") LocalDateTime dateTimeAfter);
}
