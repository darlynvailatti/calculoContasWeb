package br.edu.unoesc.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class DataUtil {
	
	public static final LocalDate dateToLocalDate(Date d){
		LocalDate localDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return localDate;
	}
	
	public static final Date localDateToDate(LocalDate d){
		Instant instant = d.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		Date res = Date.from(instant);
		return res;
	}
}
