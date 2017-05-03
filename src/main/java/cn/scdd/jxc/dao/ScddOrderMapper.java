package cn.scdd.jxc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.scdd.jxc.entity.ScddOrder;
import cn.scdd.jxc.entity.ScddOrderExample;

@Repository("scddOrderMapper")
public interface ScddOrderMapper {
    int countByExample(ScddOrderExample example);

    int deleteByExample(ScddOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScddOrder record);

    int insertSelective(ScddOrder record);

    List<ScddOrder> selectByExample(ScddOrderExample example);

    ScddOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScddOrder record, @Param("example") ScddOrderExample example);

    int updateByExample(@Param("record") ScddOrder record, @Param("example") ScddOrderExample example);

    int updateByPrimaryKeySelective(ScddOrder record);

    int updateByPrimaryKey(ScddOrder record);
}