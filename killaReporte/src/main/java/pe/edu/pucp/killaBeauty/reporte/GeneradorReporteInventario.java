package pe.edu.pucp.killaBeauty.reporte;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import pe.edu.pucp.killaBeauty.reporte.DTO.FilaReporteInventarioDTO;
import pe.edu.pucp.killaBeauty.reporte.DTO.ReporteInventarioDTO;

import java.awt.Color;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GeneradorReporteInventario {
    private static final int PAGE_WIDTH = 842;
    private static final int PAGE_HEIGHT = 595;
    private static final int MARGIN = 28;
    private static final int COLUMN_WIDTH = PAGE_WIDTH - (MARGIN * 2);

    public byte[] generarReporteInventario(
            ReporteInventarioDTO reporte,
            String estadoFiltro,
            String categoriaFiltro,
            String subcategoriaFiltro,
            String ordenFiltro
    ) throws Exception {
        JasperDesign design = construirDiseno();
        JasperReport jasperReport = JasperCompileManager.compileReport(design);
        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(construirFilas(reporte));

        JasperPrint jasperPrint = net.sf.jasperreports.engine.JasperFillManager.fillReport(
                jasperReport,
                construirParametros(reporte, estadoFiltro, categoriaFiltro, subcategoriaFiltro, ordenFiltro),
                dataSource
        );

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private JasperDesign construirDiseno() throws Exception {
        JasperDesign design = new JasperDesign();
        design.setName("ReporteInventario");
        design.setPageWidth(PAGE_WIDTH);
        design.setPageHeight(PAGE_HEIGHT);
        design.setOrientation(net.sf.jasperreports.engine.type.OrientationEnum.LANDSCAPE);
        design.setLeftMargin(MARGIN);
        design.setRightMargin(MARGIN);
        design.setTopMargin(MARGIN);
        design.setBottomMargin(MARGIN);
        design.setColumnWidth(COLUMN_WIDTH);

        agregarParametros(design);
        agregarCampos(design);
        design.setTitle(construirTitulo());
        design.setColumnHeader(construirEncabezadoTabla());
        ((JRDesignSection) design.getDetailSection()).addBand(construirDetalle());
        design.setSummary(construirResumen());

        return design;
    }

    private void agregarParametros(JasperDesign design) throws Exception {
        agregarParametro(design, "fechaGeneracion", String.class);
        agregarParametro(design, "estadoFiltro", String.class);
        agregarParametro(design, "categoriaFiltro", String.class);
        agregarParametro(design, "subcategoriaFiltro", String.class);
        agregarParametro(design, "ordenFiltro", String.class);
        agregarParametro(design, "valorTotal", BigDecimal.class);
        agregarParametro(design, "stockCritico", Integer.class);
        agregarParametro(design, "agotados", Integer.class);
        agregarParametro(design, "unidadesTotales", Integer.class);
    }

    private void agregarCampos(JasperDesign design) throws Exception {
        agregarCampo(design, "sku", String.class);
        agregarCampo(design, "nombre", String.class);
        agregarCampo(design, "marca", String.class);
        agregarCampo(design, "categoria", String.class);
        agregarCampo(design, "subcategoria", String.class);
        agregarCampo(design, "stockActual", Integer.class);
        agregarCampo(design, "stockEstado", String.class);
        agregarCampo(design, "precioUnitario", BigDecimal.class);
        agregarCampo(design, "valorInventario", BigDecimal.class);
    }

    private JRDesignBand construirTitulo() {
        JRDesignBand band = new JRDesignBand();
        band.setHeight(112);

        band.addElement(textoEstatico("Reporte de inventario", 0, 0, 320, 28, 20, true));
        band.addElement(campoTexto("\"Generado: \" + $P{fechaGeneracion}", 590, 4, 190, 18, 9, false, true));

        band.addElement(textoEstatico("Estado", 0, 44, 60, 14, 9, true));
        band.addElement(campoTexto("$P{estadoFiltro}", 65, 44, 120, 14, 9, false, false));
        band.addElement(textoEstatico("Categoria", 200, 44, 70, 14, 9, true));
        band.addElement(campoTexto("$P{categoriaFiltro}", 275, 44, 140, 14, 9, false, false));
        band.addElement(textoEstatico("Subcategoria", 430, 44, 85, 14, 9, true));
        band.addElement(campoTexto("$P{subcategoriaFiltro}", 520, 44, 140, 14, 9, false, false));
        band.addElement(textoEstatico("Orden", 675, 44, 45, 14, 9, true));
        band.addElement(campoTexto("$P{ordenFiltro}", 720, 44, 60, 14, 9, false, false));

        agregarKpi(band, "Valor inventario", "$P{valorTotal}", 0, 74, 185);
        agregarKpi(band, "Stock critico", "$P{stockCritico}", 200, 74, 170);
        agregarKpi(band, "Agotados", "$P{agotados}", 385, 74, 170);
        agregarKpi(band, "Unidades totales", "$P{unidadesTotales}", 570, 74, 210);

        return band;
    }

    private JRDesignBand construirEncabezadoTabla() {
        JRDesignBand band = new JRDesignBand();
        band.setHeight(24);

        agregarHeader(band, "SKU", 0, 56);
        agregarHeader(band, "Producto", 58, 180);
        agregarHeader(band, "Marca", 240, 86);
        agregarHeader(band, "Categoria", 328, 90);
        agregarHeader(band, "Subcategoria", 420, 100);
        agregarHeader(band, "Stock", 522, 50);
        agregarHeader(band, "Estado", 574, 70);
        agregarHeader(band, "Precio", 646, 60);
        agregarHeader(band, "Valor", 708, 72);

        return band;
    }

    private JRDesignBand construirDetalle() {
        JRDesignBand band = new JRDesignBand();
        band.setHeight(22);

        band.addElement(campoTexto("$F{sku}", 0, 2, 56, 16, 8, false, false));
        band.addElement(campoTexto("$F{nombre}", 58, 2, 180, 16, 8, false, false));
        band.addElement(campoTexto("$F{marca}", 240, 2, 86, 16, 8, false, false));
        band.addElement(campoTexto("$F{categoria}", 328, 2, 90, 16, 8, false, false));
        band.addElement(campoTexto("$F{subcategoria}", 420, 2, 100, 16, 8, false, false));
        band.addElement(campoTexto("$F{stockActual}", 522, 2, 50, 16, 8, false, true));
        band.addElement(campoTexto("$F{stockEstado}", 574, 2, 70, 16, 8, false, false));
        band.addElement(campoTexto("$F{precioUnitario}", 646, 2, 60, 16, 8, false, true));
        band.addElement(campoTexto("$F{valorInventario}", 708, 2, 72, 16, 8, false, true));

        return band;
    }

    private JRDesignBand construirResumen() {
        JRDesignBand band = new JRDesignBand();
        band.setHeight(28);
        band.addElement(campoTexto("\"Productos listados: \" + $V{REPORT_COUNT}", 0, 8, 180, 16, 9, true, false));
        return band;
    }

    private Map<String, Object> construirParametros(
            ReporteInventarioDTO reporte,
            String estadoFiltro,
            String categoriaFiltro,
            String subcategoriaFiltro,
            String ordenFiltro
    ) {
        ReporteInventarioDTO.InventarioKpisDTO kpis = reporte.getKpis() == null
                ? new ReporteInventarioDTO.InventarioKpisDTO()
                : reporte.getKpis();
        Map<String, Object> parametros = new HashMap<>();

        parametros.put("fechaGeneracion", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
        parametros.put("estadoFiltro", normalizarFiltro(estadoFiltro));
        parametros.put("categoriaFiltro", normalizarFiltro(categoriaFiltro));
        parametros.put("subcategoriaFiltro", normalizarFiltro(subcategoriaFiltro));
        parametros.put("ordenFiltro", normalizarFiltro(ordenFiltro));
        parametros.put("valorTotal", nvl(kpis.getValorTotal()));
        parametros.put("stockCritico", kpis.getStockCritico());
        parametros.put("agotados", kpis.getAgotados());
        parametros.put("unidadesTotales", kpis.getUnidadesTotales());

        return parametros;
    }

    private List<FilaReporteInventarioDTO> construirFilas(ReporteInventarioDTO reporte) {
        List<ReporteInventarioDTO.ProductoInventarioDTO> productos = reporte.getProductos() == null
                ? Collections.emptyList()
                : reporte.getProductos();

        return productos.stream()
                .map(this::crearFila)
                .collect(Collectors.toList());
    }

    private FilaReporteInventarioDTO crearFila(ReporteInventarioDTO.ProductoInventarioDTO producto) {
        FilaReporteInventarioDTO fila = new FilaReporteInventarioDTO();
        fila.setSku(producto.getSku());
        fila.setNombre(producto.getNombre());
        fila.setMarca(producto.getMarca());
        fila.setCategoria(producto.getCategoria());
        fila.setSubcategoria(producto.getSubcategoria());
        fila.setStockActual(producto.getStockActual());
        fila.setStockEstado(producto.getStockEstado());
        fila.setPrecioUnitario(nvl(producto.getPrecioUnitario()));
        fila.setValorInventario(nvl(producto.getValorInventario()));
        return fila;
    }

    private void agregarKpi(JRDesignBand band, String etiqueta, String expresion, int x, int y, int width) {
        band.addElement(textoEstatico(etiqueta, x, y, width, 12, 8, true));
        band.addElement(campoTexto(expresion, x, y + 13, width, 16, 12, true, false));
    }

    private void agregarHeader(JRDesignBand band, String texto, int x, int width) {
        JRDesignStaticText header = textoEstatico(texto, x, 0, width, 22, 8, true);
        header.setMode(ModeEnum.OPAQUE);
        header.setBackcolor(new Color(244, 201, 219));
        band.addElement(header);
    }

    private void agregarParametro(JasperDesign design, String nombre, Class<?> tipo) throws Exception {
        JRDesignParameter parameter = new JRDesignParameter();
        parameter.setName(nombre);
        parameter.setValueClass(tipo);
        design.addParameter(parameter);
    }

    private void agregarCampo(JasperDesign design, String nombre, Class<?> tipo) throws Exception {
        JRDesignField field = new JRDesignField();
        field.setName(nombre);
        field.setValueClass(tipo);
        design.addField(field);
    }

    private JRDesignStaticText textoEstatico(String texto, int x, int y, int width, int height, int fontSize, boolean bold) {
        JRDesignStaticText element = new JRDesignStaticText();
        element.setX(x);
        element.setY(y);
        element.setWidth(width);
        element.setHeight(height);
        element.setFontSize((float) fontSize);
        element.setBold(bold);
        element.setText(texto);
        return element;
    }

    private JRDesignTextField campoTexto(String expresion, int x, int y, int width, int height,
                                         int fontSize, boolean bold, boolean rightAlign) {
        JRDesignTextField element = new JRDesignTextField();
        element.setX(x);
        element.setY(y);
        element.setWidth(width);
        element.setHeight(height);
        element.setFontSize((float) fontSize);
        element.setBold(bold);
        element.setBlankWhenNull(true);
        element.setExpression(expresion(expresion));
        if (rightAlign) {
            element.setHorizontalTextAlign(HorizontalTextAlignEnum.RIGHT);
        }
        return element;
    }

    private JRDesignExpression expresion(String texto) {
        JRDesignExpression expression = new JRDesignExpression();
        expression.setText(texto);
        return expression;
    }

    private String normalizarFiltro(String filtro) {
        if (filtro == null || filtro.isBlank()) return "Todos";
        return filtro.trim();
    }

    private BigDecimal nvl(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }
}
