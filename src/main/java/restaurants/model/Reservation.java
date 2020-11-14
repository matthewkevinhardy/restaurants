package restaurants.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RESERVATION")
public class Reservation {
	
	@Id
	@Column(name = "RESERVATION_ID")
	@GeneratedValue
	private int reservationId;
	
	@Column(name = "TABLE_ID")
	private int tableId;
	
	private LocalDateTime start;
	
	private LocalDateTime end;
	
	public Reservation(int tableId, LocalDateTime start, LocalDateTime end) {
		this.tableId = tableId;
		this.start = start;
		this.end = end;
	}
	
	public Reservation() {
	}
	
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
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
	
	
}
