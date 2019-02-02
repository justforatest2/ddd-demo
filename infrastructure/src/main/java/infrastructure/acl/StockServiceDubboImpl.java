package infrastructure.acl;

import domain.acl.StockService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StockServiceDubboImpl implements StockService {
	@Override
	public void dispatch(Long staffId, LocalDateTime dateTime) {
		// 真正调用dubbo服务调度员工库存
	}
}
