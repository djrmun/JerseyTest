/**
 * 
 */
package com.akkineni.rest.stax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.akkineni.rest.util.DateTimeUtil;
import com.akkineni.schema.so.ServiceOrderDTO;

/**
 * This class is to parse the incoming document for the java type
 * ServiceOrderSearchDTO and generate a Java Object
 * 
 * @author lokesh
 * 
 */
public class ServiceOrderDTOStaxParser {

	public List<ServiceOrderDTO> parseServiceOrderDTO(InputStream is) {
		XMLStreamReader xmlStreamReader = null;
		ServiceOrderDTO dto = null;
		List<ServiceOrderDTO> serviceOrderDTOList = new ArrayList<ServiceOrderDTO>();
		try {
			XMLInputFactory factory = XMLInputFactory.newInstance();
			xmlStreamReader = factory.createXMLStreamReader(is);
			try {
				while (xmlStreamReader.hasNext()) {
					int event = xmlStreamReader.next();
					if (event == XMLStreamConstants.START_DOCUMENT) {
					}
					if (event == XMLStreamConstants.START_ELEMENT) {
						if (xmlStreamReader.getLocalName() == "ServiceOrderDTO") {
							dto = new ServiceOrderDTO();
						}
						if (xmlStreamReader.getLocalName() == "orderNumber") {
							dto.setOrderNumber(xmlStreamReader.getElementText());
						}
						if (xmlStreamReader.getLocalName() == "version") {
							dto.setVersion(Long.parseLong(xmlStreamReader
									.getElementText()));
						}
						if (xmlStreamReader.getLocalName() == "flowableID") {
							dto.setFlowableID(Long.parseLong(xmlStreamReader
									.getElementText()));
						}
						if (xmlStreamReader.getLocalName() == "description") {
							dto.setDescription(xmlStreamReader.getElementText());
						}
						if (xmlStreamReader.getLocalName() == "creationDate") {
							DateTimeUtil util = new DateTimeUtil();
							XMLGregorianCalendar xgc = util
									.convertXmlTextToDate(xmlStreamReader
											.getElementText());
							dto.setCreationDate(xgc);
							serviceOrderDTOList.add(dto);
						}
					}
					if (event == XMLStreamConstants.ATTRIBUTE) {
					}
					if (event == XMLStreamConstants.CHARACTERS) {
					}
					if (event == XMLStreamConstants.COMMENT) {
					}
					if (event == XMLStreamConstants.END_DOCUMENT) {
					}
					if (event == XMLStreamConstants.END_ELEMENT) {
					}
					if (event == XMLStreamConstants.NAMESPACE) {
					}
					if (event == XMLStreamConstants.PROCESSING_INSTRUCTION) {
					}
					if (event == XMLStreamConstants.SPACE) {
					}
				}
			} catch (Throwable t) {
				t.printStackTrace();
			} finally {
				xmlStreamReader.close();
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return serviceOrderDTOList;
	}

	public static void main(String[] args) {
		File file = new File("input.xml");
		try {
			FileInputStream fis = new FileInputStream(file);
			ServiceOrderDTOStaxParser parser = new ServiceOrderDTOStaxParser();
			parser.parseServiceOrderDTO(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}