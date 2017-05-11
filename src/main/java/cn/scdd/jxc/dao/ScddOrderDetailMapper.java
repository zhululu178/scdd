package cn.scdd.jxc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.scdd.jxc.entity.ScddOrderDetail;
import cn.scdd.jxc.entity.ScddOrderDetailExample;

@Repository("scddOrderDetailMapper")
public interface ScddOrderDetailMapper {
    int countByExample(ScddOrderDetailExample example);

    int deleteByExample(ScddOrderDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScddOrderDetail record);

    int insertSelective(ScddOrderDetail record);

    List<ScddOrderDetail> selectByExample(ScddOrderDetailExample example);

    ScddOrderDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScddOrderDetail record, @Param("example") ScddOrderDetailExample example);

    int updateByExample(@Param("record") ScddOrderDetail record, @Param("example") ScddOrderDetailExample example);

    int updateByPrimaryKeySelective(ScddOrderDetail record);

    int updateByPrimaryKey(ScddOrderDetail record);
}