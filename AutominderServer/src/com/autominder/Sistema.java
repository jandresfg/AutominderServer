package com.autominder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Sistema implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8434543257264651269L;

	public static final String file = "AutominderServer.dat";

	private ServerSocket socket;
	private static final int PUERTO = 9999;
	
	private ArrayList<Principal> instancias;

	private int conexiones;
	
	
	public Sistema() {

		loadState();

		if (instancias==null) {
			instancias = new ArrayList<Principal>();
		}
		saveState();
		
	}

	
	@SuppressWarnings("unchecked")
	public void loadState(){
		try {
			File f = new File(file);
			f.createNewFile();
			FileInputStream fis =  new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			instancias = (ArrayList<Principal>) ois.readObject();
			System.out.println("cargados "+instancias.size()+" instancias");
			ois.close();
			fis.close();
		} catch (FileNotFoundException e1) {
			System.out.println("file not found encontrada");
		} catch (IOException e) {
			System.out.println("IOException encontrada");
		} catch (ClassNotFoundException e) {
			System.out.println("Class no found encontrada");
		}	
	}
	
	public void saveState(){
		try {
			File f = new File(file);
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(instancias);
			oos.close();
			fos.close();	
			System.out.println("Saved "+instancias.size()+" instances.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		Sistema nuevo = new Sistema();
		nuevo.recibirConexiones();
	}
	
	/**
	 * Método encargado de esperar y recibir conexiones y generar nuevas clases Conexion por cada unión cliente-servidor realizada
	 */
	public void recibirConexiones(){
		try{
			socket = new ServerSocket(PUERTO);
			while (true){
				//Esperar conexión
				Socket socketCliente = socket.accept();
				conexiones ++;
				System.out.println("Inicio de Conexión: Conexión "+ conexiones);
				PrintWriter out = new PrintWriter (socketCliente.getOutputStream(), true);
				BufferedReader in = new BufferedReader ( new InputStreamReader(socketCliente.getInputStream()));
				//Creador Hilo de conexion
				ConexionServer conexion = new ConexionServer(in, out, this);
				conexion.start();
			}
		}
		catch( IOException e){
			e.printStackTrace();
		}
	}


	public boolean conectar(String username, String password) {
		for (int i = 0; i < instancias.size(); i++) {
			Principal p = instancias.get(i);
			if(p.getUsername().equals(username) && p.getPassword().equals(password))return true;
		}
		return false;
	}


	public void registro(String username, String password) {
		Principal p = new Principal(username, password);
		instancias.add(p);
		saveState();
	}


	public Principal darInstancia(String username, String password) {
		for (int i = 0; i < instancias.size(); i++) {
			Principal p = instancias.get(i);
			if(p.getUsername().equals(username) && p.getPassword().equals(password))return p;
		}
		return null;
	}


	public void restarConexion() {
		conexiones--;
		System.out.println("conexiones:"+conexiones);
	}
	
}
