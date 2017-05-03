package cn.scdd.jxc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.scdd.jxc.entity.ScddUser;
import cn.scdd.jxc.entity.ScddUserExample;

@Repository("scddUserMapper")
public interface ScddUserMapper {
    int countByExample(ScddUserExample example);

    int deleteByExample(ScddUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScddUser record);

    int insertSelective(ScddUser record);

    List<ScddUser> selectByExample(ScddUserExample example);

    ScddUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScddUser record, @Param("example") ScddUserExample example);

    int updateByExample(@Param("record") ScddUser record, @Param("example") ScddUserExample example);

    int updateByPrimaryKeySelective(ScddUser record);

    int updateByPrimaryKey(ScddUser record);
    
    int checkUserExists(ScddUser record);
}