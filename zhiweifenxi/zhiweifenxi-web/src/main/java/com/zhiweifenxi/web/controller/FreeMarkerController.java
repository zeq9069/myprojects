package com.zhiweifenxi.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * freemarker controller
 * @author kyrin
 *
 */
@Controller
@RequestMapping(value="freemarker")
public class FreeMarkerController {
	
	@Autowired
	private FreeMarkerConfigurer freemarkerConfig;
	
	@RequestMapping(value="test1",method=RequestMethod.GET)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void resolver(HttpServletRequest request,HttpServletResponse response){
		Configuration conf;
		Template template=null;
		Map root =new HashMap();
		Map user=new HashMap();
		user.put("name", "kyrin");
		user.put("age", 32);
		user.put("birthday", "1992-6");
		root.put("user", user);
		try {
			conf = freemarkerConfig.createConfiguration();
			Writer out=response.getWriter();
			template=conf.getTemplate("test1.ftl");
			template.process(root, out);
		} catch (IOException e) {
			e.printStackTrace();
		}catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}
