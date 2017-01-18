package br.com.zup.ws.resource;

import java.io.IOException;
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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

import br.com.zup.dao.ModelAttributeType;
import br.com.zup.model.Model;

@Path("model")
/**
 * Classe de resource de attribute.
 * 
 * @author Lucas
 *
 */
public class GenericResource {

	/**
	 * Busca um modelo através do id
	 * 
	 * @param id
	 * @return modelo selecionado.
	 */
	@GET
	@Path("{modelName}/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") long id, @PathParam("modelName") String modelName){
		Model attribute = new ModelAttributeType(modelName).findById(id);
		if (attribute != null) {
			return Response.status(200).entity(attribute).build();
		} else {
			return Response.status(404).entity("Modelo não encontrado").build();
		}
	}

	/**
	 * Busca todos os modelos de atributo de um modelo.
	 * 
	 * @return lista de modelos.
	 */
	@GET
	@Path("{modelName}/list")
	@Produces(MediaType.TEXT_XML)
	public Response listAll(@PathParam("modelName") String modelName) {
		List<Model> modelList = new ModelAttributeType(modelName).listAll();

		if (modelList != null) {
			GenericEntity<List<Model>> models = new GenericEntity<List<Model>>(modelList) {
			};

			if (modelList.size() >= 1) {
				return Response.status(200).entity(models).build();
			} else {
				return Response.status(404).entity("Nenhum modelo encontrado.").build();
			}
		}
		return Response.status(404).entity("Nenhum formatador modelo encontrado para este nome.").build();
	}

	/**
	 * Adiciona um novo .
	 * 
	 * @param model
	 * @return resposta da solitação.
	 */
	@POST
	@Path("{modelName}")
	public Response add(Model model, @PathParam("modelName") String modelName) {
		new ModelAttributeType(model.getModelName()).add(model);

		URI uri = URI.create("/" + modelName + "/" + model.getId());
		return Response.created(uri).build();
	}

	/**
	 * Remove um modelo através do id.
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@DELETE
	@Path("{modelName}/{id:[0-9][0-9]*}")
	public Response delete(@PathParam("id") long id, @PathParam("modelName") String modelName) throws Exception {
		boolean isRemoved = new ModelAttributeType(modelName).remove(id);
		if (isRemoved)
			return Response.ok().build();
		else
			throw new Exception("O modelo nao pode ser removido.");
	}

	/**
	 * Edita modelo de atributos.
	 *
	 * @param model
	 * @param id
	 * @return resposta da solicitação.
	 */
	@PUT
	@Path("edit/{modelName}/{id:[0-9][0-9]*}")
	@Produces(MediaType.TEXT_XML)
	public Response updateAttributeModel(Model model, @PathParam("id") long id,
			@PathParam("modelName") String modelName) {
		new ModelAttributeType(modelName).update(id, model);
		return Response.ok().build();
	}

}
