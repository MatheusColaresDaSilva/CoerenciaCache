package com.cache.rmiclient;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.cache.rmiinterface.RMIInterface;
import com.cache.rmiinterface.RMIInterfaceCliente;
import com.cache.tabela.Usuario;

public class Cliente1 extends UnicastRemoteObject implements RMIInterfaceCliente, Serializable{
	protected Cliente1() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static RMIInterface look_up;
	static List<Usuario> cacheCliente1 = new ArrayList<Usuario>();
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		

		look_up = (RMIInterface) Naming.lookup("//localhost:1100/MyServer"); //Servidor
        Naming.rebind("//localhost:1101/Cliente1", new Cliente1());
		
		int opcao = 0;
		int id_desejado = 0;
		
		do {
			System.out.println("\n\n### COERENCIA DE CACHE ###");
			System.out.println("\n                  =========================");
			System.out.println("                  |     1 - Selecionar    |");
			System.out.println("                  |     2 - Alterar CPF   |");
			System.out.println("                  |     3 - Imprime Cache |");
			System.out.println("                  =========================\n");

			Scanner in = new Scanner(System.in); 
			opcao = in.nextInt();
			
			System.out.print("\n");
			
			switch (opcao) {
				case 1:
					System.out.println("Selecione um ID: ");
					id_desejado = in.nextInt();
					
					if(validaCache(id_desejado)) {
						
					}
					else {
						Usuario usuarioaux = new Usuario();
						
						usuarioaux = look_up.selectById(id_desejado);
						if(!(usuarioaux== null)) {
							
							cacheCliente1.add(usuarioaux);
						}
						
					}
					
				break;
			
				case 2:
					System.out.println("Selecione um ID: ");
					id_desejado = in.nextInt();

					Usuario usuarioaux = new Usuario();
					usuarioaux = look_up.selectById(id_desejado);
					
					if(usuarioaux != null) {
						
						System.out.println("NOVO CPF DO CLIENTE "+ usuarioaux.getNome_usuario() +": ");
						String novocpf;
						novocpf = in.next();
						look_up.updateById(id_desejado, novocpf);
					}
										
				break;
				
				case 3:
					imprimiCache();
				break;
				
				default:
					System.out.println("Opção Inválida!");
				break;
			
			}
			
		} while (opcao != 0);
		
	}
	
	public static boolean validaCache(int id) {

		for (Usuario usuario : cacheCliente1){
			if(usuario.getId_usuario() == id) {
				System.out.println("Cliente possui na chache.\n");
				return true;
			}
		}
		
		return false;
	}
	
	public static void imprimiCache() {
		
		System.out.println("Cache Cliente 1:");
		
		if(cacheCliente1.isEmpty()) {
			System.out.println("Vazia");
			return;
			};
		
		for (Usuario usuario : cacheCliente1){
			System.out.println(usuario);
		}
	}

	@Override
	public void selectCacheById(Integer id) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCacheById(Integer id,String novocpf) throws RemoteException {

		for (int i = 0 ; i < cacheCliente1.size(); i++) {	
			   if(cacheCliente1.get(i).getId_usuario() == id) {
				   cacheCliente1.get(i).setCpf_usuario(novocpf);

				}
			}
		
	}

}
