package application.service;

import domain.module.workorder.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkOrderService {
	@Autowired
	private DispatchService dispatchService;
//	@Autowired
//	private RemoveDispatchService removeDispatchService;

	public void dispatch(DispatchDTO dispatchDTO) {
		dispatchService.dispatch(dispatchDTO.getServiceWorkOrderId(), dispatchDTO.getStaffId());
	}

	public void dispatch(Long serviceWorkOrderId, Long staffId) {
		dispatchService.dispatch(serviceWorkOrderId, staffId);

		// 调度完后发短信

	}

//	public void removeDispatch(Long serviceWorkOrderId) {
//		removeDispatchService.removeDispatch(serviceWorkOrderId);
//	}

	public void finish() {

	}
}

class DispatchDTO {
	private Long serviceWorkOrderId;
	private Long staffId;

	public Long getServiceWorkOrderId() {
		return serviceWorkOrderId;
	}

	public void setServiceWorkOrderId(Long serviceWorkOrderId) {
		this.serviceWorkOrderId = serviceWorkOrderId;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
}
