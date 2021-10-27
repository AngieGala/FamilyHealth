package pe.edu.upc.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.upc.spring.service.ITypeMedicalStaffService;


@Controller
@RequestMapping("/typemedicalstaff")
public class TypeMedicalStaffController {

	@Autowired
	private ITypeMedicalStaffService tmsService;


	@RequestMapping("/bienvenido")
	public String irSVBienvenido() {
		return "bienvenido";
	}


	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaTipoPersonalMedico", tmsService.listar());
		return "listTypeMedicalStaff";
	}

	

}
