package com.autominder;

import java.io.Serializable;
import java.util.ArrayList;

public class Principal implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8013898316665900177L;
	
	
	private ArrayList<Vehicle> vehiculos;
	private ArrayList<Maintenance> mantenimientos;
	private String username;
	private String password;
	
	private Vehicle selected;

	public Principal(String username, String password, ArrayList<Vehicle> vehiculos, ArrayList<Maintenance> mantenimientos) {

		this.username = username;
		this.password = password;
		this.vehiculos = vehiculos;
		selected = vehiculos.get(0);
		this.mantenimientos = mantenimientos;
		
	}
	
	public Principal(String username, String password) {

		this.username = username;
		this.password = password;
		this.vehiculos = new ArrayList<Vehicle>();
		selected = vehiculos.isEmpty() ?null: vehiculos.get(0);
		this.mantenimientos = new ArrayList<Maintenance>();
		
	}

//	public ArrayList<Maintenance> cargarMantenimientosIniciales() {
//		try {
//			ArrayList<Maintenance> a = new ArrayList<Maintenance>();
//			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder builder = factory.newDocumentBuilder();
//			Document document = builder.parse(getClass().getResourceAsStream("/maintenance_types.xml"));
//			NodeList nodeList = document.getDocumentElement().getChildNodes();
//			for (int i = 0; i < nodeList.getLength(); i++) {
//				Node node = nodeList.item(i);
//				if (node.getNodeType() == Node.ELEMENT_NODE) {
//					Element elem = (Element) node;
//					// Get the value of all sub-elements.
//					int type = Integer.parseInt(elem.getElementsByTagName("type")
//							.item(0).getChildNodes().item(0).getNodeValue());
//					String nombre = elem.getElementsByTagName("name").item(0)
//							.getChildNodes().item(0).getNodeValue();
//					int km = Integer.parseInt(elem.getElementsByTagName("km")
//							.item(0).getChildNodes().item(0).getNodeValue());
//					long tiempo = Long.parseLong(elem.getElementsByTagName("time")
//							.item(0).getChildNodes().item(0).getNodeValue());
//					Maintenance m = new Maintenance(type, nombre, km, tiempo);
//					a.add(m);
//				}
//			}
//			return a;
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setVehiculos(ArrayList<Vehicle> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public void setMantenimientos(ArrayList<Maintenance> mantenimientos) {
		this.mantenimientos = mantenimientos;
	}

	public ArrayList<Vehicle> getVehiculos() {
		return vehiculos;
	}

	
//	@SuppressWarnings("unchecked")
//	public void loadState(){
//		try {
//			FileInputStream fis = c.openFileInput(file);
//			ObjectInputStream ois = new ObjectInputStream(fis);
//			vehiculos = (ArrayList<Vehicle>) ois.readObject();
//			System.out.println("cargados "+vehiculos.size()+" vehiculos");
//			mantenimientos = (ArrayList<Maintenance>) ois.readObject();
//			System.out.println("cargados "+mantenimientos.size()+" mantenimientos");
//			selected = vehiculos.get(0);
//			System.out.println("cargado selected: "+selected.getName());
//			ois.close();
//			fis.close();
//		} catch (StreamCorruptedException e1) {
//			System.out.println("StreamCorruptedException");
//			e1.printStackTrace();
//		} catch (ClassNotFoundException e1) {
//			System.out.println("ClassNotFoundException");
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			System.out.println("IOException");
//			e1.printStackTrace();
//		}		
//	}

	public ArrayList<Maintenance> getMantenimientos() {
		return mantenimientos;
	}

//	public static Principal darInstancia(Context context)
//	{
//		if(instancia == null)
//		{
//			instancia = new Principal(context);
//		}
//		return instancia;
//	}

//	public void agregarMantenimiento(String nombre, int tipo, int km, long tiempo){
//		Maintenance m = new Maintenance(tipo, nombre, km, tiempo);
//		mantenimientos.add(m);
//		saveState();
//	}


//	public ArrayList<Reminder> obtenerReminders() {
//		ArrayList<Reminder> allReminders = new ArrayList<Reminder>();
//		for (int i = 0; i < vehiculos.size(); i++) {
//			Vehicle act = vehiculos.get(i);
//			allReminders.addAll(act.getReminders());
//		}
//		return allReminders;
//	}

//	@SuppressWarnings("static-access")
//	public void saveState(){
//		try {
//			FileOutputStream fos = c.openFileOutput(file, c.MODE_PRIVATE);
//			ObjectOutputStream oos = new ObjectOutputStream(fos);
//			oos.writeObject(vehiculos);
//			oos.writeObject(mantenimientos);
//			oos.close();
//			fos.close();	
//			System.out.println("Saved "+vehiculos.size()+" vehicles.");	
//			System.out.println("Saved "+mantenimientos.size()+" maintenances.");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
	
//	/**
//	 * 
//	 * @param v
//	 * @return true si se logró agregar el vehiculo, false si ya existe un vehiculo con ese nombre
//	 */
//	public boolean addVehicle(Vehicle v) {
//		for (int i = 0; i < vehiculos.size(); i++) {
//			Vehicle act = vehiculos.get(i);
//			if(act.getName().equals(v.getName())) return false;
//		}
//		vehiculos.add(v);
//		saveState();
//		return true;
//		
//	}

	public Vehicle getSelected() {
		return selected;
	}

	public void setSelected(Vehicle selected) {
		this.selected = selected;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
//	public boolean addMaintenanceSelected(Maintenance m, Record r){
//		boolean a = selected.addNewMaintenance(m, r);
//		if(a){
//			saveState();
//		}
//		return a;
//	}

//	public void setSelectedName(String nombreCarro) {
//		for (int i = 0; i < vehiculos.size(); i++) {
//			Vehicle act = vehiculos.get(i);
//			if(act.getName().equalsIgnoreCase(nombreCarro))selected=act;
//		}
//		
//	}

//	public void deleteVehicle(Vehicle vehicle) {
//		for (int i = 0; i < vehiculos.size(); i++) {
//			Vehicle act = vehiculos.get(i);
//			if (vehicle.getName().equals(act.getName())) {
//				vehiculos.remove(i);
//				if(vehicle.getName().equals(selected.getName())){
//					setSelected(vehiculos.get(0));
//				}
//			}
//		}
//		
//	}
}
