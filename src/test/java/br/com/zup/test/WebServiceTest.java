package br.com.zup.test;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.zup.model.Model;

public class WebServiceTest {

	private HttpServer server;
	private WebTarget target;
	private Client client;
	private Model model;

	/**
	 * Inicia o servidor antes do teste.
	 */
	@Before
	public void initServer() {
		addNewModel();

		server = Server.startServer();

		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());

		this.client = ClientBuilder.newClient(config);
		this.target = client.target("http://localhost:8080");
	}

	private void addNewModel() {
		model = new Model("pedido", 2l);

		model.addAttribute("codigo", "Long")
		     .addAttribute("vendedor", "String")
		     .addAttribute("valorTotal", "double");
	}

	/**
	 * Finaliza o servidor após a conclusão do teste.
	 */
	@After
	public void finishServidor() {
		server.shutdownNow();
	}

	@Test
	public void testGET() throws JAXBException {

		Model pedido = findModel();

		Assert.assertEquals("Pessoa", pedido.getModelName());
	}

	private Model findModel() throws JAXBException {

		return target.path("model/1").request().get(Model.class);
	}

	@Test
	public void testListGET() {
		Response response = target.path("model/pessoa/list").request().get();
		List<Model> list = response.readEntity(new GenericType<List<Model>>() {
		});

		Assert.assertTrue(list.size() >= 1);
	}

	@Test
	public void testPOST() {

		Entity<Model> entity = Entity.entity(model, MediaType.APPLICATION_XML);

		Response response = target.path("/model").request().post(entity);
		Assert.assertEquals(201, response.getStatus());

		String location = response.getHeaderString("Location");
		Model pedidoInserido = client.target(location).request().get(Model.class);

		Assert.assertTrue(pedidoInserido.getAttributes().size() > 0);
		Assert.assertEquals("pedido", pedidoInserido.getModelName());
	}

	@Test
	public void testDELETE() throws JAXBException {

		Response response = target.path("model/1").request().delete();
		Assert.assertEquals(200, response.getStatus());

		Model model = findModel();
		Assert.assertTrue(model == null);

		// linha abaixo não roda devido a erro na versão do grizzly
		// pedido.getProdutos().forEach(p -> System.out.println(p.getNome()));
	}

	@Test
	public void testPUT() throws JAXBException {
		Model model = findModel();
		model.setModelName("Produto");
		Entity<Model> entity = Entity.entity(model, MediaType.APPLICATION_XML);
		Response response = target.path("model/edit/1").request().put(entity);
		Assert.assertEquals(200, response.getStatus());

		Assert.assertTrue(model.getModelName().equalsIgnoreCase(findModel().getModelName()));
	}

}
