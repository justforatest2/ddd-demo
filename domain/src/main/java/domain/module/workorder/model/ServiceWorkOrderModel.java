package domain.module.workorder.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 服务工单实体类
 */
public class ServiceWorkOrderModel {
    Long id;

    /**
     * 工单ID
     */
    Long workOrderId;

    /**
     * 服务工单号(待设计)
     */
    String code;

    /**
     * 服务产品ID
     */
    Long productId;

    /**
     * 服务产品名称
     */
    String productName;

    /**
     * 计划服务时间
     */
    LocalDateTime serviceTime;

    /**
     * 服务时长（分钟），0表示不限
     */
    Integer serviceDuration;

    /**
     * 用户ID
     */
    Long userId;

    /**
     * 价值金额（元）
     */
    BigDecimal priceIncome;

    /**
     * 员工成本
     */
    BigDecimal priceCost;

    /**
     * 订单类型：0=服务单，1=附加单，2=培训单，3=补偿单
     */
//    WorkOrderTypeEnum type;
    Integer type;

    /**
     * 状态：0=下单，10=预调度，20=已调度，30=服务确认，40=已出发，50=已到达，60=服务中，70=服务完成，100=取消，110=终止
     */
//    ServiceWorkOrderStatusEnum status;
    Integer status;

    /**
     * 员工预占号
     */
    Long staffReserveNo;

    /**
     * 参考员工ID，优先调度(固定保洁员工)
     */
    Long refStaffId;

    /**
     * 是否设置固定员工
     */
//    YesOrNoEnum fixStaffStatus;
    Integer fixStaffStatus = 0;

    /**
     * 参考员工调节类型（0=不可调节，1=可调节,默认值为1）
     */
    Integer refStaffAdjustType = 1;//FIXSTAFF_ALLOW_REPLACE;

    /**
     * 服务员工ID
     */
    Long staffId;

    /**
     * 出发时间
     */
    LocalDateTime departTime;

    /**
     * 到达时间
     */
    LocalDateTime arriveTime;

    /**
     * 工作开始时间
     */
    LocalDateTime workStarTime;

    /**
     * 工作结束时间
     */
    LocalDateTime workEndTime;

    /**
     * 站点ID
     */
    Long siteId;

    /**
     * 站点名称
     */
    String siteName;

    /**
     * 签名图片地址
     */
    String signUrl;

    private String modifyBy;

    /**
     * MyBatis映射需要
     */
    private ServiceWorkOrderModel() {
    }

	public Long getId() {
		return id;
	}

	void depart() {
        status = 60;
    }

//    @Override
    void dispatch() {

    }

	void dispatch(Long staffId) {
        System.out.println("service work order dispatch");

        status = 20;
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        return "ServiceWorkOrderModel{" +
                "id=" + id +
                ", workOrderId=" + workOrderId +
                ", code='" + code + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", serviceTime=" + serviceTime +
                ", serviceDuration=" + serviceDuration +
                ", userId=" + userId +
                ", priceIncome=" + priceIncome +
                ", priceCost=" + priceCost +
                ", staffReserveNo=" + staffReserveNo +
                ", refStaffId=" + refStaffId +
                ", fixStaffStatus=" + fixStaffStatus +
                ", refStaffAdjustType=" + refStaffAdjustType +
                ", staffId=" + staffId +
                ", departTimea=" + departTime +
                ", arriveTime=" + arriveTime +
                ", workStarTime=" + workStarTime +
                ", workEndTime=" + workEndTime +
                ", siteId=" + siteId +
                ", siteName='" + siteName + '\'' +
                ", signUrl='" + signUrl + '\'' +
                ", modifyBy='" + modifyBy + '\'' +
                '}';
    }
}