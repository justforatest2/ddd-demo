package infrastructure.dao;


import domain.module.workorder.model.WorkOrderModel;

public interface WorkOrderModelMapper {
    WorkOrderModel findById(Long id);

    WorkOrderModel findByServiceWorkOrderId(Long serviceWorkOrderId);

    WorkOrderModel findByServiceWorkOrderIdAndType(Long serviceWorkOrderId, Integer type);

    void update(WorkOrderModel workOrderModel);
}
