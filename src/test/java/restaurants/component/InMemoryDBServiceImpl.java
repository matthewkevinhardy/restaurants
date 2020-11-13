package restaurants.component;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import restaurants.model.Reservation;
import restaurants.model.Restaurant;
import restaurants.model.Table;
import restaurants.utils.ReservationPredicates;

public class InMemoryDBServiceImpl implements DBService {

	private TreeMap<Integer, Restaurant> restaurantMap = new TreeMap<>();
	private TreeMap<Integer, Table> tableMap = new TreeMap<>();
	private TreeMap<Integer, Reservation> reservationMap = new TreeMap<>();

	public Restaurant getRestaurant(int id) {
		return this.restaurantMap.get(id);
	}

	public Table getTable(int id) {
		return this.tableMap.get(id);
	}

	public List<Table> getTables(int restaurantId) {
		return getRestaurant(restaurantId).getTables();
	}

	public Reservation getReservation(int id) {
		return reservationMap.get(id);
	}

	public List<Reservation> getReservations(int restaurantId, Date start, Date end) {

		return getRestaurant(restaurantId).getReservations().stream()
				.filter(ReservationPredicates.byDateAndRestaurant(start, end)).collect(Collectors.toList());
	}

	public int makeReservation(int tableId, int restaurantId, Date start, Date end) {
		Restaurant restaurant = getRestaurant(restaurantId);
		// Table table = getTable(tableId);
		Table table = restaurant.getTables().stream().filter(t -> t.getTableId() == tableId).findAny().orElse(null);
		
		
		
		return 0;
	}

	@Override
	public Restaurant createRestaurant() {
		int newKey;
		Integer lastKey = restaurantMap.lastEntry().getKey();
		if (lastKey != null) {
			newKey = lastKey++;
		} else {
			newKey = 1;
		}
		Restaurant restaurant = new Restaurant(newKey);

		return restaurant;
	}

	@Override
	public Table createTable(int restaurantId, int capacity) {
		Restaurant restaurant = getRestaurant(restaurantId);

		int newKey;
		Integer lastKey = tableMap.lastEntry().getKey();
		if (lastKey != null) {
			newKey = lastKey++;
		} else {
			newKey = 1;
		}

		Table table = new Table(newKey, capacity);
		tableMap.put(newKey, table);
		restaurant.getTables().add(table);

		return null;
	}

}
