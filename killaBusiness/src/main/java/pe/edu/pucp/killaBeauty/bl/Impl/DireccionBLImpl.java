package pe.edu.pucp.killaBeauty.bl.Impl;

import pe.edu.pucp.dbManager.TransactionContext;
import pe.edu.pucp.killaBeauty.bl.DireccionBL;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Direccion;
import pe.edu.pucp.killaDAO.DireccionDAO;
import pe.edu.pucp.killaDAO.Impl.DireccionDAOImpl;
import pe.edu.pucp.killaBeauty.bl.utils.FormatHelper;

import java.util.List;

public class DireccionBLImpl implements DireccionBL {

    private DireccionDAO direccionDAO = new DireccionDAOImpl();

    @Override
    public Direccion create(Direccion d) throws BusinessLogicException {
        try {
            TransactionContext.getConnection();

            // Capitalizar Alias y Detalle de Dirección
            d.setAlias(FormatHelper.capitalizarTexto(d.getAlias()));
            d.setDireccionDetalle(FormatHelper.capitalizarTexto(d.getDireccionDetalle()));
            if (d.getReferencia() != null) d.setReferencia(FormatHelper.capitalizarTexto(d.getReferencia()));

            // No permitir nombres (alias) repetidos para el mismo usuario
            List<Direccion> lista = direccionDAO.listarPorUsuario(d.getUsuario().getId());
            for (Direccion existente : lista) {
                if (existente.getAlias().equalsIgnoreCase(d.getAlias())) {
                    throw new BusinessLogicException("Ya tienes una dirección registrada con el alias '" + d.getAlias() + "'.");
                }
            }

            if(lista.isEmpty()){
                // Si es la primera, se marca como predeterminada automáticamente
                d.setEsPredeterminada(true);
            } else{
                // Al crear una cuando ya hay otras, por defecto no es predeterminada
                d.setEsPredeterminada(false);
            }
            Direccion guardada = direccionDAO.save(d);
            TransactionContext.commit();
            return guardada;
        } catch (Exception ex) {
            TransactionContext.rollback();
            throw new BusinessLogicException("Error al crear la dirección: " + ex.getMessage());
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public Direccion update(Direccion d) throws BusinessLogicException {
        try {
            TransactionContext.getConnection();

            // CAPITALIZACIÓN: Solo lo necesario
            d.setAlias(FormatHelper.capitalizarTexto(d.getAlias()));
            d.setDireccionDetalle(FormatHelper.capitalizarTexto(d.getDireccionDetalle()));
            if (d.getReferencia() != null) d.setReferencia(FormatHelper.capitalizarTexto(d.getReferencia()));

            // VALIDACIÓN: No duplicados en edición (excluyendo su propio ID)
            List<Direccion> lista = direccionDAO.listarPorUsuario(d.getUsuario().getId());
            for (Direccion existente : lista) {
                if (existente.getAlias().equalsIgnoreCase(d.getAlias()) && existente.getId() != d.getId()) {
                    throw new BusinessLogicException("Ya tienes otra dirección registrada con el alias '" + d.getAlias() + "'.");
                }
            }

            // Si el usuario marca esta como predeterminada, limpiamos las anteriores
            if (d.getEsPredeterminada()) {
                direccionDAO.resetearPredeterminadas(d.getUsuario().getId());
            }
            direccionDAO.update(d);
            TransactionContext.commit();
            return d;
        } catch (Exception ex) {
            TransactionContext.rollback();
            throw new BusinessLogicException("Error al actualizar la dirección: " + ex.getMessage());
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public void remove(Direccion d) throws BusinessLogicException {
        try {
            TransactionContext.getConnection();
            direccionDAO.remove(d);
            TransactionContext.commit();
        } catch (Exception ex) {
            TransactionContext.rollback();
            throw new BusinessLogicException("Error al eliminar la dirección: " + ex.getMessage());
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public Direccion load(Integer id) throws BusinessLogicException {
        try {
            return direccionDAO.load(id);
        } catch (Exception e) {
            throw new BusinessLogicException("Error al cargar la dirección: " + e.getMessage());
        }
    }

    @Override
    public List<Direccion> listAll() throws BusinessLogicException {
        try {
            return direccionDAO.listAll();
        } catch (Exception e) {
            throw new BusinessLogicException("Error al listar todas las direcciones: " + e.getMessage());
        }
    }

    @Override
    public List<Direccion> listarPorUsuario(Integer idUsuario) throws BusinessLogicException {
        try {
            List<Direccion> lista = direccionDAO.listarPorUsuario(idUsuario);
            lista.sort((d1, d2) -> Boolean.compare(d2.getEsPredeterminada(), d1.getEsPredeterminada()));
            return lista;
        } catch (Exception e) {
            throw new BusinessLogicException("Error al listar las direcciones del usuario: " + e.getMessage());
        }
    }
}
