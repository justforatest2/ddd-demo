import domain.module.workorder.model.IDispatch;
import infrastructure.dao.ServiceWorkOrderModelMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ServiceWorkOrderTest {
	@Autowired
	private ServiceWorkOrderModelMapper serviceWorkOrderModelMapper;

	@Test
	public void testFind() {
		// dispatch(id, staffId);

		// dispatchTrain(id, staffId);

//		IDispatch dispatchOrderModel = serviceWorkOrderModelMapper.findDispatchById(1049303827448725504L);

		// 培训单ID 1125447853158694912
		IDispatch dispatchOrderModel = serviceWorkOrderModelMapper.findDispatchById(1125447853158694912L);
		dispatchOrderModel.dispatch();
//		System.out.println(dispatchOrderModel);
	}
}