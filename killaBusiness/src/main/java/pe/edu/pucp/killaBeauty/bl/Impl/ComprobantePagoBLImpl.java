package pe.edu.pucp.killaBeauty.bl.Impl;

import pe.edu.pucp.dbManager.TransactionContext;
import pe.edu.pucp.killaBeauty.bl.ComprobantePagoBL;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.ComprobantePago;
import pe.edu.pucp.killaDAO.ComprobantePagoDAO;
import pe.edu.pucp.killaDAO.Impl.ComprobantePagoDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class ComprobantePagoBLImpl implements ComprobantePagoBL {

    private ComprobantePagoDAO comprobanteDAO = new ComprobantePagoDAOImpl();

    @Override
    public ComprobantePago create(ComprobantePago c) throws BusinessLogicException {
        try {
            // Iniciamos transacción para asegurar consistencia entre tablas
            TransactionContext.getConnection();
            ComprobantePago comprobanteGuardado = comprobanteDAO.save(c);
            TransactionContext.commit();
            return comprobanteGuardado;
        } catch (SQLException e) {
            TransactionContext.rollback();
            throw new BusinessLogicException("Error crítico al emitir el comprobante: " + e.getMessage());
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public ComprobantePago load(Integer id) throws BusinessLogicException {
        try {
            return comprobanteDAO.load(id);
        } catch (SQLException e) {
            throw new BusinessLogicException("Error al cargar el comprobante: " + e.getMessage());
        }
    }

    @Override
    public List<ComprobantePago> listAll() throws BusinessLogicException {
        try {
            return comprobanteDAO.listAll();
        } catch (SQLException e) {
            throw new BusinessLogicException("Error al listar los comprobantes: " + e.getMessage());
        }
    }

    @Override
    public ComprobantePago obtenerPorIdPago(Integer idPago) throws BusinessLogicException{
        try {
            return comprobanteDAO.buscarPorIdPago(idPago);
        } catch (Exception ex) {
            System.err.println("Error al obtener comprobante: " + ex.getMessage());
            return null;
        }
    }
}
