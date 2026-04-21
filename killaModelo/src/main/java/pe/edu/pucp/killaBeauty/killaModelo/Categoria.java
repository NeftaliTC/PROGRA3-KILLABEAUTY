package pe.edu.pucp.killaBeauty.killaModelo;
import java.util.List;
import java.util.ArrayList;

public class Categoria {
    private int idCategoria;
    private String nombre;
    private List<Subcategoria> subcategorias;
	
	public Categoria() {
		this.subcategorias = new ArrayList<>();
	}
	public void agregarSubcategoria(Subcategoria subcategoria) {
    	subcategorias.add(subcategoria);
	}
	//por ver
	public void eliminarSubcategoriaPorId(int idSubcategoria) {
    	for (int i = 0; i < subcategorias.size(); i++) {
        	if (subcategorias.get(i).getIdSubcategoria() == idSubcategoria) {
           		subcategorias.remove(i);
            	break;
        	}
    	}
	}	

	public int getIdCategoria() {
    	return idCategoria;
	}

	public String getNombre() {
   		return nombre;
	}
	public List<Subcategoria> getSubcategorias() {
    	return subcategorias;
	}

	public void setIdCategoria(int idCategoria) {
    	this.idCategoria = idCategoria;
	}

	public void setNombre(String nombre) {
    	this.nombre = nombre;
	}
	public void setSubcategorias(List<Subcategoria> subcategorias) {
    	this.subcategorias = subcategorias;
	}
	
}
