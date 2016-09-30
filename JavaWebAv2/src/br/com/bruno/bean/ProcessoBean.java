package br.com.bruno.bean;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.bruno.DAO.DAO;
import br.com.bruno.modelo.Fornecedor;
import br.com.bruno.modelo.Processo;

@ManagedBean
@SessionScoped
public class ProcessoBean {

	private Processo processo;	
	private Integer processoId;	
	private boolean editar;
	private boolean podeInserir;
	
	public Integer getProcessoId() {
		return processoId;
	}
	
	public void setProcessoId(Integer processoId){
		this.processoId = processoId;
	}
	
	public Processo getProcesso(){
		
		if(processo == null)
		{
			return new Processo();
		}
		
		return processo;
	}
	
	public void setProcesso(Processo processo){
		this.processo = processo;
	}
	
	public List<Processo> getProcessos(){
		return new DAO<Processo>(Processo.class).listar();
	}
	
	public boolean getEditar(){
		return editar;
	}
	
	public String editarProcesso(boolean editar, Processo processo){
		if(processo == null)
		{
			this.processo = new Processo();
		}
		else
		{
			this.processo = processo;
		}
		
		this.editar = editar;
		
		return "cadastro-processo.xhtml?faces-redirect=true";
	}
	
	public boolean getPodeInserir(){
		return podeInserir;
	}
	
	public void setPodeInserir(boolean podeInserir){
		this.podeInserir = podeInserir;
	}
	public void pesquisarFornecedor(String cnpj){
		Fornecedor f = new DAO<Fornecedor>(Fornecedor.class).buscarCnpj(cnpj);
		
		if(f != null)
		{
			this.processo.setFornecedor(f);
			podeInserir = true;
		}
		else
		{
			this.processo.setFornecedor(new Fornecedor());
			podeInserir = false;
		}
	}
	public void deletarProcesso(Processo processo){
		addMessage("Ação concluída", "Processo de número: " + processo.getNumeroProcesso() + ", foi excluido!");
		
		new DAO<Processo>(Processo.class).remover(processo);
	}
	
	public String salvar(){		
		if(processo.getId() != null)
			new DAO<Processo>(Processo.class).atualizar(processo, processo.getFornecedor().getCnpj());
		else{
			Date data = new Date();
			
			processo.getDataRelato().setTime(data.getTime());
			new DAO<Processo>(Processo.class).adicionar(processo, processo.getFornecedor().getCnpj());
		}
		
		podeInserir = false;
		
		return "processo.xhtml";
	}
		
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
}
