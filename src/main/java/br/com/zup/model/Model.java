package br.com.zup.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;

/**
 * Classe Modelo usando o padrão de projetos 'Builder'. Para tal os métodos de
 * interação com a classe retornam ela mesma.
 * 
 * @author Lucas
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Model {

	private String modelName;
	private Long id;
    private Map<String, String> attributes = new HashMap<>();
	
	public Model(String modelName, Long id) {
		this.modelName = modelName;
		this.id = id;
	}
	
	public Model() {
		
	}

	public String getModelName() {
		return modelName;
	}

	public Model setModelName(String modelName) {
		this.modelName = modelName;
		return this;
	}

    public Map<String, String> getAttributes() {
		return attributes;
	}

    public Model addAttribute(String attrName, String attrType) {
        attributes.put(attrName, attrType);
        return this;
	}
    
    public Long getId() {
		return id;
	}

	/**
	 * Converte o objeto da classe para json.
	 * 
	 * @return json
	 */
	public String toJson() {
		return new Gson().toJson(this);
	}

}
