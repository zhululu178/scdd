package cn.scdd.jxc.service.supplier.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.scdd.jxc.dao.ScddSupplierMapper;
import cn.scdd.jxc.entity.ScddSupplier;
import cn.scdd.jxc.entity.ScddSupplierExample;
import cn.scdd.jxc.service.supplier.SupplierService;

@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	private ScddSupplierMapper scddSupplierMapper;
	
	public void saveSupplier(ScddSupplier supplier) {
		supplier.setModifyDate(new Date());
		if(supplier != null && supplier.getId() != null) {
			ScddSupplier supplierT = this.scddSupplierMapper.selectByPrimaryKey(supplier.getId());
			supplierT.setAddress(supplier.getAddress());
			supplierT.setQq(supplier.getQq());
			supplierT.setWx(supplier.getWx());
			supplierT.setPhone(supplier.getPhone());
			supplierT.setName(supplier.getName());
			supplierT.setContact(supplier.getContact());
			supplierT.setRemark(supplier.getRemark());
			this.scddSupplierMapper.updateByPrimaryKey(supplierT);
		} else {
			supplier.setCreateDate(supplier.getModifyDate());
			this.scddSupplierMapper.insert(supplier);	
		}
	}

	public List<ScddSupplier> searchBySupplier(ScddSupplier supplier) {
		ScddSupplierExample example = new ScddSupplierExample();
		if(supplier != null) {
			example.setOrderByClause("modify_date desc");
			ScddSupplierExample.Criteria criteria = example.createCriteria();
			if(StringUtils.isNotBlank(supplier.getName())) {
				criteria.andNameLike("%" + supplier.getName() + "%");
			}
			if(StringUtils.isNotBlank(supplier.getPhone())) {
				criteria.andPhoneLike("%" + supplier.getPhone() + "%");
			}
		}
		return this.scddSupplierMapper.selectByExample(example);
	}

	public ScddSupplier searchSupplierById(int id) {
		return this.scddSupplierMapper.selectByPrimaryKey(id);
	}

	public boolean checkSupplierExists(ScddSupplier record) {
		// TODO Auto-generated method stub
		return false;
	}
}
