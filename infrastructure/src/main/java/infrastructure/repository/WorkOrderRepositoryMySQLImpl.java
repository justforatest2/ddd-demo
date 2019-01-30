package infrastructure.repository;

import domain.module.workorder.model.WorkOrderModel;
import domain.module.workorder.repository.WorkOrderRepository;
import infrastructure.dao.ServiceWorkOrderModelMapper;
import infrastructure.dao.WorkOrderModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class WorkOrderRepositoryMySQLImpl implements WorkOrderRepository {
	@Autowired
	private WorkOrderModelMapper workOrderModelMapper;
	@Autowired
	private ServiceWorkOrderModelMapper serviceWorkOrderModelMapper;

	@Override
	public WorkOrderModel findById(Long id) {
		return workOrderModelMapper.findById(id);
	}

	// findFixedToDispatchByDate(日期范围)

	// findToDispatchByDate(日期范围，是否固定单)

	public void findFixedToDispatchByDate() {
//		result = workOrderModelMapper.findById();
//		result.stream().filter
	}

	public void findList() {
		// 固定单
		// 取消单
	}

	@Override
	public void save(WorkOrderModel workOrderModel) {
//		workOrderModelMapper.create(workOrderModel);
//		serviceWorkOrderModelMapper.create(workOrderModel.getServiceWorkOrder());
	}

	public void update(WorkOrderModel workOrderModel) {
//		workOrderModelMapper.update(workOrderModel);
//		serviceWorkOrderModelMapper.update(workOrderModel.getServiceWorkOrder());
	}
}
