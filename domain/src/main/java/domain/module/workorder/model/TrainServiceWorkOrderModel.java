package domain.module.workorder.model;

public class TrainServiceWorkOrderModel implements IDispatch {
	Long id;

	/**
	 * 工单ID
	 */
	Long workOrderId;

	@Override
	public void dispatch(Long staffId) {
		System.out.println("TrainServiceWorkOrderModel dispatch");
	}

	@Override
	public String toString() {
		return "TrainServiceWorkOrderModel{" +
				"id=" + id +
				", workOrderId=" + workOrderId +
				'}';
	}
}
