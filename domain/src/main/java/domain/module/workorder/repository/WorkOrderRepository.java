package domain.module.workorder.repository;

import domain.module.workorder.model.WorkOrderModel;

public interface WorkOrderRepository {
	WorkOrderModel findById(Long id);

	void save(WorkOrderModel workOrderModel);
}
