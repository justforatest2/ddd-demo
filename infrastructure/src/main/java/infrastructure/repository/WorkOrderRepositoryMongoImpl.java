package infrastructure.repository;

import domain.module.workorder.model.ServiceWorkOrderModel;
import domain.module.workorder.model.WorkOrderModel;
import domain.module.workorder.repository.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Component
public class WorkOrderRepositoryMongoImpl implements WorkOrderRepository {
	@Autowired
	private MongoTemplate mongoTemplate;


	@Override
	public WorkOrderModel findById(Long id) {
		@SuppressWarnings("unchecked")
		Map<String, Object> result = mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), Map.class, "work_order");

		try {
			return mapToModel(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public WorkOrderModel findByServiceWorkOrderId(Long id) {
		return null;
	}

//	findFixedToDispatchByDate

	@Override
	public void save(WorkOrderModel workOrderModel) {

	}

	@Override
	public void update(WorkOrderModel workOrderModel) {

	}

	private WorkOrderModel mapToModel(Map<String, Object> map) throws IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
		Class<WorkOrderModel> workOrderModelClass = WorkOrderModel.class;

		WorkOrderModel workOrderModel = workOrderModelClass.newInstance();

		Field userId = workOrderModelClass.getDeclaredField("userId");
		userId.setAccessible(true);
		userId.set(workOrderModel, ((Integer)map.get("userId")).longValue());

		Field id = workOrderModelClass.getDeclaredField("id");
		id.setAccessible(true);
		id.set(workOrderModel, map.get("id"));


		Class<ServiceWorkOrderModel> serviceWorkOrderModelClass = ServiceWorkOrderModel.class;

		Constructor<ServiceWorkOrderModel> serviceWorkOrderModelConstructor = serviceWorkOrderModelClass.getDeclaredConstructor();
		serviceWorkOrderModelConstructor.setAccessible(true);
		ServiceWorkOrderModel serviceWorkOrderModel = serviceWorkOrderModelConstructor.newInstance();

		Field productName = serviceWorkOrderModelClass.getDeclaredField("productName");
		productName.setAccessible(true);
		productName.set(serviceWorkOrderModel, ((Map)map.get("serviceWorkOrder")).get("productName"));

		Field serviceWorkOrder = workOrderModelClass.getDeclaredField("serviceWorkOrder");
		serviceWorkOrder.setAccessible(true);
		serviceWorkOrder.set(workOrderModel, serviceWorkOrderModel);

		return workOrderModel;
	}
}
