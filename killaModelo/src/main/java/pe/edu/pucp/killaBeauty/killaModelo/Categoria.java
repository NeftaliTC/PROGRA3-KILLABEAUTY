package pe.edu.pucp.killaBeauty.killaModelo;
import java.util.List;
import java.util.ArrayList;

public class Categoria {
    private int id;
    private String nombre;
    private List<Subcategoria> subcategorias;
	private Boolean activo;
	
	public Categoria() {
		this.subcategorias = new ArrayList<>();
	}
	public void agregarSubcategoria(Subcategoria subcategoria) {
    	subcategorias.add(subcategoria);
	}
	//por ver
	public void eliminarSubcategoriaPorId(int id) {
    	for (int i = 0; i < subcategorias.size(); i++) {
        	if (subcategorias.get(i).getId() == id) {
           		subcategorias.remove(i);
            	break;
        	}
    	}
	}	

	public int getId() {
    	return id;
	}

	public String getNombre() {
   		return nombre;
	}
	public List<Subcategoria> getSubcategorias() {
    	return subcategorias;
	}

	public void setId(int id) {
    	this.id = id;
	}

	public void setNombre(String nombre) {
    	this.nombre = nombre;
	}
	public void setSubcategorias(List<Subcategoria> subcategorias) {
    	this.subcategorias = subcategorias;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}
