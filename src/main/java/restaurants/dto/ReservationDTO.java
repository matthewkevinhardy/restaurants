package restaurants.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import restaurants.model.Reservation;

@Entity
@Table(name = "RESERVATION")
public class ReservationDTO {
	
	@Id
	@Column(name = "RESERVATION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer reservationId;
	
	@NotNull(message = "tableId is mandatory")
	@Column(name = "TABLE_ID")
	private int tableId;
	
	@NotNull(message = "start is mandatory")
	private LocalDateTime start;
	
	@NotNull(message = "end is mandatory")
	private LocalDateTime end;
	
	public ReservationDTO(int tableId, LocalDateTime start, LocalDateTime end) {
		this.tableId = tableId;
		this.start = start;
		this.end = end;
	}
	
	public ReservationDTO(Reservation reservation) {
		this.reservationId = reservation.getReservationId();
		this.tableId = reservation.getTableId();
		this.start = reservation.getStart();
		this.end = reservation.getEnd();
	}
	
//	public void setStart(LocalDate startDate,LocalTime time) {
//		this.start = LocalDateTime.of(startDate, time);
//	}
	
	public ReservationDTO() {
	}
	
	public Integer getReservationId() {
		return reservationId;
	}
	
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	@Override
	public int hashCode() {
		return reservationId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservationDTO other = (ReservationDTO) obj;
		if (reservationId != other.reservationId)
			return false;
		return true;
	}
	
	public static ReservationDTO valueOf(Reservation reservation) {
		ReservationDTO r = new ReservationDTO(reservation);
		
		return r;
	}
}
