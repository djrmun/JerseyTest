package com.akkineni.rest.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

public class XMLStreamingOutput implements StreamingOutput {

	private Object xmlType;

	public Object getXmlType() {
		return xmlType;
	}

	public void setXmlType(Object xmlType) {
		this.xmlType = xmlType;
	}

	@Override
	public void write(OutputStream output) throws IOException,
			WebApplicationException {
		//output.write();
	}

}
