package br.com.zup.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.zup.model.Model;

public class ModelAttributeType implements IModelType {

	private String formatterName;

	public ModelAttributeType(String formatterName) {
		this.formatterName = formatterName;
	}

	/**
	 * Adiciona um novo modelo.
	 */
	@Override
	public void add(Model model) {
		Long id = findFormatterIdByName();
		if (id != null)
			GenericDAO.databaseValues.get(id).add(model);
	}

	/**
	 * Remove o pedido com id especificado.
	 */
	@Override
	public boolean remove(Long id) {
		Long formatterId = findFormatterIdByName();
		if (formatterId != null) {
			List<Model> modelList = GenericDAO.databaseValues.get(formatterId);

			for (@SuppressWarnings("rawtypes")
			Iterator iterator = modelList.iterator(); iterator.hasNext();) {
				Model model = (Model) iterator.next();
				if (model.getId() == id) {
					iterator.remove();
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Faz o update de arquivo.
	 */
	@Override
	public void update(Long id, Model model) {
		Long formatterId = findFormatterIdByName();
		if (formatterId != null) {
			List<Model> modelList = GenericDAO.databaseValues.get(formatterId);

			for (@SuppressWarnings("rawtypes")
			Iterator iterator = modelList.iterator(); iterator.hasNext();) {
				Model m = (Model) iterator.next();
				if (m.getId() == model.getId()) {
					iterator.remove();
					break;
				}
			}
			modelList.add(model);
		}
	}

	/**
	 * Busca um modelo de atributos pelo id
	 */
	@Override
	public Model findById(Long id) {
		for (Long key : GenericDAO.databaseValues.keySet()) {

			if (id == key) {
				for (Model model : GenericDAO.databaseValues.get(key)) {
					if (model.getId() == id) {
						return model;
					}
				}
			}

		}
		return null;
	}

	/**
	 * Lista todos os modelos de atributo de um formato específico.
	 */
	@Override
	public List<Model> listAll() {
		Long formatterId = findFormatterIdByName();
		if(formatterId != null){
			return GenericDAO.databaseValues.get(formatterId);	
		}
		return null;
	}

	/**
	 * Encontra o id do modelo através do nome do mesmo.
	 * 
	 * @return
	 * @throws Exception
	 */
	private Long findFormatterIdByName() {

		for (Map.Entry<Long, Model> formatter : GenericDAO.database.entrySet()) {
			if (formatter.getValue().getModelName().equalsIgnoreCase(formatterName)) {
				return formatter.getKey();
			}
		}

		return null;
	}

}
