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

import pe.edu.upc.spring.model.Patient;
import pe.edu.upc.spring.service.IPatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private IPatientService pService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "index"; // bienvenido es una pagina del frontend, pagina de Inicio
	}
	
	@RequestMapping("/")
	public String irPaginaListadoPacientes(Map<String, Object> model) {
		model.put("listaPacientes", pService.listar());
		return "listPatient"; // "listPatient" es una pagina del frontEnd para listar
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar (Model model) {
		model.addAttribute("patient", new Patient());
		return "patient"; // "patient" es una pagina del frontend para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Patient objPatient, BindingResult binRes, Model model)
		throws ParseException
	{
		if(binRes.hasErrors())
			return "patient";
		else {
			boolean flag = pService.grabar(objPatient);
			if(flag)
				return "redirect:/patient/listar";
			else {
				model.addAttribute("mensaje", "F, Ocurrio algo, LUZ ROJA xD");
				return "redirect:/patient/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException
	{
		Optional<Patient> objPatient = pService.listarId(id);
		if(objPatient == null) {
			objRedir.addFlashAttribute("mensaje", "F, Ocurrio algo, LUZ ROJA xD");
			return "redirect:/patient/listar";
		}
		else {
			model.addAttribute("patient", objPatient);
			return "patient";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaPacientes", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaPacientes", pService.listar());
		}
		return "listPatient";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPacientes", pService.listar());
		return "listPatient";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Patient patient ) 
		throws ParseException
	{
		pService.listarId(patient.getIdPatient());
		return "listPatient";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model ) {
		model.addAttribute("patient", new Patient());
		return "buscar";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Patient patient ) throws ParseException, java.text.ParseException
	{
		//vamos a buscar por nombre, apellido, dni, o numero de cama
		
		List<Patient> listaPacientes;
		patient.setNamePatient(patient.getNamePatient());//capturo lo de la caja de texto
		listaPacientes = pService.buscarNombre(patient.getNamePatient()); //buscando 1
		
		if(listaPacientes.isEmpty()) {
			listaPacientes = pService.buscarApellido(patient.getNamePatient());
		}
		
		if(listaPacientes.isEmpty()) {
			listaPacientes = pService.buscarDNI(patient.getNamePatient());
		}
		
		if(listaPacientes.isEmpty()) {
			listaPacientes = pService.buscarCama(Integer.parseInt(patient.getNamePatient()));
		}
		
		if(listaPacientes.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		
		model.put("patient", new Patient());
		model.put("listaPacientes", listaPacientes);
		return "buscar";
	}
	
	@RequestMapping("/buscarFecha")
	public String buscarfecha(Map<String, Object> model, @ModelAttribute Patient patient ) throws ParseException, java.text.ParseException
	{
		//vamos a buscar por fecha de ingreso del paciente
		
		List<Patient> listaPacientes;
		
		patient.setDatePatient(patient.getDatePatient()); // capturo la fecha de la caja de texto
		listaPacientes = pService.findByDatePatient(patient.getDatePatient()); // buscando

		if(listaPacientes.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaPacientes", listaPacientes);
		return "buscar";
	}
	
}
