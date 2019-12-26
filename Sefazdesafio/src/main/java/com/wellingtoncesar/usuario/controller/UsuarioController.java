package com.wellingtoncesar.usuario.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
 
import org.apache.commons.lang3.StringUtils;

import com.wellingtoncesar.model.PessoaModel;
import com.wellingtoncesar.repository.PessoaRepository;
import com.wellingtoncesar.repository.entity.PessoaEntity;
import com.wellingtoncesar.uteis.Uteis;
 
@Named(value="usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	@Inject
	transient private PessoaModel pessoaModel;
 
	@Inject
	transient private PessoaRepository pessoaRepository;
	
	@Inject
	transient private PessoaEntity pessoaEntity;
	
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}
 
	public PessoaModel GetUsuarioSession(){
 
		FacesContext facesContext = FacesContext.getCurrentInstance();
 
		return (PessoaModel)facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}
 
	public String Logout(){
 
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
 
		return "/index.xhtml?faces-redirect=true";
	}
	public String EfetuarLogin(){
 
		if(StringUtils.isEmpty(pessoaModel.getUsuario()) || StringUtils.isBlank(pessoaModel.getUsuario())){
 
			Uteis.Mensagem("Favor informar o login!");
			return null;
		}
		else if(StringUtils.isEmpty(pessoaModel.getSenha()) || StringUtils.isBlank(pessoaModel.getSenha())){
 
			Uteis.Mensagem("Favor informara senha!");
			return null;
		}
		else{	
 
			pessoaEntity = pessoaRepository.ValidaUsuario(pessoaModel);
 
			if(pessoaEntity!= null){
 
				pessoaModel.setSenha(null);
				pessoaModel.setCodigo(pessoaEntity.getCodigo());
 
 
				FacesContext facesContext = FacesContext.getCurrentInstance();
 
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", pessoaModel);
 
 
				return "sistema/home?faces-redirect=true";
			}
			else{
 
				Uteis.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}
 
 
	}
 
}