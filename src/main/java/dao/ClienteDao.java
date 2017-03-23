package dao;

import java.util.List;

import model.Cliente;
import util.HibernateUtil;

public class ClienteDao  extends HibernateUtil{

	@SuppressWarnings("unchecked")
	public List<Cliente> findAll(){
		try{
			return super.getSessionFactory().createCriteria(Cliente.class).list();	
		}
		finally {
			super.shutdown();
		}
	}
	
}
