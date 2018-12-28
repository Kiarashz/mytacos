package tacos.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import tacos.Order;
import tacos.Taco;

@Repository
public class JdbcOrderRepository implements OrderRepository {

	private JdbcTemplate jdbc;
	private SimpleJdbcInsert orderInsert;
	private SimpleJdbcInsert orderTacoInsert;
	private ObjectMapper objectMapper;
	
	@Autowired
	public JdbcOrderRepository(JdbcTemplate jdbc) {
		super();
		this.jdbc = jdbc;
		orderInsert = new SimpleJdbcInsert(jdbc)
				.usingGeneratedKeyColumns("oid")
				.withTableName("Orders");
		orderTacoInsert = new SimpleJdbcInsert(jdbc)
				.withTableName("Orders_Tacos");
		objectMapper = new ObjectMapper();
	}



	@Override
	public Order save(Order order) {
		order.setPlacedAt(new Date());
		long oid = saveOrderDetails(order);
		order.setId(oid);
		
		saveOrderTacos(order);
		
		return order;
	}
	
	private void saveOrderTacos(Order order) {
		var values = new HashMap<String, Object>();
		values.put("oid", order.getId());
		for (Taco taco: order.getTacos()) {
			values.put("tid", taco.getId());
			orderTacoInsert.execute(values);
		}
		
	}

	private long saveOrderDetails(Order order) {
		@SuppressWarnings("unchecked")
		Map<String, Object> values = objectMapper.convertValue(order, Map.class);
		values.put("placedAt", order.getPlacedAt());
		long oid = orderInsert.executeAndReturnKey(values).longValue();
		return oid;
	}
}
