package br.com.fiap.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.fiap.dao.JdbcCursoDao;
import br.com.fiap.dao.JdbcEscolaDao;
import br.com.fiap.entidades.Curso;

@Controller
public class CursoController {
	
	@RequestMapping("/curso/cadastro")
	public String incluir(ModelMap model){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beanJdbc.xml");
		JdbcEscolaDao escolaDao = (JdbcEscolaDao) ctx.getBean("jdbcEscolaDAO");
		try {
			model.addAttribute("escolas", escolaDao.listarEscolas());
			return "cadastro/incluirCurso";
		} catch (Exception e) {
			model.addAttribute("erro", e.getMessage());
			return "erro";
		}
	}
	
	@RequestMapping(value="/cadCurso", method=RequestMethod.POST)
	public String incluir(@RequestParam("idc")int idc, Curso curso, ModelMap model){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beanJdbc.xml");
		JdbcEscolaDao escolaDao = (JdbcEscolaDao) ctx.getBean("jdbcEscolaDAO");
		JdbcCursoDao cursoDao = (JdbcCursoDao) ctx.getBean("jdbcCursoDAO");
		try {
			curso.setEscola(escolaDao.buscarEscola(idc));
			cursoDao.incluirCurso(curso);
			model.addAttribute("msg", "Curso " + curso.getDescricao() + " incluído com sucesso!");
			return "menu"; 
		} catch (Exception e) {
			model.addAttribute("erro", e.getMessage());
			return "erro";
		}
	}

}
