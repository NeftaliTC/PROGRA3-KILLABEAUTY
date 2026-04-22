package pe.edu.pucp.killaBeauty.killaModelo;
<<<<<<< HEAD

=======
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
import java.util.List;
import java.util.ArrayList;

public class Categoria {
<<<<<<< HEAD
    private int id;
    private String nombre;
    private List<Subcategoria> subcategorias;

    public Categoria() {
        this.subcategorias = new ArrayList<>();
    }

    public int getIdCategoria() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    public List<Subcategoria> getSubcategorias() {
        return subcategorias;
    }

    public void setIdCategoria(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setSubcategorias(List<Subcategoria> subcategorias) {
        this.subcategorias = subcategorias;
    }
=======
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
	
>>>>>>> a9af6bf1bc00f06ed32a6e4560954ef4086471c8
}
