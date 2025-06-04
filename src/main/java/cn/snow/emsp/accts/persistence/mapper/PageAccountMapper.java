package cn.snow.emsp.accts.persistence.mapper;

import cn.snow.emsp.accts.persistence.model.DbAccountCard;
import org.springframework.stereotype.Repository;

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
    List<DbAccountCard> selectPageAccountCard(Integer startRowNum, Integer pageSize);
}
