package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Feeding;
import pe.edu.upc.spring.model.FeedingShift;
import pe.edu.upc.spring.model.FeedingType;
import pe.edu.upc.spring.model.MedicalStaff;
import pe.edu.upc.spring.model.Medication;
import pe.edu.upc.spring.model.Medicine;
import pe.edu.upc.spring.model.MedicineStatus;
import pe.edu.upc.spring.model.Patient;
import pe.edu.upc.spring.model.VitalSigns;
import pe.edu.upc.spring.service.IFeedingService;
import pe.edu.upc.spring.service.IFeedingShiftService;
import pe.edu.upc.spring.service.IFeedingTypeService;
import pe.edu.upc.spring.service.IMedicalStaffService;
import pe.edu.upc.spring.service.IMedicationService;
import pe.edu.upc.spring.service.IMedicineService;
import pe.edu.upc.spring.service.IMedicineStatusService;
import pe.edu.upc.spring.service.IPatientService;
import pe.edu.upc.spring.service.ITypeMedicalStaffService;
import pe.edu.upc.spring.service.IVitalSignsService;

@Controller
@RequestMapping("/report")
public class ReportController {
	@Autowired
	private IPatientService pService;
	
	@Autowired
	private IFeedingService fService;
	
	@Autowired
	private IFeedingTypeService ftService;
	
	@Autowired
	private IFeedingShiftService fsService;
	
	@Autowired
	private IMedicalStaffService msService;

	
	@Autowired
	private IMedicationService mService;
	
	@Autowired
	private IMedicineStatusService estService;
	
	@Autowired
	private IMedicineService medService;
	
	@Autowired
	private IVitalSignsService vsService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "index"; // bienvenido es una pagina del frontend, pagina de Inicio
	}
	
	@RequestMapping("/buscarreporte")
	public String buscarreporte(Map<String, Object> model, @ModelAttribute Patient patient ) throws ParseException, java.text.ParseException
	{
		/*------------------------SIGNOS VITALES------------------------*/
		
		List<VitalSigns> listaSignosVitales;
		patient.setNamePatient(patient.getNamePatient());
		listaSignosVitales = vsService.buscarPaciente(patient.getNamePatient());
		
		if(listaSignosVitales.isEmpty()) {
			listaSignosVitales = vsService.buscarApellido(patient.getNamePatient());
		}

		if (listaSignosVitales.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		
		model.put("patient", new Patient());
        model.put("medico", new MedicalStaff());
        model.put("vitalsigns", new VitalSigns());

        model.put("listaSignosVitales", listaSignosVitales);
        model.put("listaPacientes", pService.listar());
        model.put("listaPersonalMedicos", msService.listar());
        
        
        /*-------------------------MEDICACION---------------------------*/
       

		List<Medication> listaMedicaciones;
		patient.setNamePatient(patient.getNamePatient());//capturo lo de la caja de texto
		listaMedicaciones = mService.buscarPaciente(patient.getNamePatient()); 
		
		if(listaMedicaciones.isEmpty()) {
			listaMedicaciones = mService.buscarApellido(patient.getNamePatient());
		}
		
		if(listaMedicaciones.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaPacientes",pService.listar());
		model.put("listaMedicaciones", listaMedicaciones);
		model.put("listaPersonalMedicos",msService.listar());
		model.put("listaMedicinas", medService.listar());
		model.put("listaEstadoMedicinas", estService.listar());
		
		model.put("patient", new Patient());
		model.put("medication", new Medication());
		model.put("medicalstaff",new MedicalStaff());
		model.put("medicinestatus", new MedicineStatus());
		model.put("medicine", new Medicine());
		
		
		/*-------------------------ALIMENTACION---------------------------*/
		
		List<Feeding> listaAlimentacion;
		patient.setNamePatient(patient.getNamePatient());//capturo lo de la caja de texto
		listaAlimentacion = fService.buscarPaciente(patient.getNamePatient()); //buscando 1
		
		if(listaAlimentacion.isEmpty()) {
			listaAlimentacion = fService.buscarApellido(patient.getNamePatient());
		}
		
		if(listaAlimentacion.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		
		model.put("patient", new Patient());
		model.put("feeding",new Feeding());
		model.put("medicalstaff",new MedicalStaff());
		model.put("feedingtype",new FeedingType());
		model.put("feedingshift",new FeedingShift());
		
		model.put("listaAlimentacion", listaAlimentacion);
		model.put("listaPacientes",pService.listar());
		model.put("listaPersonalMedicos",msService.listar());
		model.put("listaTipoAlimentacion", ftService.listar());
		model.put("listaTurnoAlimentacion", fsService.listar());
		
		return "reportPatient";
	}
	
	@RequestMapping("/buscarFecha")
	public String buscarfecha(Map<String, Object> model, @ModelAttribute("patient") Patient patient)
			throws ParseException {
		// vamos a buscar por fecha en cual se hizo el control

		List<VitalSigns> listaSignosVitales;

		patient.setDatePatient(patient.getDatePatient()); // capturo la fecha del control
		listaSignosVitales = vsService.findByDateSV(patient.getDatePatient()); // buscando uwu

		if (listaSignosVitales.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("patient", new Patient());
        model.put("medico", new MedicalStaff());
        model.put("vitalsigns", new VitalSigns());

        model.put("listaSignosVitales", listaSignosVitales);
        model.put("listaPacientes", pService.listar());
        model.put("listaPersonalMedicos", msService.listar());
		
		return "buscarvs";
	}
	
}
