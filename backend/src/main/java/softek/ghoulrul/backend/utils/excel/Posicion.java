package softek.ghoulrul.backend.utils.excel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Posicion {
    private int col;
    private int row;

    public void addRows(int rows){
        this.row = this.row + rows;
    }

    public void addCols(int cols){
        this.col = this.col + cols;
    }
}