package restaurants.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import restaurants.component.ReservationComponent;
import restaurants.model.Reservation;

@RestController
@RequestMapping("/api/v1")
public class ReservationController {

	@Autowired
	private ReservationComponent reservationComponent;

	@ApiOperation(value = "Get a reservation")
	@GetMapping("/reservation/{reservationId}")
	public ResponseEntity<Reservation> getReservation(@PathVariable(value = "reservationId", required = true) int reservationId) {
		return new ResponseEntity<>(reservationComponent.getReservation(reservationId),HttpStatus.OK);
	}

	@ApiOperation(value = "Add a reservation")
	@PostMapping("/reservation/save")
	public ResponseEntity<Reservation> save(@Valid @RequestBody Reservation reservation) {
		Reservation saved = reservationComponent.save(reservation);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update a reservation")
	@PutMapping("/reservation/{reservationId}/update")
	public ResponseEntity<Reservation> update(@Valid @RequestBody Reservation reservation,@PathVariable(value = "reservationId", required = true) Integer reservationId) {
		reservation.setReservationId(reservationId);
		Reservation updated = reservationComponent.update(reservation);
		return new ResponseEntity<>(updated,HttpStatus.OK);
	}

//	@ApiOperation(value = "Get reservations for restaurant and date range")
//	@GetMapping("/restaurant/{restaurantId}/reservations")
//	public ResponseEntity<List<Reservation>> findByIdDateRange(@PathVariable(value = "restaurantId", required = true) int restaurantId,
//			@ApiParam(value = "yyyy-MM-dd HH:mm",required = true) @RequestParam(name = "start", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime start,
//			@ApiParam(value = "yyyy-MM-dd HH:mm",required = true) @RequestParam(name = "end", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime end) {
//		List<Reservation> reservations = reservationComponent.findByRestaurantIdAndDateRange(restaurantId, start, end);
//		return new ResponseEntity<List<Reservation>>(reservations,HttpStatus.OK);
//	}

	@ApiOperation(value = "Get reservations for restaurant and date")
	@GetMapping("/restaurant/{restaurantId}/reservations")
	public ResponseEntity<List<Reservation>> findByIdDate(@PathVariable(value = "restaurantId", required = true) int restaurantId,
			@ApiParam(value = "yyyy-MM-dd",required = true) @RequestParam(name = "date", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		List<Reservation> reservations = reservationComponent.findByRestaurantIdAndDate(restaurantId, date);
		return new ResponseEntity<List<Reservation>>(reservations,HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete a reservation")
	@DeleteMapping("/reservation/{reservationId}")
	public ResponseEntity<Void> delete(@PathVariable(value = "reservationId", required = true) int reservationId) {
		reservationComponent.delete(reservationId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
