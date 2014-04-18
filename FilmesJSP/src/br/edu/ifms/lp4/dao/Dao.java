package br.edu.ifms.lp4.dao;

import java.io.Serializable;

public interface Dao<T> {

	public T salva(T objeto);

	public boolean remove(T objeto);

	public T recupera(Serializable id);

}
