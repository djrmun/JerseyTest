package com.akkineni.rest.util;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateTimeUtil {

	public DateTimeUtil() {
		super();
	}

	public XMLGregorianCalendar getDate(GregorianCalendar gregCal) {
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(
					gregCal);
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	public XMLGregorianCalendar getDate() {
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(
					new GregorianCalendar());
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	public XMLGregorianCalendar convertXmlTextToDate(String dateText) {
		XMLGregorianCalendar xgc = null;
		GregorianCalendar gregCal = null;
		DateTimeFormatter XML_DATE_TIME_FORMAT = ISODateTimeFormat.dateTime();
		DateTime date = XML_DATE_TIME_FORMAT
				.parseDateTime("2011-06-11T12:12:41.107-04:00");
		if (date != null)
			gregCal = date.toGregorianCalendar();
		if (gregCal != null)
			xgc = getDate(gregCal);
		return xgc;
	}
}