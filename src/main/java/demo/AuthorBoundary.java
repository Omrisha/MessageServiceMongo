package demo;

public class AuthorBoundary {
	private String Id;
	private String email;
	private String name;

	public AuthorBoundary() {
	}

	public AuthorBoundary(AuthorEntity entity) {
		this.setId(entity.getId());
		this.setEmail(entity.getEmail());
		this.setName(entity.getName());
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AuthorBoundary{" +
				"Id='" + Id + '\'' +
				", email='" + email + '\'' +
				", name='" + name + '\'' +
				'}';
	}

	public AuthorEntity toEntity() {
		AuthorEntity entity = new AuthorEntity();
		entity.setId(this.getId());
		entity.setEmail(this.getEmail());
		entity.setName(this.getName());
		return entity;
	}
}
