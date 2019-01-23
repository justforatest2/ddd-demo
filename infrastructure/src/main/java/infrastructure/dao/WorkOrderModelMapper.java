package infrastructure.dao;


import domain.module.workorder.model.WorkOrderModel;

public interface WorkOrderModelMapper {
    WorkOrderModel findById(Long id);
}
