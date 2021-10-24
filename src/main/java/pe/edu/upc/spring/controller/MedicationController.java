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

import pe.edu.upc.spring.model.Medication;
import pe.edu.upc.spring.service.IMedicationService;

@Controller
@RequestMapping("/medication")
public class MedicationController {
	@Autowired
	private IMedicationService mService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido"; // bienvenido es una pagina del frontend, pagina de Inicio
	}
	
	@RequestMapping("/")
	public String irPaginaListadoMedicamentos(Map<String, Object> model) {
		model.put("listaMedicamentos", mService.listar());
		return "listMedication"; // "listPatient" es una pagina del frontEnd para listar
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar (Model model) {
		model.addAttribute("medication", new Medication());
		return "medication"; // "patient" es una pagina del frontend para insertar y/o modificar
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Medication objMedication, BindingResult binRes, Model model)
		throws ParseException
	{
		if(binRes.hasErrors())
			return "medication";
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
			model.addAttribute("medication", objMedication);
			return "medication";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if(id!=null && id>0) {
				mService.eliminar(id);
				model.put("listaMedicamentos", mService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaMedicamentos", mService.listar());
		}
		return "listMedication";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaMedicamentos", mService.listar());
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
		model.addAttribute("medication", new Medication());
		return "buscar";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Medication medication ) throws ParseException
	{
		//vamos a buscar por nombre, apellido, dni, o numero de cama
		
		List<Medication> listaMedicamentos;
		medication.setPatient(medication.getPatient());//capturo lo de la caja de texto
		listaMedicamentos = mService.buscarPaciente(medication.getPatient().getNamePatient()); //buscando 1
		
		
		if(listaMedicamentos.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaMedicamentos", listaMedicamentos);
		return "buscar";
	}
	
}
