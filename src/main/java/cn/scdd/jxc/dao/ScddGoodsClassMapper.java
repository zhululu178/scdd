package cn.scdd.jxc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.scdd.jxc.entity.ScddGoodsClass;
import cn.scdd.jxc.entity.ScddGoodsClassExample;

@Repository("scddGoodsClassMapper")
public interface ScddGoodsClassMapper {
    int countByExample(ScddGoodsClassExample example);

    int deleteByExample(ScddGoodsClassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScddGoodsClass record);

    int insertSelective(ScddGoodsClass record);

    List<ScddGoodsClass> selectByExample(ScddGoodsClassExample example);

    ScddGoodsClass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScddGoodsClass record, @Param("example") ScddGoodsClassExample example);

    int updateByExample(@Param("record") ScddGoodsClass record, @Param("example") ScddGoodsClassExample example);

    int updateByPrimaryKeySelective(ScddGoodsClass record);

    int updateByPrimaryKey(ScddGoodsClass record);
}