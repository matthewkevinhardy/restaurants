package restaurants.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "RESERVATION")
public class Reservation {
	
	@Id
	@Column(name = "RESERVATION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int reservationId;
	
	@Column(name = "TABLE_ID")
	private int tableId;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime start;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime end;
	
	public Reservation(int tableId, LocalDateTime start, LocalDateTime end) {
		this.tableId = tableId;
		this.start = start;
		this.end = end;
	}
	
//	public void setStart(LocalDate startDate,LocalTime time) {
//		this.start = LocalDateTime.of(startDate, time);
//	}
	
	public Reservation() {
	}
	
	public int getReservationId() {
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
	
	
}
