package tacos.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.fasterxml.jackson.databind.ObjectMapper;

import tacos.Order;
import tacos.Taco;

public class JdbcOrderRepository implements OrderRepository {

	private SimpleJdbcInsert orderInsert;
	private SimpleJdbcInsert orderTacoInsert;
	
	private ObjectMapper objectMapper;
	
	
	
	public JdbcOrderRepository(JdbcTemplate jdbc) {
		super();
		this.orderInsert = new SimpleJdbcInsert(jdbc)
				.withTableName("Orders")
				.usingGeneratedKeyColumns("oid");
		this.orderTacoInsert = new SimpleJdbcInsert(jdbc)
				.withTableName("Orders_Tacos");
	}



	@Override
	public Order save(Order order) {
		Map<String, Object> values = objectMapper.convertValue(order, Map.class);
		values.put("placedAt", new Date());
		long oid = orderInsert.executeAndReturnKey(values).longValue();
		for (Taco taco: order.getTacos()) {
			HashMap<String, Long> ids = new HashMap<>();
			ids.put("tid", taco.getId());
			ids.put("oid", order.getId());
			orderTacoInsert.execute(ids);
		}
		return order;
	}

}
