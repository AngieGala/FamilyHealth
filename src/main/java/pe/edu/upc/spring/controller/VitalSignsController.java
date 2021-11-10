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
import pe.edu.upc.spring.model.Patient;
import pe.edu.upc.spring.model.VitalSigns;
import pe.edu.upc.spring.service.IMedicalStaffService;
import pe.edu.upc.spring.service.IPatientService;
import pe.edu.upc.spring.service.IVitalSignsService;

@Controller
@RequestMapping("/vitalsigns")
public class VitalSignsController {

	@Autowired
	private IVitalSignsService vsService;

	@Autowired
	private IPatientService pService;
	
	@Autowired
	private IMedicalStaffService pmService;

	@RequestMapping("/bienvenido")
	public String irSVBienvenido() {
		return "login";
	}

	@RequestMapping("/")
	public String irSV(Map<String, Object> model) {
		model.put("listaSignosVitales", vsService.listar());
		return "listVitalSigns";
	}

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {

		model.addAttribute("listaPacientes", pService.listar());
		model.addAttribute("listaPersonalMedicos", pmService.listar());

		model.addAttribute("vitalsigns", new VitalSigns());
		model.addAttribute("patient", new Patient());
		model.addAttribute("medicalstaff", new MedicalStaff());

		return "vitalsigns";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute VitalSigns objVitalSigns, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaPacientes", pService.listar());
			model.addAttribute("listaPersonalMedicos", pmService.listar());
			return "vitalsigns";
		} else {
			boolean flag = vsService.grabar(objVitalSigns);
			if (flag) {
				return "redirect:/vitalsigns/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/vitalsigns/irRegistrar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<VitalSigns> objVitalSigns = vsService.listarId(id);
		if (objVitalSigns == null) {
			objRedir.addAttribute("mensaje", "Ocurrio un error");
			return "redirect:/vitalsigns/listar";
		} else {
			model.addAttribute("listaPacientes", pService.listar());
			model.addAttribute("listaPersonalMedicos", pmService.listar());
			if (objVitalSigns.isPresent())
				objVitalSigns.ifPresent(o -> model.addAttribute("vitalsigns", o));
			return "vitalsigns";
		}
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				vsService.eliminar(id);
				model.put("listaSignosVitales", vsService.listar());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaSignosVitales", vsService.listar());
		}
		return "listVitalSigns";
	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaSignosVitales", vsService.listar());
		return "listVitalSigns";
	}

	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute VitalSigns vitalsigns) throws ParseException {
		vsService.listarId(vitalsigns.getIdSV());
		return "listVitalSigns";
	}

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("patient", new Patient());
		model.addAttribute("medico", new MedicalStaff());
		model.addAttribute("vitalsigns", new VitalSigns());
		
		model.addAttribute("listaPersonalMedicos",pmService.listar());
		model.addAttribute("listaPacientes", pService.listar());
        model.addAttribute("listaSignosVitales", vsService.listar());
		return "buscarvs";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute("patient") Patient patient) throws ParseException {
		
		// vamos a buscar por nombre de paciente

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
        model.put("listaPersonalMedicos", pmService.listar());
		
		return "buscarvs";
	}
	
	@RequestMapping("/buscarPM")
	public String buscarpersonalmedico(Map<String, Object> model, @ModelAttribute("medico") MedicalStaff medico, BindingResult binRes)
			throws ParseException {
		// vamos a buscar por fecha en cual se hizo el control

		List<VitalSigns> listaSignosVitales;
		medico.setNamePM(medico.getNamePM());
		listaSignosVitales = vsService.buscarPersonalMedico(medico.getNamePM()); // buscando uwu

		if (listaSignosVitales.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}

		model.put("patient", new Patient());
        model.put("medico", new MedicalStaff());
        model.put("vitalsigns", new VitalSigns());

        model.put("listaSignosVitales", listaSignosVitales);
        model.put("listaPacientes", pService.listar());
        model.put("listaPersonalMedicos", pmService.listar());
		
		return "buscarvs";
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
        model.put("listaPersonalMedicos", pmService.listar());
		
		return "buscarvs";
	}
	
	
}
