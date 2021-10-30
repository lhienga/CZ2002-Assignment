public class Table {

	private int tableid;
	private int capacity;
	private STATUS status;

	/**
	 * 
	 * @param id
	 * @param capacity
	 */
	public Table(int id, int capacity) {
		// TODO - implement Table.Table
		throw new UnsupportedOperationException();
	}

	public int getTableId() {
		// TODO - implement Table.getTableId
		throw new UnsupportedOperationException();
	}

	public int getCapacity() {
		return this.capacity;
	}

	public STATUS getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @param id
	 */
	public void setTableId(int id) {
		// TODO - implement Table.setTableId
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(STATUS status) {
		this.status = status;
	}

}