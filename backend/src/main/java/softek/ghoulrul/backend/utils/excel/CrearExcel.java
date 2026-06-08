package softek.ghoulrul.backend.utils.excel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import softek.ghoulrul.backend.exceptions.ExcelException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrearExcel {
    private final XSSFWorkbook wb;
    private final boolean cerrado;
    private final List<EstiloCeldaExcel> estilos;
    private EstiloCeldaExcel rojo;

    public CrearExcel() {
        wb = new XSSFWorkbook();
        cerrado = false;
        estilos = new ArrayList<>();
    }

    public XSSFSheet CrearHoja(String hoja) {
        if (cerrado) {
            throw new ExcelException("El archivo ya se guardó, no se pueden agregar más hojas");
        }

        String nombreHoja;
        if (hoja == null || hoja.isBlank()) {
            nombreHoja = "Nombre vacío";
        } else {
            nombreHoja = hoja.trim().length() < 31 ? hoja.trim() : hoja.substring(0, 31).trim();
        }

        XSSFSheet sheet = wb.createSheet(nombreHoja);
        sheet.setDisplayGridlines(false);  // Oculta la cuadrícula
        return sheet;
    }


    public Posicion creaFila(XSSFSheet hoja, ColumnaFila filaDatos){
        var filasColumnas = new FilasColumnas(wb, hoja, this.estilos, this.estilos.get(0), this.rojo );
        filasColumnas.DibujaFila(filaDatos);
        return filaDatos.getPosicion();
    }

    public void estiloEncabezado(){
        String fuenteNombre = "Tahoma";
        Integer fuenteSize = 11;

        ColorExcel rojo = new ColorExcel("Rojo", "#FEFEFE", "F5F5F5");
        var estiloRojo = new EstiloCeldaExcel(rojo, wb);
        estilos.add(estiloRojo);
        this.rojo = estiloRojo;

        ColorExcel estandar = new ColorExcel("Estandar", "#FEFEFE", "F5F5F5");
        var estiloEstandar = new EstiloCeldaExcel(estandar, wb);
        estilos.add(estiloEstandar);

        var colorEncabezado = new ColorExcel("Encabezado", "002B7F" ,"#002B7F");
        var estiloEncabezado = new EstiloCeldaExcel(colorEncabezado,wb);
        estilos.add(estiloEncabezado);
        var colorTotal = new ColorExcel("Total", "00B050" ,"#00B050");
        var estiloTotal = new EstiloCeldaExcel(colorTotal,wb);
        estilos.add(estiloTotal);

    }

    public void agregaColor(ColorExcel color) throws ExcelException {
        var temp = this.estilos.stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(color.getNombre()))
                .findFirst();
        if(temp.isPresent()){
            throw new ExcelException("El color ya existe");
        }else{
            this.estilos.add(new EstiloCeldaExcel(color, wb));
        }
    }

    public ByteArrayInputStream guardaExcel() throws IOException {
        var out = new ByteArrayOutputStream();
        wb.write(out);
        wb.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
