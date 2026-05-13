package pe.edu.pucp.killaBeauty.bl;

import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Categoria;

import java.util.List;

public interface CategoriaBL {
    Categoria create(Categoria c) throws BusinessLogicException;
    Categoria update(Categoria c) throws BusinessLogicException;
    void remove(Categoria c) throws BusinessLogicException;
    Categoria load(Integer id) throws BusinessLogicException;
    List<Categoria> listAll() throws BusinessLogicException;
}
