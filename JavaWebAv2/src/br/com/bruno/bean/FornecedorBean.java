package br.com.bruno.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.bruno.DAO.DAO;
import br.com.bruno.modelo.Endereco;
import br.com.bruno.modelo.Fornecedor;

@ManagedBean
@ViewScoped
public class FornecedorBean {

	private Fornecedor fornecedor = new Fornecedor();	
	private Integer fornecedorId;
	
	public Integer getFornecedorId(){
		return fornecedorId;
	}
	
	public void setFornecedorId(Integer fornecedorId){
		this.fornecedorId = fornecedorId;
	}
	
	public Fornecedor getFornecedor(){
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor){
		this.fornecedor = fornecedor;
	}
	
	public List<Fornecedor> getFornecedores(){
		return new DAO<Fornecedor>(Fornecedor.class).listar();
	}
	
	public String salvar(){
		new DAO<Endereco>(Endereco.class).adicionar(fornecedor.getEndereco());		
		new DAO<Fornecedor>(Fornecedor.class).adicionar(this.fornecedor);
		this.fornecedor = new Fornecedor();
		
		return "fornecedor.xhtml";
	}
}
