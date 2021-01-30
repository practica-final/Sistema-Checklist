package domainapp.modules.simple.dominio.reportes;

import domainapp.modules.simple.dominio.empresa.Empresa;
import domainapp.modules.simple.dominio.vehiculo.Vehiculo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.isis.applib.value.Blob;;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ejecutar {

    //REPORTE DE VEHICULOS

    public Blob ListadoVehiculosPDF(List<Vehiculo> vehiculos) throws JRException, IOException{

        List<ReporteVehiculo> reporteVehiculos = new ArrayList<>();
        reporteVehiculos.add(new ReporteVehiculo());

        for (Vehiculo vehiculo : vehiculos) {
            ReporteVehiculo reporteVehiculo = new ReporteVehiculo(vehiculo.ReporteDominio(), vehiculo.ReporteMarca(),
                    vehiculo.ReporteModelo(), vehiculo.ReporteAnyo(), vehiculo.ReporteKilometraje(),
                    vehiculo.ReporteVencimientoVtv().toString("dd-MM-yyyy"), vehiculo.ReporteVencimientoPoliza().toString("dd-MM-yyyy"),
                    vehiculo.ReporteEstado());
            reporteVehiculos.add(reporteVehiculo);
        }

        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(reporteVehiculos);
        return GenerarArchivoPDF("ListVehiculoDesing.jrxml","Reporte de Vehiculos.pdf", ds);
    }


    //REPORTE DE EMPRESAS

    public Blob ListadoEmpresasPDF(List<Empresa> empresas) throws JRException, IOException{

        List<ReporteEmpresa> reporteEmpresas = new ArrayList<>();
        reporteEmpresas.add(new ReporteEmpresa());

        for (Empresa empresa : empresas) {
            ReporteEmpresa reporteEmpresa = new ReporteEmpresa(empresa.ReporteCuit(), empresa.ReporteRazonSocial(), empresa.ReporteDireccion(),
                    empresa.ReporteTelefono(), empresa.ReporteEstado());
            reporteEmpresas.add(reporteEmpresa);
        }

        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(reporteEmpresas);
        return GenerarArchivoPDF("ListEmpresaDesing.jrxml","Reporte de Empresas.pdf", ds);
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private Blob GenerarArchivoPDF(String archivoDesing, String nombreSalida, JRBeanCollectionDataSource ds) throws JRException, IOException{

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(archivoDesing);
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ds", ds);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
        byte[] contentBytes = JasperExportManager.exportReportToPdf(jasperPrint);

        return new Blob(nombreSalida, "application/pdf", contentBytes);
    }

}
