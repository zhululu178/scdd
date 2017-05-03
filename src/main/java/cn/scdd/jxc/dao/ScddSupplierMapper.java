package cn.scdd.jxc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.scdd.jxc.entity.ScddSupplier;
import cn.scdd.jxc.entity.ScddSupplierExample;

@Repository("scddSupplierMapper")
public interface ScddSupplierMapper {
    int countByExample(ScddSupplierExample example);

    int deleteByExample(ScddSupplierExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScddSupplier record);

    int insertSelective(ScddSupplier record);

    List<ScddSupplier> selectByExample(ScddSupplierExample example);

    ScddSupplier selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScddSupplier record, @Param("example") ScddSupplierExample example);

    int updateByExample(@Param("record") ScddSupplier record, @Param("example") ScddSupplierExample example);

    int updateByPrimaryKeySelective(ScddSupplier record);

    int updateByPrimaryKey(ScddSupplier record);
}