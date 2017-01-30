package br.com.victor.famintos.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Victor H. Colombo
 * @since 28/01/2017
 */
public class DataUteis {
	/**
	 * Mascara dd/MM/yyyy
	 */
	public static final String DIAMESANO = "dd/MM/yyyy";

	/**
	 * Seta o hor·rio do calendar para a maior hora do dia.
	 * 
	 * @param cal
	 */
	public static void maximizeTime(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
		cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
	}

	/**
	 * Seta o hor·rio do calendar para a maior hora do dia.
	 * 
	 * @param cal
	 * @return a data ou null caso o par√¢metro informado seja nulo
	 */
	public static Date getMaximizedTime(Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		maximizeTime(cal);
		return cal.getTime();
	}

	/**
	 * Seta o hor·rio do calendar para a menor hora do dia.
	 * 
	 * @param cal
	 */
	public static void minimizeTime(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
	}

	/**
	 * Seta o hor·rio do calendar para a menor hora do dia.
	 * 
	 * @param cal
	 * @return a data ou null caso o par√¢metro informado seja nulo
	 */
	public static Date getMinimizedTime(Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		minimizeTime(cal);
		return cal.getTime();
	}

	/**
	 * O retorno e dependente da mascara informada.
	 * 
	 * @param date
	 * @param mascara
	 * @return String
	 */
	public static String formateDate(Date date, String mascara) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(mascara);
		return format.format(date);
	}

	/**
	 * Retorna a lista de dias da semana
	 * 
	 * @param dia
	 * @return
	 */
	public static List<Date> listaSemana(Date dia) {
		List<Date> semana = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dia);
		int domingo = cal.get(Calendar.DAY_OF_WEEK) - 1;
		cal.add(Calendar.DAY_OF_MONTH, -domingo);
		int x = (cal.get(Calendar.DAY_OF_MONTH)) + 7;
		for (int i = cal.get(Calendar.DAY_OF_MONTH); i < x; i++) {
			semana.add(cal.getTime());
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		return semana;
	}
}
