package cn.scdd.jxc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.scdd.jxc.entity.ScddGoods;
import cn.scdd.jxc.entity.ScddGoodsExample;

@Repository("scddGoodsMapper")
public interface ScddGoodsMapper {
    int countByExample(ScddGoodsExample example);

    int deleteByExample(ScddGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScddGoods record);

    int insertSelective(ScddGoods record);

    List<ScddGoods> selectByExample(ScddGoodsExample example);

    ScddGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScddGoods record, @Param("example") ScddGoodsExample example);

    int updateByExample(@Param("record") ScddGoods record, @Param("example") ScddGoodsExample example);

    int updateByPrimaryKeySelective(ScddGoods record);

    int updateByPrimaryKey(ScddGoods record);
}