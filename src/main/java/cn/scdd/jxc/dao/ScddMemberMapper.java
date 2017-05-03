package cn.scdd.jxc.dao;

import cn.scdd.jxc.entity.ScddMember;
import cn.scdd.jxc.entity.ScddMemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("scddMemberMapper")
public interface ScddMemberMapper {
    int countByExample(ScddMemberExample example);

    int deleteByExample(ScddMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScddMember record);

    int insertSelective(ScddMember record);

    List<ScddMember> selectByExample(ScddMemberExample example);

    ScddMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScddMember record, @Param("example") ScddMemberExample example);

    int updateByExample(@Param("record") ScddMember record, @Param("example") ScddMemberExample example);

    int updateByPrimaryKeySelective(ScddMember record);

    int updateByPrimaryKey(ScddMember record);
}