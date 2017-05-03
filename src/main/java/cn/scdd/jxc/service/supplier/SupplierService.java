package cn.scdd.jxc.service.supplier;

import java.util.List;

import cn.scdd.jxc.entity.ScddSupplier;

public interface SupplierService {
	public void saveSupplier(ScddSupplier supplier);
	
	public List<ScddSupplier> searchBySupplier(ScddSupplier supplier);
	
	public ScddSupplier searchSupplierById(int id);
	
	public boolean checkSupplierExists(ScddSupplier record);
}
