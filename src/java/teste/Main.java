package teste;

import dao.DaoArea_Entrega;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Area_Entrega;

public class Main {

    public static void main(String[] args){

        Calendar c = Calendar.getInstance();
	c.set(Calendar.MONTH,Calendar.FEBRUARY);
        System.out.println();
	DateFormat df  = new SimpleDateFormat("dd/MM/yyyy");
	System.out.println("Maior dia de Fevereiro:" + c.getActualMaximum(Calendar.DAY_OF_MONTH));

    }
}
