package domain.module.workorder.service;

import domain.module.workorder.model.WorkOrderModel;
import domain.module.workorder.repository.WorkOrderRepository;

public class DispatchService {
	private WorkOrderRepository workOrderRepository;

	public DispatchService(WorkOrderRepository workOrderRepository) {
		this.workOrderRepository = workOrderRepository;
	}

	public void dispatch(Long serviceWorkOrderId, Long staffId) {
		// 通过仓储获取领域模型
		// 调用领域模型进行操作
		// 持久化保存数据

		WorkOrderModel workOrderModel = workOrderRepository.findByServiceWorkOrderId(serviceWorkOrderId);
		workOrderModel.dispatch(staffId);
		workOrderRepository.update(workOrderModel);

		// comboPlanOrderRepository.findById();
		// comboPlanOrder.xxx();
		// comboPlanOrderRepository.update();

		// comboPointRecordRepository.findById();
		// comboPointRecord.xxx();
		// comboPointRecordRepository.update();
	}
}
