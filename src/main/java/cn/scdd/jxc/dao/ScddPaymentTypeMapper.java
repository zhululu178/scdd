package cn.scdd.jxc.dao;

import cn.scdd.jxc.entity.ScddPaymentType;
import cn.scdd.jxc.entity.ScddPaymentTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("scddPaymentTypeMapper")
public interface ScddPaymentTypeMapper {
    int countByExample(ScddPaymentTypeExample example);

    int deleteByExample(ScddPaymentTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScddPaymentType record);

    int insertSelective(ScddPaymentType record);

    List<ScddPaymentType> selectByExample(ScddPaymentTypeExample example);

    ScddPaymentType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScddPaymentType record, @Param("example") ScddPaymentTypeExample example);

    int updateByExample(@Param("record") ScddPaymentType record, @Param("example") ScddPaymentTypeExample example);

    int updateByPrimaryKeySelective(ScddPaymentType record);

    int updateByPrimaryKey(ScddPaymentType record);
}