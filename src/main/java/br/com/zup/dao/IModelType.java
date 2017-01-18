package br.com.zup.dao;

import java.util.List;

import br.com.zup.model.Model;

public interface IModelType {

    void add(Model model);

    boolean remove(Long id);

    void update(Long id, Model model);

    Model findById(Long id);

    List<Model> listAll();

}
