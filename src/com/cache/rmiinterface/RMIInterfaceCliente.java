package com.cache.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterfaceCliente extends Remote {
	public void selectCacheById(Integer id) throws RemoteException;
	
	public void updateCacheById(Integer id,String novocpf) throws RemoteException;
	
}