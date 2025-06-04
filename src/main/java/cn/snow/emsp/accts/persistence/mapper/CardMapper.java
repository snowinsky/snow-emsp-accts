package cn.snow.emsp.accts.persistence.mapper;

import cn.snow.emsp.accts.persistence.model.DbCard;
import org.apache.ibatis.annotations.Param;

public interface CardMapper {

    int insert(@Param("card") DbCard card);

    DbCard selectByCardId(@Param("cardId") Long cardId);

    int updateStatus(@Param("card") DbCard card, @Param("oldVersion") Integer oldVersion);

    int updateAccount(@Param("card") DbCard card, @Param("oldVersion") Integer oldVersion);
}
