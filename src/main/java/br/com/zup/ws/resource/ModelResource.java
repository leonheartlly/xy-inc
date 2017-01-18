package br.com.zup.ws.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.zup.dao.ModelFormatterType;
import br.com.zup.model.Model;

@Path("model")

/**
 * Classe de resource de Pedido.
 * 
 * @author Lucas
 *
 */
public class ModelResource {

	/**
	 * Busca um modelo através do id.
	 * 
	 * @param id
	 * @return pedido selecionado.
	 */
	@Path("/{id:[0-9][0-9]*}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response findById(@PathParam("id") long id) {
		Model formatter = new ModelFormatterType().findById(id);
		return Response.status(200).entity(formatter).build();
	}

	/**
	 * Busca todos os modelos.
	 * 
	 * @return lista de modelos.
	 */
	@Path("list")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Response listAll() {
		List<Model> modelList = new ModelFormatterType().listAll();

		GenericEntity<List<Model>> modelos = new GenericEntity<List<Model>>(modelList) {
		};
		if (modelList.size() >= 1) {
			return Response.status(200).entity(modelos).build();
		} else {
			return Response.status(404).entity("Nenhum modelo encontrado.").build();
		}
	}

	/**
	 * Adiciona um novo modelo.
	 *
	 * @param model
	 * @return resposta da solitação.
	 */
	@POST
	public Response add(Model model) {
		new ModelFormatterType().add(model);
		URI uri = URI.create("model/" + model.getId());
		return Response.created(uri).build();
	}

	/**
	 * Remove um modelo através do id.
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Path("/{id:[0-9][0-9]*}")
	@DELETE
	public Response delete(@PathParam("id") long id) throws Exception {
		boolean isRemoved = new ModelFormatterType().remove(id);
		if (isRemoved)
			return Response.ok("Modelo removido.").build();
		else
			throw new Exception("O modelo nao pode ser removido.");
	}

	/**
	 * Edita um modelo.
	 *
	 * @param model
	 * @param id
	 * @return resposta da solicitação.
	 */
	@Path("/edit/{id}")
	@PUT
	public Response updateModel(Model model, @PathParam("id") long id) {
		new ModelFormatterType().update(id, model);
		return Response.ok("Modelo editado com sucesso. Os modelos de Atributo existentes de modelo foram removidos devido a inconsistencia de formato.").build();
	}

}
