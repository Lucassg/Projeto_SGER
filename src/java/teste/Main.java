package teste;

import dao.DaoArea_Entrega;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import model.Area_Entrega;

public class Main {

    Date data;

    public static void main(String[] args) {

        GregorianCalendar gci = new GregorianCalendar();
        gci.add(Calendar.MONTH, 0);
        gci.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println(gci.getTime());

        GregorianCalendar gcf = new GregorianCalendar();
        gcf.add(Calendar.MONTH, 0);
        gcf.set(Calendar.DAY_OF_MONTH, gcf.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println(gcf.getTime());
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.MONTH, 0);
        System.out.println(gc.getTime());
        
        SimpleDateFormat formatoi = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatadai = formatoi.format(gci.getTime());
        System.out.println(dataFormatadai);

        SimpleDateFormat formatof = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatadaf = formatof.format(gcf.getTime());
        System.out.println(dataFormatadaf);

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formato.format(gc.getTime());
        System.out.println(dataFormatada);
        
        
    }
}
