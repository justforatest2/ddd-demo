import domain.module.workorder.model.WorkOrderModel;
import domain.module.workorder.repository.WorkOrderRepository;
import infrastructure.dao.WorkOrderModelMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class WorkOrderTest {
	@Autowired
	private WorkOrderModelMapper workOrderModelMapper;
	@Autowired
	private WorkOrderRepository workOrderRepository;

	@Test
	public void testFind() {
		WorkOrderModel workOrderModel = workOrderModelMapper.findById(1049303827448725504L);
		System.out.println(workOrderModel);

//		new Phone("1838469034973");//request.getParameter("phone"));

//		BigDecimal value = BigDecimal.ZERO;
//		value.add(BigDecimal.ONE);
//		String contact;
//		ValidatorUtil.checkEmpty(contact);
//		PhoneValidatorUtil.checkEmpty(contact);
	}

	@Test
	public void testMultiRow() {
		Long workOrderId = 1088499092260978688L;
		WorkOrderModel workOrderModel = workOrderModelMapper.findById(workOrderId);
		System.out.println(workOrderModel);
	}

	@Test
	public void testSingleRow() {
		Long serviceWorkOrderId = 1049748441046974464L;
		WorkOrderModel workOrderModel = workOrderModelMapper.findByServiceWorkOrderId(serviceWorkOrderId);
		System.out.println(workOrderModel);
	}

	@Test
	public void testMyBatisParam() {
		Long serviceWorkOrderId = 1049748441046974464L;
		WorkOrderModel workOrderModel = workOrderModelMapper.findByServiceWorkOrderIdAndType(serviceWorkOrderId, 0);
		System.out.println(workOrderModel);
	}

	@Test
	public void testDispatch() {
		// WorkOrderService dispatch(Long serviceWorkOrderId, Long staffId);
		Long serviceWorkOrderId = 1049748441046974464L;
		Long staffId = 20164635L;

//		update service_work_order set status=20, staff_id=20164635L

		WorkOrderModel workOrderModel = workOrderModelMapper.findByServiceWorkOrderId(serviceWorkOrderId);
		workOrderModel.dispatch(staffId);

		// 出发
		workOrderModel.depart();

//		workOrderModel.getServiceWorkOrderId();

//		ServiceWorkOrderModel serviceWorkOrder = workOrderModel.getServiceWorkOrder();
//		serviceWorkOrder.getId();

//		workOrderModel.finish();

		// 修改 combo_plan_order combo_point_record
		// 弱一致来说 finish -> MQ消息（本地消息）
		// combo_plan_order combo_point_record 各自监听，进而去修改自己的状态


//		ServiceWorkOrderModel serviceWorkOrderModel = workOrderModel.getServiceWorkOrder();
//		serviceWorkOrderModel.dispatch();
	}

	@Test
	public void testMongoFind() {
		Long workOrderId = 1049748441046974464L;
		WorkOrderModel workOrderModel = workOrderRepository.findById(workOrderId);
//		workOrderModel.dispatch(20161234L);
		System.out.println(workOrderModel);
	}

	@Test
	public void testMongo() throws IllegalAccessException, InstantiationException, NoSuchFieldException {
//		Class<WorkOrderModel> workOrderModelClass = WorkOrderModel.class;
//
//		WorkOrderModel workOrderModel = workOrderModelClass.newInstance();
//
//		Field userId = workOrderModelClass.getDeclaredField("userId");
//		userId.setAccessible(true);
//		userId.set(workOrderModel, 111L);

//		System.out.println(workOrderModel);

//		->findAll();
//
//		List<Long> numberList = new ArrayList<>();
//		numberList.add(1L);
//		numberList.add(2L);
//
//		numberList.stream().filter(number -> {
//			return true;
//		});
	}

}
