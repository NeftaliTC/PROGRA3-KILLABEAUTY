package pe.edu.pucp.killaBeauty.bl;

import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Subcategoria;

import java.util.List;

public interface SubcategoriaBL {
    Subcategoria create(Subcategoria s, Integer idCategoria) throws BusinessLogicException;
    Subcategoria update(Subcategoria s, Integer idCategoria) throws BusinessLogicException;
    void remove(Subcategoria s) throws BusinessLogicException;
    Subcategoria load(Integer id) throws BusinessLogicException;
    List<Subcategoria> listAll() throws BusinessLogicException;
    List<Subcategoria> listByCategoriaId(Integer idCategoria) throws BusinessLogicException;
}
