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
import pe.edu.upc.spring.model.TypeMedicalStaff;
import pe.edu.upc.spring.service.IMedicalStaffService;

@Controller
@RequestMapping("/medicalstaff")
public class MedicalStaffController {
	
	@Autowired
	private IMedicalStaffService msService;
	
	
	@RequestMapping("/bienvenido")
	public String irSVBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irSV(Map<String, Object> model) {
		model.put("listaPersonalMedicos", msService.listar());
		return "listMedicalStaff";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		
		model.addAttribute("listaPacientes", msService.listar());
		
		model.addAttribute("medicalstaff", new MedicalStaff());
		model.addAttribute("typemedical", new TypeMedicalStaff());
		
		return "medicalstaff";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute MedicalStaff objMedicalStaff, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaPacientes", msService.listar());
			return "medicalstaff";
		} 
		else {
			boolean flag = msService.grabar(objMedicalStaff);
			if (flag) {
				return "redirect:/medicalstaff/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/medicalstaff/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<MedicalStaff> objMedicalStaff = msService.listarId(id);
		if (objMedicalStaff == null) {
			objRedir.addAttribute("mensaje", "Ocurrió un error");
			return "redirect:/medicalstaff/listar";
		} else {
			model.addAttribute("listaPacientes", msService.listar());
			if (objMedicalStaff.isPresent())
				objMedicalStaff.ifPresent(o -> model.addAttribute("medicalstaff", o));
			return "medicalstaff";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				msService.eliminar(id);
				model.put("listaPersonalMedicos", msService.listar());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrió un error");
			model.put("listaPersonalMedicos", msService.listar());
		}
		return "listMedicalStaff";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPersonalMedicos", msService.listar());
		return "listMedicalStaff";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute MedicalStaff medicalstaff) throws ParseException {
		msService.listarId(medicalstaff.getIdPM());
		return "listMedicalStaff";
	}
	

	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		
		return "buscarms";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute TypeMedicalStaff typemedical) 
		throws ParseException
	{
		
		return null;
	}

	
}
