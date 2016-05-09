package db;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Node {

	@JsonProperty("id")
	private Long id;

	String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || id == null || getClass() != o.getClass()) return false;

		Node entity = (Node) o;

		if (!id.equals(entity.id)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (id == null) ? -1 : id.hashCode();
	}
}
