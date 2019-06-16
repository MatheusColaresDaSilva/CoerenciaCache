package com.cache.rmiserver;

import java.awt.Component;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.cache.rmiinterface.RMIInterface;
import com.cache.rmiinterface.RMIInterfaceCliente;
import com.cache.tabela.Usuario;

public class Servidor extends UnicastRemoteObject implements RMIInterface{
	
	private List<Usuario> tableUsuario = new ArrayList<Usuario>();
	private static RMIInterfaceCliente look_up_cliente1;
	private static RMIInterfaceCliente look_up_cliente2;
	private static RMIInterfaceCliente look_up_cliente3;

	protected Servidor() throws RemoteException {
		super();
		
		Usuario usuario1 = new Usuario();
		Usuario usuario2 = new Usuario();
		
		usuario1.criarUsuario(1, "Matheus", "999999-9");
		usuario2.criarUsuario(2, "Frank", "888888-8");
		
		tableUsuario.add(usuario1);
		tableUsuario.add(usuario2);
		
		imprimeTabela();
	}

	public static void main (String[] args) {
		
		try {
					
			Naming.rebind("//localhost:1100/MyServer", new Servidor());            
            System.err.println("Server Ready");
            
        } catch (Exception e) {
        	System.err.println("Server exception: " + e.toString());
          e.printStackTrace();
        }

	}

	public Usuario selectById(Integer id) throws RemoteException {
		
		if(!tableUsuario.isEmpty()) {
			
			for (Usuario usuario : tableUsuario){
				
				if(usuario.getId_usuario() == id) {
					System.out.println("Requisição Aprovada \n");
					return usuario;
				}
			}
		}
		
		System.out.println("Cliente nao existe");
		return null;
	}

	@Override
	public void updateById(Integer id, String novocpf) throws RemoteException, MalformedURLException, NotBoundException {
		
		for (int i = 0; i < tableUsuario.size(); i++) {
			
			   if(tableUsuario.get(i).getId_usuario() == id) {
					tableUsuario.get(i).setCpf_usuario(novocpf);
					System.out.println("Cliente id: " + id+" alteraro \n");
				}
		 }	
		imprimeTabela();
		perguntaMetodo(id,novocpf);
	}

	public void imprimeTabela() {
		
		System.out.println("TABELA CLIENTE");
		for (Usuario usuario : tableUsuario){

			System.out.println(usuario);
		}
	}
	
	public void writeUpdate(Integer id,String novocpf) throws MalformedURLException, RemoteException, NotBoundException{
		
		look_up_cliente1 = (RMIInterfaceCliente) Naming.lookup("//localhost:1101/Cliente1");	
		look_up_cliente2 = (RMIInterfaceCliente) Naming.lookup("//localhost:1102/Cliente2");	
		look_up_cliente3 = (RMIInterfaceCliente) Naming.lookup("//localhost:1099/Cliente3");	
		
		look_up_cliente1.updateCacheById(id,novocpf);
		look_up_cliente2.updateCacheById(id,novocpf);
		look_up_cliente3.updateCacheById(id,novocpf);
	}
	
	public void writeInvalidate() {
		
	}

	public void perguntaMetodo(Integer id,String novocpf) throws MalformedURLException, RemoteException, NotBoundException {
		String[] buttons = { "write-update", "write-invalidate" };

	    int rc = JOptionPane.showOptionDialog(null, "Question ?", "Confirmation", JOptionPane.INFORMATION_MESSAGE, 0, null, buttons, buttons[1]);
	    
	    if(rc == 0) {
	    	writeUpdate(id, novocpf);
	    }
	    else if(rc == 1) {
	    	//writeInvalidate();
	    	System.out.println("writeInvalidate(id)");
	    }
	}
}
