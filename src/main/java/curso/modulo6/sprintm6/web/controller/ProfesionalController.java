package curso.modulo6.sprintm6.web.controller;

import curso.modulo6.sprintm6.domain.service.ProfesionalService;
import curso.modulo6.sprintm6.domain.service.UsuarioService;
import curso.modulo6.sprintm6.persistence.entity.Profesional;
import curso.modulo6.sprintm6.persistence.entity.Usuario;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * curso.modulo6.sprintm6.web.controller
 *
 * @author Sergio Teran on 09-08-2022
 */

@Controller
@RequestMapping("/profesional")
public class ProfesionalController {

    Log logger = LogFactory.getLog(InicioController.class);

    private final ProfesionalService service;
    private final UsuarioService uService;


    public ProfesionalController(ProfesionalService service, UsuarioService uService) {
        this.service = service;
        this.uService = uService;
    }

    @GetMapping("/listar")
    public String profesionalList(Model model){
        model.addAttribute("profesional", service.getAll());
        return "profesionalList";
    }

    @GetMapping("/edit/{usuarioId}")
    public String profesionalEdit(@PathVariable("usuarioId") int usuarioId, Model model){
        Usuario u = new Usuario();
        u = uService.getOne(usuarioId).orElse(null);
        model.addAttribute("nombreUsuario", u.getUsuarioNombre());
        model.addAttribute("fechaNacimiento", u.getUsuarioFechaNac());
        model.addAttribute("profesional", service.getOneByUsuario(usuarioId));
        return "profesionalEdit";
    }

    @PostMapping("/save")
    public String profesionalSave(@ModelAttribute Profesional profesional){
        service.save(profesional);
        return "redirect:/usuario/listar";
    }


}
