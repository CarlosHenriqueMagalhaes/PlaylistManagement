package br.inatel.project.playlist.enums;

public enum Roles {

	L("listener"), A("admin"), B("artist");

	private String description;

	Roles(String description) {
		this.description = description;
	}

	public String getDescricao() {
		return description;
	}

}
