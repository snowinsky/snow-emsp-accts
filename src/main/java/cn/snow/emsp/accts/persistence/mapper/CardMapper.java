package cn.snow.emsp.accts.persistence.mapper;

import cn.snow.emsp.accts.persistence.model.DbCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CardMapper {

    int insert(@Param("card") DbCard card);

    DbCard selectByCardId(@Param("cardId") Long cardId);

    List<DbCard> selectByAccountId(@Param("accountId") Long accountId);

    int updateCard(@Param("card") DbCard card, @Param("oldVersion") Integer oldVersion);

    DbCard selectByRfidVisibleNumber(@Param("rfidVisibleNumber") String rfidVisibleNumber);
}
