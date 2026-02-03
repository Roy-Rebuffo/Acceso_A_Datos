package com.roy.controller;

import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.roy.model.Perfile;
import com.roy.model.Usuario;
import com.roy.service.IUsuariosService;

@Controller
public class HomeController {

    @Autowired
    private IUsuariosService serviceUsuarios;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Pantalla de inicio (Dashboard limpio)
     */
    @GetMapping("/")
    public String mostrarHome(Model model) {
        model.addAttribute("mensaje", "Bienvenido al Sistema de Gestión SCOTT");
        model.addAttribute("fecha", new Date());
        return "home";
    }

    /**
     * Método que gestiona el post-login. 
     * Aquí se puede guardar al usuario en sesión para tener sus datos a mano.
     */
    @GetMapping("/index")
    public String mostrarIndex(Authentication authentication, HttpSession session) {        
        String username = authentication.getName();     
        
        if (session.getAttribute("usuario") == null){
            // Buscamos el usuario en la base de datos
            Usuario usuario = serviceUsuarios.buscarPorUsername(username);
            if (usuario != null) {
                usuario.setPassword(null); // Seguridad: quitamos el hash de la sesión
                session.setAttribute("usuario", usuario);
            }
        }
        return "redirect:/";
    }

    /**
     * Muestra el formulario de registro
     */
    @GetMapping("/signup")
    public String registrarse(Usuario usuario) {
        return "formRegistro";
    }

    /**
     * Procesa el registro de un nuevo usuario
     */
    @PostMapping("/signup")
    public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
        // Encriptamos la contraseña antes de guardar
        String pwdEncriptado = passwordEncoder.encode(usuario.getPassword()); 
        usuario.setPassword(pwdEncriptado); 
        
        usuario.setEstatus(1); // Activo por defecto
        usuario.setFechaRegistro(new Date());
        
        // Asignamos el perfil de "USUARIO" (ID 3 según tu lógica)
        Perfile perfil = new Perfile();
        perfil.setId(3);
        usuario.agregar(perfil);
        
        serviceUsuarios.guardar(usuario);
        attributes.addFlashAttribute("msg", "¡Registro completado! Ya puedes loguearte.");
        return "redirect:/login";
    }

    /**
     * Muestra el formulario de login personalizado
     */
    @GetMapping("/login")
    public String mostrarLogin() {
        return "formLogin";
    }

    /**
     * Gestiona el cierre de sesión
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/";
    }

    /**
     * Utility para limpiar strings vacíos en los formularios (los convierte en null)
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}