package com.wellingtoncesar.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
 
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
 
import com.wellingtoncesar.model.PessoaModel;
import com.wellingtoncesar.repository.entity.PessoaEntity;
import com.wellingtoncesar.uteis.Uteis;
 
public class PessoaRepository {
 
	@Inject
	PessoaEntity pessoaEntity;
 
	EntityManager entityManager;
 
	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA PESSOA
	 * @param pessoaModel
	 */
	public void SalvarNovoRegistro(PessoaModel pessoaModel){
 
		entityManager =  Uteis.JpaEntityManager();
 
		pessoaEntity = new PessoaEntity();
		pessoaEntity.setUsuario(pessoaModel.getUsuario());
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setSenha(pessoaModel.getSenha());
 
		//UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, pessoaModel.getUsuarioModel().getCodigo()); 
 
		//pessoaEntity.setUsuarioEntity(usuarioEntity);
 
		entityManager.persist(pessoaEntity);
		entityManager.close();
 
	}
 
	/***
	 * MÉTODO PARA CONSULTAR A PESSOA
	 * @return
	 */
	public List<PessoaModel> GetPessoas(){
 
		List<PessoaModel> pessoasModel = new ArrayList<PessoaModel>();
 
		entityManager =  Uteis.JpaEntityManager();
 
		Query query = entityManager.createNamedQuery("PessoaEntity.findAll");
 
		@SuppressWarnings("unchecked")
		Collection<PessoaEntity> pessoasEntity = (Collection<PessoaEntity>)query.getResultList();
 
		PessoaModel pessoaModel = null;
 
		for (PessoaEntity pessoaEntity : pessoasEntity) {
 
			pessoaModel = new PessoaModel();
			pessoaModel.setCodigo(pessoaEntity.getCodigo());
			pessoaModel.setEmail(pessoaEntity.getEmail());
			pessoaModel.setUsuario(pessoaEntity.getUsuario());
			pessoaModel.setNome(pessoaEntity.getNome());
 
			pessoasModel.add(pessoaModel);
		}
 
		return pessoasModel;
 
	}
	
	/***
	 * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
	 * @param codigo
	 * @return
	 */
	private PessoaEntity GetPessoa(int codigo){
 
		entityManager =  Uteis.JpaEntityManager();
 
		return entityManager.find(PessoaEntity.class, codigo);
	}
 
	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * @param pessoaModel
	 */
	public void AlterarRegistro(PessoaModel pessoaModel){
 
		entityManager =  Uteis.JpaEntityManager();
 
		PessoaEntity pessoaEntity = this.GetPessoa(pessoaModel.getCodigo());
 
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setUsuario(pessoaModel.getUsuario());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setSenha(pessoaModel.getSenha());
 
		entityManager.merge(pessoaEntity);
	}
	
	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo){
 
		entityManager =  Uteis.JpaEntityManager();		
 
		PessoaEntity pessoaEntity = this.GetPessoa(codigo);
 
		entityManager.remove(pessoaEntity);
	}
	
	public PessoaEntity ValidaUsuario(PessoaModel pessoaModel){
		 
		try {
 
			//QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser) 	
			Query query = Uteis.JpaEntityManager().createNamedQuery("PessoaEntity.findUser");
 
			//PARÂMETROS DA QUERY
			query.setParameter("usuario", pessoaModel.getUsuario());
			query.setParameter("senha", pessoaModel.getSenha());
 
			//RETORNA O USUÁRIO SE FOR LOCALIZADO
			return (PessoaEntity)query.getSingleResult();
 
		} catch (Exception e) {
 
			return null;
		}
 
 
 
	}
	
	
}