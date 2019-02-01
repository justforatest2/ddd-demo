import application.service.WorkOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:ddd-application.xml"})
public class WorkOrderServiceTest {
	@Autowired
	private WorkOrderService workOrderService;

	@Test
	public void testDispatch() {
		Long serviceWorkOrderId = 1110779225596035072L;
		Long staffId = 20164635L;
		workOrderService.dispatch(serviceWorkOrderId, staffId);
	}
}
