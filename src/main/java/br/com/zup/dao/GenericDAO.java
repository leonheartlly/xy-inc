package br.com.zup.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.zup.model.Model;

/**
 * Classe apenas simbólica simulando uma carga inicial de testes e um banco de
 * dados.
 * 
 * @author lucas.silva
 *
 */
public abstract class GenericDAO {

	/**
	 * Banco de dados
	 */
	protected static Map<Long, Model> database = new HashMap<Long, Model>();
	protected static Map<Long, List<Model>> databaseValues = new HashMap<Long, List<Model>>();
	protected static AtomicLong contador = new AtomicLong(1);

	/**
	 * Carga inicial do banco de dados.
	 */
	static {
		/**
		 * Padrao de projeto Builder
		 */
		Model modelFormatter = new Model("Pessoa", contador.longValue());
		modelFormatter.addAttribute("nome", "String")
				.addAttribute("cidade", "String")
				.addAttribute("rua", "String")
				.addAttribute("numero", "int");
		database.put(contador.longValue(), modelFormatter);

		List<Model> modelList = new ArrayList<>();
		Model model = new Model(modelFormatter.getModelName(), modelFormatter.getId());

		model.addAttribute("nome", "lucas")
			 .addAttribute("cidade", "uberlandia")
		     .addAttribute("rua", "Rua dos webservices")
		     .addAttribute("numero", "123");
		
		modelList.add(model);
		databaseValues.put(contador.longValue(), modelList);

	}
}
