package com.cache.rmiinterface;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import com.cache.tabela.Usuario;


public interface RMIInterface extends Remote {
	public Usuario selectById(Integer id) throws RemoteException;
	
	public void updateById(Integer id,String novocpf) throws RemoteException, MalformedURLException, NotBoundException;
		
	public void imprimeTabela() throws RemoteException;
	
}