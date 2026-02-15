package com.example.demo.controller;

import com.example.demo.dto.PartidoDTO;
import com.example.demo.dto.TablaDTO;
import com.example.demo.model.Equipo;
import com.example.demo.model.Perfil;
import com.example.demo.model.Usuario;
import com.example.demo.service.IEquipoService;
import com.example.demo.service.IJornadaService;
import com.example.demo.service.IUsuariosService;
import com.example.demo.service.PartidosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private IUsuariosService serviceUsuarios;
    @Autowired
    private PartidosService servicePartidos;


    @Autowired
    @Qualifier("equipoServiceImp")
    private IEquipoService serviceEquipo;

    @Autowired
    @Qualifier("jornadaServiceImp")
    private IJornadaService serviceJornada;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String mostraHome(Model model) {
        return "home";
    }

    @GetMapping("/index")
    public String mostrarIndex(Authentication authentication, Model model, HttpSession session) {
        // Como el usuario ya ingreso, ya podemos agregar a la sesion el objeto usuario
        String username = authentication.getName();

        for(GrantedAuthority rol: authentication.getAuthorities()){
            System.out.println("ROL: "+ rol.getAuthority());
        }

        if (session.getAttribute("usuario") == null){

        }

        int jornadaActual = 12;

        List<PartidoDTO> resultados = servicePartidos.buscarPartidosDTO(jornadaActual);
        List<TablaDTO> tabla = servicePartidos.calcularTablaGeneral(jornadaActual);

        model.addAttribute("resultados", resultados);
        model.addAttribute("tabla", tabla);

        return "home";
    }


    @GetMapping("/signup")
    public String registrarse(Usuario usuario){
        return "formRegistro";
    }

    @PostMapping("/signup")
    public String guardarRegistro(Usuario usuario, RedirectAttributes attributes){
        // Recuperamos la contraseña en texto plano
        String pwdPlano = usuario.getPassword();
        // Encriptamos la contraseña
        String pwdEncriptado = passwordEncoder.encode(pwdPlano);
        //Hacemos un set al atributo password
        usuario.setPassword(pwdEncriptado);
        usuario.setEstatus(1);
        usuario.setFechaRegistro(new Date());

        // Creamo el perfil al usuario nuevo
        Perfil perfil = new Perfil();
        perfil.setId(1);
        usuario.agregar(perfil);

        /**
         * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
         */
         serviceUsuarios.guardar(usuario);
         attributes.addFlashAttribute("msg", "Has sido registrado");

         return "redirect:/login";
    }


    @GetMapping("/serach")
    public String buscar(@ModelAttribute("search")Equipo equipo, Model model){
        // Personalizamos el tipo de busqueda...
        ExampleMatcher matcher  = ExampleMatcher.matching().
                // and descripcion like '%?%'
                        withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<Equipo> example = Example.of(equipo, matcher);
        List<Equipo> lista = serviceEquipo.buscarByExample(example);
        model.addAttribute("usuarios", lista);
        return "home";
    }

    @GetMapping("/about")
    public String mostrarAcerca(){
        return "acerca";
    }

    @GetMapping("/login")
    public String mostrarLogin(){
        return "formLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "home";
    }

    @GetMapping("/bcrypt/{texto}")
    @ResponseBody
    public String encriptar(@PathVariable("texto") String texto){
        return texto + " Enciptado en Bcrypt: " +passwordEncoder.encode(texto);
    }
    /**
     * InitBinder para Strings si los detecta vacios en el Data Binding los settea a NULL
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    /**
     * Metodo que agrega al modelo datos genéricos para todo el controlador
     * @param model
     */
    @ModelAttribute
    public void setGenericos(Model model){
        Equipo equipoSearch = new Equipo();
        equipoSearch.reset();
        model.addAttribute("search", equipoSearch);
        model.addAttribute("jornada", serviceJornada.buscarDestacadas());
        model.addAttribute("equipo", serviceEquipo.buscarTodas());
    }
}
