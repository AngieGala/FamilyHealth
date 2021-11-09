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
import pe.edu.upc.spring.model.Feeding;
import pe.edu.upc.spring.model.FeedingShift;
import pe.edu.upc.spring.model.FeedingType;
import pe.edu.upc.spring.model.MedicalStaff;
import pe.edu.upc.spring.service.IPatientService;
import pe.edu.upc.spring.service.IFeedingService;
import pe.edu.upc.spring.service.IFeedingShiftService;
import pe.edu.upc.spring.service.IFeedingTypeService;
import pe.edu.upc.spring.service.IMedicalStaffService;

@Controller
@RequestMapping("/feeding")
public class FeedingController {
	
	@Autowired
	private IFeedingService fService;
	
	@Autowired
	private IPatientService pService;
	
	@Autowired
	private IFeedingTypeService ftService;
	
	@Autowired
	private IFeedingShiftService fsService;
	
	@Autowired
	private IMedicalStaffService pmService;
	
	@RequestMapping("/bienvenido")
	public String irFeedingBienvenido() {
		return "index";
	}
	
	@RequestMapping("/")
	public String irFeeding(Map<String, Object> model) {
		model.put("listaAlimentos", fService.listar());
		return "listFeeding";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		
		model.addAttribute("listaPacientes", pService.listar());
		model.addAttribute("listaPersonalMedicos", pmService.listar());
		model.addAttribute("listaTipoAlimentacion", ftService.listar());
		model.addAttribute("listaTurnoAlimentacion", fsService.listar());
				
		model.addAttribute("feeding", new Feeding());
		model.addAttribute("patient", new Patient());
		model.addAttribute("feedingtype", new FeedingType());
		model.addAttribute("feedingshift", new FeedingShift());
		model.addAttribute("medicalstaff", new MedicalStaff());
		
		return "feeding";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Feeding objFeeding, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaPacientes", pService.listar());
			model.addAttribute("listaTipoAlimentacion", ftService.listar());
			model.addAttribute("listaTurnoAlimentacion", fsService.listar());
			model.addAttribute("listaPersonalMedicos", pmService.listar());
			return "feeding";
		} 
		else {
			boolean flag = fService.grabar(objFeeding);
			if (flag) {
				return "redirect:/feeding/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/feeding/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Feeding> objFeeding = fService.listarId(id);
		if (objFeeding == null) {
			objRedir.addAttribute("mensaje", "Ocurrió un error");
			return "redirect:/feeding/listar";
		}
		else {
			model.addAttribute("listaPacientes", pService.listar());
			model.addAttribute("listaTipoAlimentacion", ftService.listar());
			model.addAttribute("listaTurnoAlimentacion", fsService.listar());
			model.addAttribute("listaPersonalMedicos", pmService.listar());
			
			if (objFeeding.isPresent())
				objFeeding.ifPresent(o -> model.addAttribute("feeding", o));
			return "feeding";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				fService.eliminar(id);
				model.put("listaAlimentacion", fService.listar());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrió un error");
			model.put("listaAlimentacion", fService.listar());
		}
		return "listFeeding";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaAlimentacion", fService.listar());
		return "listFeeding";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Feeding feeding) throws ParseException {
		fService.listarId(feeding.getIdFeeding());
		return "listFeeding";
	}
	

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("patient", new Patient());
		model.addAttribute("feeding",new Feeding());
		model.addAttribute("medicalstaff",new MedicalStaff());
		model.addAttribute("feedingtype",new FeedingType());
		model.addAttribute("feedingshift",new FeedingShift());
		
		model.addAttribute("listaPersonalMedicos",pmService.listar());
		model.addAttribute("listaPacientes",pService.listar());
		model.addAttribute("listaAlimentacion",fService.listar());
		model.addAttribute("listaTipoAlimentacion", ftService.listar());
		model.addAttribute("listaTurnoAlimentacion", fsService.listar());
		return "buscarf";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Patient patient) 
		throws ParseException
	{
		//vamos a buscar por nombre de paciente
		
		List<Feeding> listaAlimentacion;
		patient.setNamePatient(patient.getNamePatient());
		listaAlimentacion = fService.buscarPaciente(patient.getNamePatient());

		if (listaAlimentacion.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaPacientes",pService.listar());
		model.put("listaAlimentacion", listaAlimentacion);
		model.put("listaPersonalMedicos",pmService.listar());
		model.put("listaTipoAlimentacion", ftService.listar());
		model.put("listaTurnoAlimentacion", fsService.listar());
		
		model.put("patient", new Patient());
		model.put("feeding",new Feeding());
		model.put("medicalstaff",new MedicalStaff());
		model.put("feedingtype",new FeedingType());
		model.put("feedingshift",new FeedingShift());
		return "buscarf";
	}
	
	@RequestMapping("/buscarFecha")
	public String buscarfecha(Map<String, Object> model, @ModelAttribute("patient") Patient patient)
			throws ParseException {
		// vamos a buscar por fecha en cual se hizo el control

		List<Feeding> listaAlimentacion;

		patient.setDatePatient(patient.getDatePatient()); // capturo la fecha del control
		listaAlimentacion = fService.findBydateFeeding(patient.getDatePatient()); // buscando uwu

		if (listaAlimentacion.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaAlimentacion", listaAlimentacion);
		model.put("listaPacientes",pService.listar());
		model.put("listaPersonalMedicos",pmService.listar());
		model.put("listaTipoAlimentacion", ftService.listar());
		model.put("listaTurnoAlimentacion", fsService.listar());
		
		model.put("patient", new Patient());
		model.put("feeding",new Feeding());
		model.put("medicalstaff",new MedicalStaff());
		model.put("feedingtype",new FeedingType());
		model.put("feedingshift",new FeedingShift());
		return "buscarf";
	}
	
	@RequestMapping("/buscarMedico")
	public String buscarMedico(Map<String, Object> model, @ModelAttribute MedicalStaff medicalstaff) 
		throws ParseException
	{
		//vamos a buscar por nombre de paciente
		
		List<Feeding> listaAlimentacion;
		medicalstaff.setNamePM(medicalstaff.getNamePM());
		listaAlimentacion = fService.buscarMedico(medicalstaff.getNamePM());

		if (listaAlimentacion.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaPersonalMedicos",pmService.listar());
		model.put("listaAlimentacion", listaAlimentacion);
		model.put("listaPacientes",pService.listar());
		model.put("listaTipoAlimentacion", ftService.listar());
		model.put("listaTurnoAlimentacion", fsService.listar());
		
		model.put("patient", new Patient());
		model.put("feeding",new Feeding());
		model.put("medicalstaff",new MedicalStaff());
		model.put("feedingtype",new FeedingType());
		model.put("feedingshift",new FeedingShift());
		return "buscarf";
	}

	@RequestMapping("/buscarTipo")
	public String buscarTipo(Map<String, Object> model, @ModelAttribute FeedingType feedingtype) 
		throws ParseException
	{
		//vamos a buscar por nombre de paciente
		
		List<Feeding> listaAlimentacion;
		feedingtype.setNameTDA(feedingtype.getNameTDA());
		listaAlimentacion = fService.buscarTipo(feedingtype.getNameTDA());

		if (listaAlimentacion.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaPersonalMedicos",pmService.listar());
		model.put("listaAlimentacion", listaAlimentacion);
		model.put("listaPacientes",pService.listar());
		model.put("listaTipoAlimentacion", ftService.listar());
		model.put("listaTurnoAlimentacion", fsService.listar());
		
		model.put("patient", new Patient());
		model.put("feeding",new Feeding());
		model.put("medicalstaff",new MedicalStaff());
		model.put("feedingtype",new FeedingType());
		model.put("feedingshift",new FeedingShift());
		return "buscarf";
	}
	
	@RequestMapping("/buscarTurno")
	public String buscarTurno(Map<String, Object> model, @ModelAttribute FeedingShift feedingshift) 
		throws ParseException
	{
		//vamos a buscar por nombre de paciente
		
		List<Feeding> listaAlimentacion;
		feedingshift.setNameTRDA(feedingshift.getNameTRDA());
		listaAlimentacion = fService.buscarTurno(feedingshift.getNameTRDA());

		if (listaAlimentacion.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		model.put("listaPersonalMedicos",pmService.listar());
		model.put("listaAlimentacion", listaAlimentacion);
		model.put("listaPacientes",pService.listar());
		model.put("listaTipoAlimentacion", ftService.listar());
		model.put("listaTurnoAlimentacion", fsService.listar());
		
		model.put("patient", new Patient());
		model.put("feeding",new Feeding());
		model.put("medicalstaff",new MedicalStaff());
		model.put("feedingtype",new FeedingType());
		model.put("feedingshift",new FeedingShift());
		return "buscarf";
	}
}
