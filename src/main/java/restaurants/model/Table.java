package restaurants.model;

public class Table {
	private int tableId;
	private int seatingCapacity;
	
	public Table(int tableId, int seatingCapacity) {
		this.tableId = tableId;
		this.seatingCapacity = seatingCapacity;
	}
	public int getTableId() {
		return tableId;
	}
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	
	@Override
	public int hashCode() {
		return this.tableId;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Table other = (Table) obj;
		if (tableId != other.tableId)
			return false;
		return true;
	}
	
	
}
