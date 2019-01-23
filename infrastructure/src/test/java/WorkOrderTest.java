import domain.module.workorder.model.WorkOrderModel;
import domain.vo.Phone;
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

	@Test
	public void testFind() {
		WorkOrderModel workOrderModel = workOrderModelMapper.findById(1049303827448725504L);
		System.out.println(workOrderModel);

		new Phone("1838469034973");//request.getParamter("phone"));

//		BigDecimal value = BigDecimal.ZERO;
//		value.add(BigDecimal.ONE);
//		String contact;
//		ValidatorUtil.checkEmpty(contact);
//		PhoneValidatorUtil.checkEmpty(contact);
	}
}
