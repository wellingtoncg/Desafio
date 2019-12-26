//package com.wellingtoncesar.repository;
//
//import java.io.Serializable;
//
//import javax.persistence.Query;
// 
//import com.wellingtoncesar.model.UsuarioModel;
//import com.wellingtoncesar.repository.entity.UsuarioEntity;
//import com.wellingtoncesar.repository.entity.PessoaEntity;
//
//import com.wellingtoncesar.uteis.Uteis;
// 
// 
//public class UsuarioRepository implements Serializable {
// 
// 
//	private static final long serialVersionUID = 1L;
// 
//	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel){
// 
//		try {
// 
//			//QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser) 	
//			Query query = Uteis.JpaEntityManager().createNamedQuery("PessoaEntity.findUser");
// 
//			//PARÂMETROS DA QUERY
//			query.setParameter("usuario", usuarioModel.getUsuario());
//			query.setParameter("senha", usuarioModel.getSenha());
// 
//			//RETORNA O USUÁRIO SE FOR LOCALIZADO
//			return (UsuarioEntity)query.getSingleResult();
// 
//		} catch (Exception e) {
// 
//			return null;
//		}
// 
// 
// 
//	}
//}
