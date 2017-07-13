package cn.scdd.jxc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.scdd.jxc.entity.ScddPayment;
import cn.scdd.jxc.entity.ScddPaymentExample;
import cn.scdd.jxc.entity.ScddPaymentSearchPage;

@Repository("scddPaymentMapper")
public interface ScddPaymentMapper {
    int countByExample(ScddPaymentExample example);

    int deleteByExample(ScddPaymentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScddPayment record);

    int insertSelective(ScddPayment record);

    List<ScddPayment> selectByExample(ScddPaymentExample example);

    ScddPayment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScddPayment record, @Param("example") ScddPaymentExample example);

    int updateByExample(@Param("record") ScddPayment record, @Param("example") ScddPaymentExample example);

    int updateByPrimaryKeySelective(ScddPayment record);

    int updateByPrimaryKey(ScddPayment record);
    
    List<ScddPaymentSearchPage> selectByPayment(ScddPaymentSearchPage payment);
    
    ScddPaymentSearchPage selectByPaymentSum(ScddPaymentSearchPage payment);
    
}