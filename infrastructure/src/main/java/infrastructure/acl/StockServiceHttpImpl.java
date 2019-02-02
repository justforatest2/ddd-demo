package infrastructure.acl;

import domain.acl.StockService;

import java.time.LocalDateTime;

public class StockServiceHttpImpl implements StockService {
	@Override
	public void dispatch(Long staffId, LocalDateTime dateTime) {
		// http方式调用库存接口
	}
}
