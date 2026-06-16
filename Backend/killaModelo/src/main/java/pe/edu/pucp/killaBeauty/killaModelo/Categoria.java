package pe.edu.pucp.killaBeauty.killaModelo;
import java.util.List;
import java.util.ArrayList;

public class Categoria {
    private int id;
    private String nombre;
	private Boolean activo;

	public Categoria() {
	}

	public Categoria(int id, String nombre, Boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}
