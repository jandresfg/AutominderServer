package com.autominder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Vehicle implements Serializable{

	private static final long serialVersionUID = -7614135801653779184L;

	private String name;
	private boolean favorite;
	/**
	 * es un numero que indica el numero de kilometros SEMANALES
	 */
	private double weeklyKM;
	private int currentKmCount;
	private Date lastDayChecked;
	private ArrayList<Maintenance> maintenances;
	private ArrayList<Record> records;

	public Vehicle(String name, double vWeeklyKM, int currentKmCount,
			ArrayList<Maintenance> maintenances, ArrayList<Record> records) {
		this.name = name;
		this.weeklyKM = vWeeklyKM;
		this.setCurrentKmCount(currentKmCount);
		setLastDayChecked(new Date());
		this.maintenances = maintenances;
		this.records = records;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public ArrayList<Maintenance> getMaintenances() {
		return maintenances;
	}

	public String getName() {
		return name;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public double getWeeklyKM() {
		return weeklyKM;
	}


	public ArrayList<Record> getRecords() {
		return records;
	}

//	/**
//	 * El ususario tambien puede darse cuenta de que su estimado de kilometraje
//	 * semanal fue una burrada, y querra cambiarlo. Ni modo. Tocara entonces
//	 * guardar el nuevo kilometraje semanal y recalcular los reminders.
//	 * @param newWeeklyKM
//	 */
//	public void setWeeklyKM(int weeklyKM) {
//		this.weeklyKM=weeklyKM;
//		calcularRecordatorios();
//	}

//	/**
//	 * Por cada mantenimiento de lista de mantenimientos (segun km) deseados
//	 * para el carro, tome su km y con base a los km recorridos desde el ultimo
//	 * record, recalcule la siguiente fecha. Cree todos los reminders y asignelos al
//	 * atributo 'reminders' 
//	 */
//	private void calcularRecordatorios() {
//		ArrayList<Reminder> brandNewReminders = new ArrayList<Reminder>();
//		for(int i = 0; i<maintenances.size(); i++){
//			Maintenance m = maintenances.get(i);
//			System.out.println("mantenimiento: "+m.getNombre());
//			System.out.println("record: "+darRecordPorMantenimiento(m.getNombre()));
//			if(m.getType() == Maintenance.SEGUN_KM){
//				double kmsUntilNext = m.getKm() - darRecordPorMantenimiento(m.getNombre()).getKmPassedSince();
//				System.out.println("----------------------KMS UNTILL NEXT:"+kmsUntilNext);
//				long daysUntilNext = (long) (7*kmsUntilNext/weeklyKM);
//				System.out.println("----------------------days UNTILL NEXT:"+daysUntilNext);
//				/**
//				 * si en una semana se recorren weeklyKM km, i.e, 1sem/weeklyKM km,
//				 * entonces se debe multiplicar por 7 para obtener 7dias/weeklyKM km, 
//				 * y finalmente se multiplica por 'kmsUntilNext' km;
//				 * obteniendo 7*kmsUntilNext/weeklyKM dias
//				 */
//				long milisUntilNext = new Date().getTime() + daysUntilNext*24*60*60*1000;
//				Date next = new Date(milisUntilNext);
//				System.out.println("--------------------------------NEXT:"+new SimpleDateFormat("dd-MM-yyyy").format(next));
//				Reminder rem = new Reminder(m.getNombre(), next, name);
//				brandNewReminders.add(rem);
//			}else{
//				long timeUntilNext = new Date().getTime() + m.getTiempo() - (new Date().getTime()-darRecordPorMantenimiento(m.getNombre()).getFecha().getTime());
//				Reminder rem = new Reminder(m.getNombre(), new Date(timeUntilNext), name);
//				brandNewReminders.add(rem);
//			}
//		}
//		reminders = brandNewReminders;
//		//voila, bitch.
//	}

//	private Record darRecordPorMantenimiento(String nombre) {
//		for (int i = 0; i < records.size(); i++) {
//			Record r = records.get(i);
//			if(r.getMaintenanceName().equalsIgnoreCase(nombre))return r;
//		}
//		return null;
//	}

	public int getCurrentKmCount() {
		return currentKmCount;
	}

	public void setCurrentKmCount(int currentKmCount) {
		this.currentKmCount = currentKmCount;
	}

	public Date getLastDayChecked() {
		return lastDayChecked;
	}

	public void setLastDayChecked(Date lastDayChecked) {
		this.lastDayChecked = lastDayChecked;
	}
}
//	/**
//	 * Cada vez que se carga la vista de un carro, se debe pedir su currentKmCount
//	 * @return
//	 */
//	public int getCurrentKmCount() {
//		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
//		try {
//			if(new Date().getTime()>df.parse(df.format(lastDayChecked)).getTime()+24*60*60*1000)//si es al menos el siguiente dia a la ultima vez
//			{
//				//entonces se averigua cuantos dias han pasado,
//				int daysPassed = (int) ((new Date().getTime() - df.parse(df.format(lastDayChecked)).getTime()+24*60*60*1000)/(24*60*60*1000));
//
//				//se actualizan los KmPassedSince de los records y
//				for (int i = 0; i < records.size(); i++) {
//					Record r = records.get(i);
//					r.setKmPassedSince(r.getKmPassedSince()+(weeklyKM*daysPassed/7));
//				}
//
//				//se actualiza el currentKmCount y el lastDayChecked. Pretty cool right?
//				currentKmCount+=weeklyKM*daysPassed/7;
//				lastDayChecked = new Date();
//				return currentKmCount;
//
//			}else{
//				return currentKmCount;
//			}
//		} catch (ParseException e) {
//			// Nunca entra a esta excepcion...
//			e.printStackTrace();
//			return 999999;
//		}
//	}
//
//	/**
//	 * Al usuario puede darle la gana de modificar su actual cuenta de km,
//	 * esta nueva cuenta de km puede ser menor o mayor a la actual; como sea, 
//	 * hay que restar/sumar lo necesario de la actual cuenta de km y, asi mismo,
//	 * de los kmPassedSince de los records... sin mencionar que toca recalcular
//	 * los reminders.
//	 * @param newCurrentKmCount
//	 */
//	public void modifyCurrentKmCount(int newCurrentKmCount){
//		int diferencia = newCurrentKmCount-currentKmCount;
//		currentKmCount = newCurrentKmCount;
//		for (int i = 0; i < records.size(); i++) {
//			Record r = records.get(i);
//			r.setKmPassedSince(r.getKmPassedSince()+diferencia);
//		}
//		calcularRecordatorios();
//	}
//	
//	public void addNewRecord(Record r) {
//		boolean finished = false;
//		for (int i = 0; i < records.size() && !finished; i++) {
//			Record act = records.get(i);
//			if(act.getMaintenanceName().equalsIgnoreCase(r.getMaintenanceName())){
//				records.remove(i);
//				records.add(i, r);
//				finished = true;
//			}
//		}
//		calcularRecordatorios();
//	} 
//	
//	/**
//	 * @return retorna false si ya existe un mantenimiento con ese nombre 
//	 */
//	public boolean addNewMaintenance(Maintenance m, Record r){
//		boolean noExiste = true;
//		for (int i = 0; i < maintenances.size() && noExiste ; i++) {
//			if (maintenances.get(i).getNombre().equals(m.getNombre())) {
//				noExiste=true;
//			}
//		}
//		if (noExiste) {
//			maintenances.add(m);
//			records.add(r);
//			calcularRecordatorios();
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * Elimina el mantenimiento cuyo nombre es dado por parametro, 
//	 * asi como su record y recalcula los reminders
//	 */
//	public void removeMaintenance(String maintenanceName){
//		Maintenance m = null;
//		
//		for (int i = 0; i < maintenances.size(); i++) {
//			Maintenance act = maintenances.get(i);
//			if(act.getNombre().equals(maintenanceName))
//				m = act;
//		}
//		
//		if(m != null){
//			maintenances.remove(m);
//			records.remove(darRecordPorMantenimiento(maintenanceName));
//			calcularRecordatorios();
//		}
//	}
//	
//	public Maintenance getMaintenance(String name){
//		Maintenance a = null;
//		
//		for (int i = 0; i < maintenances.size(); i++) {
//			Maintenance act = maintenances.get(i);
//			if(name.equalsIgnoreCase(act.getNombre()))
//				a = act;
//		}
//		
//		return a;
//	}
//
//	public void modifyMaintenance(String maintenName, int newKmInterval, int newTimeInterval, String timeChoice){
//		boolean over = false;
//		for (int i = 0; i < maintenances.size() && !over; i++) {
//			Maintenance x = maintenances.get(i);
//			if(x.getNombre().equalsIgnoreCase(maintenName)){
//				if(x.getType() == Maintenance.SEGUN_KM){
//					maintenances.get(i).setKm(newKmInterval);
//					calcularRecordatorios();
//				}else if(x.getType() == Maintenance.SEGUN_TIEMPO){
//					long newTimeIntervalMilis = 0;
//					if(timeChoice.equalsIgnoreCase("días")){
//						newTimeIntervalMilis = ((long)newTimeInterval)*24*60*60*1000;
//					}else if(timeChoice.equalsIgnoreCase("meses")){
//						newTimeIntervalMilis = ((long)newTimeInterval)*30*24*60*60*1000;
//					}else if(timeChoice.equalsIgnoreCase("años")){
//						newTimeIntervalMilis = ((long)newTimeInterval)*365*24*60*60*1000;
//					}
//					System.out.println("----------------------------------newTimeIntervalMilis ="+newTimeIntervalMilis);
//					maintenances.get(i).setTiempo(newTimeIntervalMilis);
//					calcularRecordatorios();
//				}
//				over = true;
//			}
//		}
//	}
//
//	public int getKmPassedSince(Date date) {
//		double dailyKm = weeklyKM/7;
//		long daysPassed = ((new Date()).getTime() - date.getTime())/(1000*60*60*24);
//		return (int) (dailyKm*daysPassed);
//	}
//
//	public Date getEstimatedDate(int kmSince) {
//		double dailyKm = weeklyKM/7;
//		long daysPassed = (long) (kmSince/dailyKm);
//		return new Date((new Date()).getTime()-(daysPassed*24*60*60*1000));
//	}
//}
