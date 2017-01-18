package br.com.zup.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.zup.model.Model;

public class ModelFormatterType implements IModelType {

    public ModelFormatterType() {
    }

    @Override
    public void add(Model model) {
    	Long id = GenericDAO.contador.incrementAndGet();
        GenericDAO.database.put(id, model);
        GenericDAO.databaseValues.put(id, new ArrayList<Model>());
    }

    @Override
    public boolean remove(Long id) {
        Model remove = GenericDAO.database.remove(id);
        GenericDAO.databaseValues.remove(id);
        if(remove != null){
        	return true;
        }
        return false;
    }

    @Override
    public void update(Long id, Model model) {
        GenericDAO.database.remove(model);
        GenericDAO.database.put(id, model);
        GenericDAO.databaseValues.remove(id);
        GenericDAO.databaseValues.put(id, new ArrayList<Model>());
    }

    @Override
    public Model findById(Long id) {
        return GenericDAO.database.get(id);
    }

    @Override
    public List<Model> listAll() {
        List<Model> formatterList = new ArrayList<>();
        for (@SuppressWarnings("rawtypes")
		Iterator iterator = GenericDAO.database.entrySet().iterator(); iterator.hasNext();) {
            Model model = (Model) iterator.next();
            formatterList.add(model);
        }
        return formatterList;
    }

}
