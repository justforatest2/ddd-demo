package domain.acl;

import java.time.LocalDateTime;

public interface StockService {
	void dispatch(Long staffId, LocalDateTime dateTime);
}
