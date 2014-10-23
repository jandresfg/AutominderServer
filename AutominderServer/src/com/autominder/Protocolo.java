package com.autominder;

/**
 * Clase que modela los comandos del protocolo de comunicacion entre servidor y usuario
 */
public class Protocolo {

	//----------
	// CONSTANTES
	//----------
	
	/**
	 * Constante que modela el comando AUTENTICAR
	 */
	public static final String AUTENTICAR = "AUT";
	
	/**
	 * Constante que modela el comando REPORTE
	 */
	public static final String REPORTE = "REP";
	
	/**
	 * Constante que modela el comando INFROMACION
	 */
	public static final String INFORMACION = "INF";
	
	/**
	 * Constante que modela el comando DESCONECTAR
	 */
	public static final String DESCONECTAR = "DES";
	
	/**
	 * Constante que modela el comando de respuesta ERROR
	 */
	public static final String ERROR = "ERR";
	
	/**
	 * Constante que modela el comando de respuesta ACEPTAR
	 */
	public static final String ACEPTAR = "OK";
	
	/**
	 * Constante seperador
	 */ 	
	public static final String SEPARADOR = "##";
	
	public static final String HOLA = "HOLA";
	
	public static final String POSICION = "POS";
}
