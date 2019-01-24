package infrastructure.dao;


import domain.module.workorder.model.IDispatch;
import domain.module.workorder.model.ServiceWorkOrderModel;

import java.util.List;

public interface ServiceWorkOrderModelMapper {
    List<ServiceWorkOrderModel> findByWorkOrderId(Long id);

    IDispatch findDispatchById(Long id);
}
