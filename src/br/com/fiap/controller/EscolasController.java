package br.com.fiap.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.fiap.dao.JdbcEscolaDao;
import br.com.fiap.entidades.Escola;

@Controller
public class EscolasController {
	
	@RequestMapping("escola/cadastro")
	public String incluir(){
		return "cadastro/incluirEscola";
	}
	
	@RequestMapping(value="/cadEscola", method=RequestMethod.POST)
	public 	String incluir(Escola escola, ModelMap modelMap){
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext("beanJdbc.xml");
			
			JdbcEscolaDao escolaDao = (JdbcEscolaDao) ctx.getBean("jdbcEscolaDAO");
			escolaDao.incluirEscola(escola);
			
			modelMap.addAttribute("msg", "Escola " + escola.getDescricao() + " incluída com sucesso!");
			
			return "menu"; 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			modelMap.addAttribute("erro", e.getMessage());
			return "erro";
		}
		
	}

}
