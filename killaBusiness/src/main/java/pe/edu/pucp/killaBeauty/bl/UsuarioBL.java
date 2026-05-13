package pe.edu.pucp.killaBeauty.bl;

import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Usuario;

import java.util.List;

public interface UsuarioBL {
    Usuario create(Usuario u) throws BusinessLogicException;
    Usuario update(Usuario u) throws BusinessLogicException;
    void remove(Usuario u) throws BusinessLogicException;
    Usuario load(Integer id) throws BusinessLogicException;
    List<Usuario> listAll() throws BusinessLogicException;
}
