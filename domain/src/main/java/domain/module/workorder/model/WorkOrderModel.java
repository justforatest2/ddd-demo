package domain.module.workorder.model;

import domain.vo.Contact;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 工单实体类
 */
public class WorkOrderModel {
	private Long id;

	/**
	 * 服务单号
	 */
	private String code;

//    private Address address;

//	private String contacts;
//
//	private String phone;

	private Contact contact;

	/**
	 * 坐标状态：0=未标记，1=不对应标记，2=自动标记,3=移动补全,4=确认标记
	 */
//    private CoordsStatusEnum coordsLevel;

	/**
	 * 用户需求备注
	 */
	private String demand;

	/**
	 * 内部备注
	 */
	private String remark;

	/**
	 * 来源来源,0=单次订单，1=套餐
	 */
//    private WorkOrderSourceTypeEnum sourceType;

	/**
	 * 来源参数
	 */
	private String sourceParam;

	/**
	 * 服务时长
	 */
	private Integer serviceDuration;

	/**
	 * 计划服务开始时间
	 */
	private LocalDateTime serviceStartTime;

	/**
	 * 计划服务结束时间
	 */
	private LocalDateTime serviceEndTime;

	/**
	 * 价值金额
	 */
	private BigDecimal priceIncome;

	/**
	 * 数量
	 */
	private Integer quantity;

	/**
	 * 状态：0=下单，20=已调度，60=服务中，70=服务完成，100=取消（主子表一致），110=终止
	 */
//    private WorkOrderStatusEnum status = WorkOrderStatusEnum.ORDER;

	/**
	 * 服务工单（一对一）
	 */
//    private ServiceWorkOrderModel serviceWorkOrder;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 是否有评价(0=否，1=有)
	 */
	private Integer commented = 0;

	/**
	 * 是否有空单(0=否，1=有)
	 */
	private Integer exceptionFlag = 0;

	Integer status;

	/**
	 * 取消原因类型
	 */
//    private CancelReasonTypeEnum cancelType;

	/**
	 * 取消原因类型
	 */
//    private SmsSendStatusEnum smsStatus;

//    private WorkOrderLogModel workOrderLogModel;

	private String createBy;

	private String modifyBy;

//	private List<ServiceWorkOrderModel> serviceWorkOrders;

//	private List<TrainServiceWorkOrderModel> trainServiceWorkOrders;

	private ServiceWorkOrderModel serviceWorkOrder;

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public ServiceWorkOrderModel getServiceWorkOrder() {
		return serviceWorkOrder;
	}

	public void dispatch(Long staffId) {
		status = 20;

		serviceWorkOrder.dispatch(staffId);
	}

	public void depart() {
		serviceWorkOrder.depart();	// 分而治之
	}

	@Override
	public String toString() {
//		serviceWorkOrders.get(0).type == 0 服务单 -> 已出发，服务中，空单
//		serviceWorkOrders.get(0).type == 1 培训单 -> 调度，移除调度
		return "WorkOrderModel{" +
				"id=" + id +
				", code='" + code + '\'' +
				", contact=" + contact +
				", demand='" + demand + '\'' +
				", remark='" + remark + '\'' +
				", sourceParam='" + sourceParam + '\'' +
				", serviceDuration=" + serviceDuration +
				", serviceStartTime=" + serviceStartTime +
				", serviceEndTime=" + serviceEndTime +
				", priceIncome=" + priceIncome +
				", quantity=" + quantity +
				", userId=" + userId +
				", commented=" + commented +
				", exceptionFlag=" + exceptionFlag +
				", createBy='" + createBy + '\'' +
				", modifyBy='" + modifyBy + '\'' +
				", serviceWorkOrder=" + serviceWorkOrder +
//				", serviceWorkOrders=" + serviceWorkOrders +
				'}';
	}
}