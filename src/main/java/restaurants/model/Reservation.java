package restaurants.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "RESERVATION")
public class Reservation {
	
	@Id
	@Column(name = "RESERVATION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer reservationId;
	
	@Column(name = "TABLE_ID")
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
}
