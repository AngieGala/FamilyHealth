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

import pe.edu.upc.spring.model.FamilyPatient;
import pe.edu.upc.spring.service.IFamilyPatientService;

@Controller
@RequestMapping("/familypatient")
public class FamilyPatientController {
	
	@Autowired
	private IFamilyPatientService fpService;
	
	
	@RequestMapping("/bienvenido")
	public String irFPBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irFP(Map<String, Object> model) {
		model.put("listaFamiliar", fpService.listar());
		return "listFamilyPatient";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		
		model.addAttribute("familypatient", new FamilyPatient());
		
		return "familypatient";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute FamilyPatient objFamilyPatient, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors()) {
			return "familypatient";
		} 
		else {
			boolean flag = fpService.grabar(objFamilyPatient);
			if (flag) {
				return "redirect:/familypatient/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/familypatient/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException
		{
			Optional<FamilyPatient> objFamilyPatient = fpService.listarId(id);
			if (objFamilyPatient == null) {
				objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
				return "redirect:/familypatient/listar";
			}
			else {
				model.addAttribute("familypatient",objFamilyPatient);
				return "familypatient";
			}
		}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				fpService.eliminar(id);
				model.put("listaFamiliar", fpService.listar());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrió un error");
			model.put("listaFamiliar", fpService.listar());
		}
		return "listFamilyPatient";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaFamiliar", fpService.listar());
		return "listFamilyPatient";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute FamilyPatient familypatient) throws ParseException {
		fpService.listarId(familypatient.getIdFDP());
		return "listFamilyPatient";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model ) {
		model.addAttribute("familypatient", new FamilyPatient());
		
		model.addAttribute("listaFamiliar", fpService.listar());
		return "buscarfp";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute FamilyPatient familypatient ) throws ParseException, java.text.ParseException
	{
		//vamos a buscar por nombre, apellido, dni, o numero de cama
		
		List<FamilyPatient> listaFamiliar;
		familypatient.setNamePM(familypatient.getNamePM());//capturo lo de la caja de texto
		listaFamiliar = fpService.buscarNombre(familypatient.getNamePM()); //buscando 1
		
		if(listaFamiliar.isEmpty()) {
			listaFamiliar = fpService.buscarApellido(familypatient.getNamePM());
		}
		
	
		if(listaFamiliar.isEmpty()) {
			model.put("mensaje", "No se encontro");
		}
		
		model.put("familypatient", new FamilyPatient());
		model.put("listaFamiliar", listaFamiliar);
		return "buscarfp";
	}
		
}
