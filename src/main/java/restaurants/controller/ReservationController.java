package restaurants.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import restaurants.component.ReservationComponent;
import restaurants.model.Reservation;

@RestController
@RequestMapping("restaurant_app")
public class ReservationController {

	@Autowired
	private ReservationComponent reservationComponent;

	@ApiOperation(value = "Get a reservation")
	@GetMapping(path = "/reservation/{reservationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Reservation getReservation(@PathVariable(value = "reservationId", required = true) int reservationId) {
		return reservationComponent.getReservation(reservationId);
	}

	@ApiOperation(value = "Add a reservation")
	@PostMapping(path = "/reservation/save")
	public Reservation save(final @RequestBody Reservation reservation) {
		Reservation saved = reservationComponent.save(reservation);
		return saved;
	}

	@ApiOperation(value = "Update a reservation")
	@PutMapping(path = "/reservation/update")
	public Reservation update(final @RequestBody Reservation reservation) {
		Reservation updated = reservationComponent.update(reservation);
		return updated;
	}
	
	@ApiOperation(value = "Get reservations for restaurant and date")
	@ApiModelProperty(name = "start",value="yyyy-MM-dd HH:mm")
	@GetMapping(path = "/restaurant/{restaurantId}/reservations")
	public List<Reservation> save(@PathVariable(value = "restaurantId", required = true) int restaurantId,
			@RequestParam(name = "start", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime start,
			@RequestParam(name = "end", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime end) {
		List<Reservation> reservations = reservationComponent.findByIdDate(restaurantId, start, end);
		return reservations;
	}
	
	@ApiOperation(value = "Delete a reservation")
	@DeleteMapping(path = "/reservation/{reservationId}")
	public void delete(@PathVariable(value = "reservationId", required = true) int reservationId) {
		reservationComponent.delete(reservationId);
	}
}
