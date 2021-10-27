package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.MedicalStaff;
import pe.edu.upc.spring.model.Medication;
import pe.edu.upc.spring.model.Medicine;
import pe.edu.upc.spring.model.MedicineStatus;
import pe.edu.upc.spring.model.Patient;
import pe.edu.upc.spring.service.IMedicalStaffService;
import pe.edu.upc.spring.service.IMedicationService;
import pe.edu.upc.spring.service.IMedicineService;
import pe.edu.upc.spring.service.IMedicineStatusService;
import pe.edu.upc.spring.service.IPatientService;

@Controller
@RequestMapping("/medication")
public class MedicationController {
	@Autowired
	private IMedicationService mService;
	
	@Autowired
	private IMedicineStatusService estService;
	
	@Autowired
	private IPatientService pService;
	
	@Autowired
	private IMedicineService medService;
	
	@Autowired
	private IMedicalStaffService pmService;
	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; // bienvenido es una pagina del frontend, pagina de Inicio
	}
	
	@RequestMapping("/")
	public String irPaginaListadoMedicamentos(Map<String, Object> model) {
		model.put("listaMedicaciones", mService.listar());
		return "listMedication"; // "listPatient" es una pagina del frontEnd para listar
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar (Model model) {
		
		model.addAttribute("listaMedicinas", medService.listar());
		model.addAttribute("listaEstadoMedicinas", estService.listar());
		model.addAttribute("listaPacientes", pService.listar());
		model.addAttribute("listaPersonalMedicos", pmService.listar());
		
		model.addAttribute("medicine", new Medicine());
		model.addAttribute("medication", new Medication());
		model.addAttribute("medicinestatus", new MedicineStatus());
		model.addAttribute("patient", new Patient());
		model.addAttribute("medicalstaff", new MedicalStaff());
		return "medication"; // "patient" es una pagina del frontend para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Medication objMedication, BindingResult binRes, Model model)
		throws ParseException
	{
		if(binRes.hasErrors()) {
			model.addAttribute("listaMedicinas", medService.listar());
			model.addAttribute("listaEstadoMedicinas", estService.listar());
			model.addAttribute("listaPacientes", pService.listar());
			model.addAttribute("listaPersonalMedicos", pmService.listar());
			return "medication";}
		
		else {
			boolean flag = mService.grabar(objMedication);
			if(flag)
				return "redirect:/medication/listar";
			else {
				model.addAttribute("mensaje", "F, Ocurrio algo, LUZ ROJA xD");
				return "redirect:/medication/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException
	{
		Optional<Medication> objMedication = mService.listarId(id);
		if(objMedication == null) {
			objRedir.addFlashAttribute("mensaje", "F, Ocurrio algo, LUZ ROJA xD");
			return "redirect:/medication/listar";
		}
		else {
			model.addAttribute("listaMedicinas", medService.listar());
			model.addAttribute("listaEstadoMedicinas", estService.listar());
			model.addAttribute("listaPacientes", pService.listar());
			model.addAttribute("listaPersonalMedicos", pmService.listar());
			if (objMedication.isPresent())
                objMedication.ifPresent(o -> model.addAttribute("medication", o));
			
			return "medication";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				mService.eliminar(id);
				model.put("listaMedicaciones", mService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaMedicaciones", mService.listar());
		}
		return "listMedication";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaMedicaciones", mService.listar());
		return "listMedication";
		
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Medication medication ) 
		throws ParseException
	{
		mService.listarId(medication.getIdMedication());
		return "listMedication";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model ) {
		model.addAttribute("patient", new Patient());
		return "buscarm";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Patient patient ) throws ParseException
	{
		//vamos a buscar por nombre del paciente
		
		List<Medication> listaMedicaciones;
		patient.setNamePatient(patient.getNamePatient());//capturo lo de la caja de texto
		listaMedicaciones = mService.buscarPaciente(patient.getNamePatient()); //buscando 1
		
		
		if(listaMedicaciones.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaMedicamentos", listaMedicaciones);
		return "buscarm";
	}
	
}
