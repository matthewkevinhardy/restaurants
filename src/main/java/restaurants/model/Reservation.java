package restaurants.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import restaurants.dto.ReservationDTO;

public class Reservation {
	
	private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	private Integer reservationId;
	
	@NotNull(message = "tableId is mandatory")
	private Integer tableId;
	
	@NotNull(message = "start is mandatory")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@ApiModelProperty(example = "yyyy-MM-dd HH:mm")
	private LocalDateTime start;
	
	@NotNull(message = "end is mandatory")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	@ApiModelProperty(example = "yyyy-MM-dd HH:mm")
	private LocalDateTime end;
	
	public Reservation(Integer tableId, LocalDateTime start, LocalDateTime end) {
		this.tableId = tableId;
		this.start = start;
		this.end = end;
	}
	
	public Reservation(ReservationDTO reservationDTO) {
		this.reservationId = reservationDTO.getReservationId();
		this.tableId = reservationDTO.getTableId();
		this.start = reservationDTO.getStart();
		this.end = reservationDTO.getEnd();
	}
		
	
//	public void setStart(LocalDate startDate,LocalTime time) {
//		this.start = LocalDateTime.of(startDate, time);
//	}
	
	public Reservation() {
	}
	
	public Integer getReservationId() {
		return reservationId;
	}
	
	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
	
	public Integer getTableId() {
		return tableId;
	}
	public void setTableId(Integer tableId) {
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
		Reservation other = (Reservation) obj;
		if (reservationId != other.reservationId)
			return false;
		return true;
	}
	
	public static Reservation valueOf(ReservationDTO reservationDTO) {
		Reservation r = new Reservation(reservationDTO);
		return r;
	}
}
