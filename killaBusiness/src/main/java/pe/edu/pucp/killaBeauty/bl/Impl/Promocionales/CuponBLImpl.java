package pe.edu.pucp.killaBeauty.bl.impl.Promocionales;

import pe.edu.pucp.dbManager.TransactionContext;
import pe.edu.pucp.killaBeauty.bl.Promocionales.CuponBL;
import pe.edu.pucp.killaBeauty.bl.exception.BusinessLogicException;
import pe.edu.pucp.killaBeauty.killaModelo.Promocionales.Campana;
import pe.edu.pucp.killaBeauty.killaModelo.Promocionales.Cupon;
import pe.edu.pucp.killaBeauty.killaModelo.Promocionales.TipoDescuento;
import pe.edu.pucp.killaDAO.Impl.Promocionales.CampanaDAOImpl;
import pe.edu.pucp.killaDAO.Impl.Promocionales.CuponDAOImpl;
import pe.edu.pucp.killaDAO.Promocionales.CampanaDAO;
import pe.edu.pucp.killaDAO.Promocionales.CuponDAO;

import java.util.List;

public class CuponBLImpl implements CuponBL {

    private CuponDAO cuponDAO = new CuponDAOImpl();
    private CampanaDAO campanaDAO = new CampanaDAOImpl();
    @Override
    public Cupon create(Cupon cupon) throws BusinessLogicException {
        try {
            if (cupon.getCodigo() == null || cupon.getCodigo().trim().isEmpty()) {
                throw new BusinessLogicException("El código del cupón es obligatorio.");
            }
            if (cupon.getValorDescuento() <= 0) {
                throw new BusinessLogicException("El valor de descuento debe ser mayor a cero.");
            }

            // Regla de negocio: Si es porcentaje, no puede ser mayor a 100
            if (cupon.getTipoDescuento() == TipoDescuento.PORCENTAJE && cupon.getValorDescuento() > 100) {
                throw new BusinessLogicException("El porcentaje de descuento no puede ser mayor al 100%.");
            }

            // Validar fechas lógicas
            if (cupon.getFechaInicio() != null && cupon.getFechaFin() != null) {
                if (cupon.getFechaInicio().isAfter(cupon.getFechaFin())) {
                    throw new BusinessLogicException("La fecha de inicio no puede ser posterior a la fecha de fin.");
                }
            }

            // 2. Verificando que la campaña asociada exista
            if (cupon.getCampana() != null && cupon.getCampana().getIdCampana() > 0) {
                Campana campanaBD = campanaDAO.load(cupon.getCampana().getIdCampana());
                if (campanaBD == null) {
                    throw new BusinessLogicException("La campaña asociada no existe en la base de datos.");
                }
                if (!campanaBD.isActivo()) {
                    throw new BusinessLogicException("No se puede asociar un cupón a una campaña inactiva.");
                }
            }

            cuponDAO.save(cupon);

            TransactionContext.commit();
        } catch (Exception ex) {
            TransactionContext.rollback();
            if (ex instanceof BusinessLogicException) {
                throw (BusinessLogicException) ex;
            } else {
                throw new BusinessLogicException(ex);
            }
        } finally {
            TransactionContext.close();
        }
        return cupon;
    }

    @Override
    public Cupon update(Cupon cupon) throws BusinessLogicException {
        try {
//            if (cupon.id() <= 0) {
//                throw new BusinessLogicException("Se requiere un ID válido para actualizar el cupón.");
//            }
            if (cupon.getValorDescuento() <= 0) {
                throw new BusinessLogicException("El valor de descuento debe ser mayor a cero.");
            }
            if (cupon.getTipoDescuento() == TipoDescuento.PORCENTAJE && cupon.getValorDescuento() > 100) {
                throw new BusinessLogicException("El porcentaje de descuento no puede ser mayor al 100%.");
            }
            if (cupon.getFechaInicio() != null && cupon.getFechaFin() != null) {
                if (cupon.getFechaInicio().isAfter(cupon.getFechaFin())) {
                    throw new BusinessLogicException("La fecha de inicio no puede ser posterior a la fecha de fin.");
                }
            }

            // Validar campaña
            if (cupon.getCampana() != null && cupon.getCampana().getIdCampana() > 0) {
                Campana campanaBD = campanaDAO.load(cupon.getCampana().getIdCampana());
                if (campanaBD == null) {
                    throw new BusinessLogicException("La campaña asociada no existe.");
                }
            }
            cuponDAO.update(cupon);

            TransactionContext.commit();
        } catch (Exception ex) {
            TransactionContext.rollback();
            if (ex instanceof BusinessLogicException) {
                throw (BusinessLogicException) ex;
            } else {
                throw new BusinessLogicException(ex);
            }
        } finally {
            TransactionContext.close();
        }
        return cupon;
    }

    @Override
    public void remove(Cupon cupon) throws BusinessLogicException {
        try {
            if (cupon.getIdCupon() <= 0) {
                throw new BusinessLogicException("Se requiere un ID válido para eliminar el cupón.");
            }

            // Eliminación lógica usando el método del DAO
            cuponDAO.remove(cupon);

            TransactionContext.commit();
        } catch (Exception ex) {
            TransactionContext.rollback();
            if (ex instanceof BusinessLogicException) {
                throw (BusinessLogicException) ex;
            } else {
                throw new BusinessLogicException(ex);
            }
        } finally {
            TransactionContext.close();
        }
    }

    @Override
    public Cupon load(Integer id) throws BusinessLogicException {
        Cupon cupon = null;
        try {
            if (id == null || id <= 0) {
                throw new BusinessLogicException("ID de cupón inválido.");
            }

            cupon = cuponDAO.load(id);
            TransactionContext.commit();
        } catch (Exception ex) {
            TransactionContext.rollback();
            if (ex instanceof BusinessLogicException) {
                throw (BusinessLogicException) ex;
            } else {
                throw new BusinessLogicException(ex);
            }
        } finally {
            TransactionContext.close();
        }
        return cupon;
    }

    @Override
    public List<Cupon> listAll() throws BusinessLogicException {
        List<Cupon> lista = null;
        try {
            lista = cuponDAO.listAll();
            TransactionContext.commit();
        } catch (Exception ex) {
            TransactionContext.rollback();
            if (ex instanceof BusinessLogicException) {
                throw (BusinessLogicException) ex;
            } else {
                throw new BusinessLogicException(ex);
            }
        } finally {
            TransactionContext.close();
        }
        return lista;
    }
}
