package restaurants.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import restaurants.dao.ReservationRepository;
import restaurants.exception.NotFoundException;
import restaurants.model.Reservation;

@RestController
@RequestMapping("restaurant_app")
public class ReservationController {

	@Autowired
	private ReservationRepository reservationRepository;

	@ApiOperation(value = "Get a reservation")
	@GetMapping(path = "/reservation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Reservation getReservation(@PathVariable(value = "reservationId", required = true) int reservationId) {
		return reservationRepository.findById(reservationId)
				.orElseThrow(() -> new NotFoundException("reservationId:" + reservationId));
	}

	@ApiOperation(value = "Add a reservation")
	@PostMapping(path = "/reservation/save")
	public Reservation save(final @RequestBody Reservation reservation) {
		reservationRepository.save(reservation);
		return reservation;
	}

}
