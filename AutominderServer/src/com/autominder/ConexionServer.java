package com.autominder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sun.applet.Main;

/**
 * Clase que modela la conexión del cliente con el servidor como un Thread para manejar
 */
public class ConexionServer extends Thread{

	//----------
	// ATRIBUTOS
	//----------

	/**
	 * Atributo que modela la clase que tiene los comandos del protocolo de comunicación
	 */
	private Protocolo protocolo;

	/**
	 * Atributo que modela el PrintWriter de la conexión
	 */
	private PrintWriter out;

	/**
	 * Atributo que modela el BufferedReader de la conexión
	 */
	private BufferedReader in;

	/**
	 * Atributo que modela el sistema principal con el que se realiza la lógica del mundo
	 */
	private Sistema sistema;


	//----------
	// CONSTRUCTOR
	//----------

	/**
	 * Constructor de la conexión con base en los parámetros enviados desde el servidor
	 * @param in - BufferedReader de la conexion; in != null
	 * @param out - PrintWriter de la conexion; out != null
	 * @param sistema - Sistema principal con el que se realiza la lógica; sistema != null
	 */
	public ConexionServer (BufferedReader in, PrintWriter out, Sistema sistema){
		this.in = in;
		this.out = out;
		this.sistema = sistema;
	}

	//----------
	// MÉTODOS
	//----------

	/**
	 * Metodo run del Thread conexión
	 */
	public void run(){
		try{
			recibirMensajes();
		}
		catch( IOException e){
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Método que modela la recepción y envío de los mensajes de comunicación con el cliente asociado a esta conexión
	 * @throws Exception 
	 */
	private void recibirMensajes() throws Exception{
		System.out.println("recibiendo mensajes");
		boolean conexionTerminada = false;
		String respuesta = null;
		while( !conexionTerminada ){
			String comando = in.readLine();
			System.out.println(comando);
			if(comando.startsWith(Protocolo.AUTENTICAR)){
				//Al entrar acá significa que esta en formato AUT##Username##Password
				String[] datos = comando.split("##");
				String username = datos[1];
				String password = datos[2];
				if(sistema.conectar(username, password)){
					respuesta = "OK";
					out.println(respuesta);
				}else{
					respuesta = "ERR";
					out.println(respuesta);
				}
			}else if (comando.startsWith("REGISTRO")) {
				String[] datos = comando.split("##");
				String username = datos[1];
				String password = datos[2];

				sistema.registro(username, password);

				respuesta = "OK";
				out.println(respuesta);
			}else if (comando.startsWith("DATOSPULL")) {
				String[] datos = comando.split("##");
				String username = datos[1];
				String password = datos[2];

				Principal p = sistema.darInstancia(username, password);

				for (int i = 0; i < p.getVehiculos().size(); i++) {
					Vehicle vAct = p.getVehiculos().get(i);
					respuesta = "VEH##"+vAct.getName()+"##"+vAct.getWeeklyKM()+"##"+vAct.getCurrentKmCount();
					System.out.println(respuesta);
					out.println(respuesta);
					for (int j = 0; j < vAct.getMaintenances().size(); j++) {
						Maintenance mAct = vAct.getMaintenances().get(j);
						respuesta = "MAN##"+mAct.getType()+"##"+mAct.getNombre()+"##"+mAct.getKm()+"##"+mAct.getTiempo();
						System.out.println(respuesta);
						out.println(respuesta);
					}
					for (int j = 0; j < vAct.getRecords().size(); j++) {
						Record rAct = vAct.getRecords().get(j);
						respuesta = "REC##"+rAct.getCost()+"##"+rAct.getNombreTaller()+"##"+rAct.getKmPassedSince()+"##"+rAct.getMaintenanceName()+"##"+new SimpleDateFormat("dd-MM-yyyy").format(rAct.getFecha());
						System.out.println(respuesta);
						out.println(respuesta);
					}
				}
				respuesta = "FIN";
				System.out.println(respuesta);
				out.println(respuesta);

			}else if (comando.startsWith("DATOSPUSH")) {
				String[] datos = comando.split("##");
				String username = datos[1];
				String password = datos[2];

				Principal p = sistema.darInstancia(username, password);

				ArrayList<Vehicle> vehiculos = new ArrayList<Vehicle>();

				comando = in.readLine();
				System.out.println(comando);
				while(!comando.startsWith("FIN")){

					while(comando.startsWith("VEH")){//recibe un vehiculo
						String[] veh = comando.split("##");

						ArrayList<Maintenance>maintenances = new ArrayList<Maintenance>();
						comando = in.readLine();
						System.out.println(comando);
						while(comando.startsWith("MAN")) {
							String[] man = comando.split("##");
							Maintenance mAct = new Maintenance(Integer.parseInt(man[1]), man[2], Integer.parseInt(man[3]), Long.parseLong(man[4]));
							maintenances.add(mAct);
							comando = in.readLine();
							System.out.println(comando);
						}

						ArrayList<Record>records = new ArrayList<Record>();
						while (comando.startsWith("REC")) {
							String[] rec = comando.split("##");
							Record rAct = new Record(Double.parseDouble(rec[1]), rec[2], Double.parseDouble(rec[3]), rec[4], new SimpleDateFormat("dd-MM-yyyy").parse(rec[5]));
							records.add(rAct);
							comando = in.readLine();
							System.out.println(comando);
						}

						Vehicle vAct = new Vehicle(veh[1], Double.parseDouble(veh[2]), Integer.parseInt(veh[3]), maintenances, records);
						vehiculos.add(vAct);

					}
				}
				p.setVehiculos(vehiculos);
				System.out.println("se hizo push de un total de "+p.getVehiculos().size()+" vehiculos al usuario "+p.getUsername());
				sistema.saveState();
			}else if(comando.startsWith(Protocolo.DESCONECTAR)){
				//Al entrar acá significa que esta en formato DES##
				conexionTerminada = true;
				sistema.restarConexion();
			}else if(comando.equals(null)){
				respuesta = Protocolo.ERROR;
				System.out.println("Servidor: " + respuesta);
				out.println(respuesta);
			}
		}
	}
}