package com.akkineni.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.ext.Providers;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.akkineni.rest.domain.Customer;
import com.akkineni.rest.stax.ServiceOrderDTOStaxParser;
import com.akkineni.rest.util.StaxParserHelper;
import com.akkineni.schema.so.ServiceOrderDTO;
import com.akkineni.schema.so.ServiceOrderSearch;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.syndication.feed.atom.Feed;

@Path("/customers")
public class CustomerResource {

	private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();
	private AtomicInteger idCounter = new AtomicInteger();

	private final Logger LOGGER = Logger.getLogger(CustomerResource.class
			.getName());

	@Context
	Providers providers;

	@GET
	@Path("/echo")
	@Produces("text/plain")
	public String getClichedMessage() {
		LOGGER.fine("getClichedMessage");
		return "Hello World";
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Customer getCustomer(@PathParam("id") int id) {
		final Customer customer = customerDB.get(id);
		if (customer == null) {
			throw new WebApplicationException(
					Response.Status.SERVICE_UNAVAILABLE);
		}
		return customer;
	}

	@GET
	@Path("/list")
	@Produces("application/xml")
	public List<Customer> getCustomerList() {
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		for (int i : customerDB.keySet()) {
			final Customer customer = customerDB.get(i);
			customerList.add(customer);
		}
		if (customerList.size() == 0) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return customerList;
	}

	@POST
	@Path("/create")
	@Consumes("application/xml")
	public Response createCustomer(Customer customer) {
		// Customer customer = readDOMParser(is);
		customer.setId(idCounter.incrementAndGet());
		customerDB.put(customer.getId(), customer);
		System.out.println("Created customer " + customer.getId());
		return Response.created(URI.create("/customers/" + customer.getId()))
				.build();
	}

	@POST
	@Path("/create/list")
	@Consumes("application/xml")
	public Response createCustomer(InputStream is) {
		List<Customer> customerList = StaxParserHelper.createCustomerList(is);
		for (Customer cust : customerList) {
			cust.setId(idCounter.incrementAndGet());
			customerDB.put(cust.getId(), cust);
		}
		return Response.created(URI.create("/customers/list")).build();
	}

	@PUT
	@Path("{id}")
	@Consumes("application/xml")
	public void updateCustomer(@PathParam("id") int id, InputStream is) {
		Customer update = StaxParserHelper.createCustomerFromInputStream(is);
		Customer current = customerDB.get(id);
		if (current == null)
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		current.setFirstName(update.getFirstName());
		current.setLastName(update.getLastName());
		current.setStreet(update.getStreet());
		current.setState(update.getState());
		current.setZip(update.getZip());
		current.setCountry(update.getCountry());
	}

	@GET
	@Path("/ServiceOrderSearch")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ServiceOrderSearch ServiceOrderSearch() {

		ServiceOrderSearch sos = new ServiceOrderSearch();

		for (int i = 0; i < 10; i++) {
			ServiceOrderDTO dto = new ServiceOrderDTO();
			dto.setCreationDate(getDate());
			dto.setDescription("Service Order Description");
			dto.setFlowableID(i);
			dto.setOrderNumber("1234" + i);
			dto.setVersion(1);

			sos.getServiceOrderDTO().add(dto);
		}

		return sos;
	}

	@GET
	@Path("/JsonStreamTest")
	@Produces({ MediaType.APPLICATION_JSON })
	public StreamingOutput StreamTest() {

		final ServiceOrderSearch sos = new ServiceOrderSearch();

		for (int i = 0; i < 10; i++) {
			ServiceOrderDTO dto = new ServiceOrderDTO();
			dto.setCreationDate(getDate());
			dto.setDescription("Service Order Description");
			dto.setFlowableID(i);
			dto.setOrderNumber("1234" + i);
			dto.setVersion(1);
			sos.getServiceOrderDTO().add(dto);
		}

		final ObjectMapper mapper = new ObjectMapper();

		return new StreamingOutput() {
			@Override
			public void write(OutputStream output) throws IOException,
					WebApplicationException {
				output.write(mapper.writeValueAsBytes(sos));
			}
		};
	}

	@POST
	@Path("/StaxParseTest")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public List<ServiceOrderDTO> StaxParseTest(InputStream is) {
		ServiceOrderDTOStaxParser parser = new ServiceOrderDTOStaxParser();
		List<ServiceOrderDTO> ServiceOrderDTOList = parser
				.parseServiceOrderDTO(is);
		return ServiceOrderDTOList;
	}

	@GET
	@Path("feed")
	@Produces("application/atom+xml")
	public Feed getFeed() throws URISyntaxException {
		// Feed feed = new Feed();
		// feed.setId(new URI("http://example.com/42"));
		// feed.setTitle("My Feed");
		// feed.setUpdated(new Date());
		// Link link = new Link();
		// link.setHref(new URI("http://localhost"));
		// link.setRel("edit");
		// feed.getLinks().add(link);
		// feed.getAuthors().add(new Person("Bill Burke"));
		// Entry entry = new Entry();
		// entry.setTitle("Hello World");
		// Content content = new Content();
		// content.setType(MediaType.TEXT_HTML_TYPE);
		// content.setText("Nothing much");
		// entry.setContent(content);
		// feed.getEntries().add(entry);
		return null;
	}

	private static XMLGregorianCalendar getDate() {
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(
					new GregorianCalendar());
		} catch (DatatypeConfigurationException e) {
			throw new Error(e);
		}
	}

}