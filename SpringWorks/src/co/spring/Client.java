package co.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Client {
	public static void main(String[] args) 
	{
		Employee emp = new Employee();
		emp.setId(0);
		emp.setEmail("p@gmail.com");
		emp.setName("p");
		emp.setSalary(300);
		emp.setDept("comp");
		
		System.out.println(emp);
		
		
		//spring way : Inversion Of Controle : Creates object when we request 
		
		Resource re = new ClassPathResource("empbean.xml");
		BeanFactory bf = new XmlBeanFactory(re);
		Employee emp1 = (Employee) bf.getBean("e1");
		Employee emp3 = (Employee) bf.getBean("e1");	//scope = prototype example
		System.out.println(emp1);System.out.println(emp3);
		
		
		//app context with scope default singleton
		
		ApplicationContext context=  new ClassPathXmlApplicationContext("empbean.xml");
		Employee emp2 = (Employee) context.getBean("e2");
		Employee emp4 = (Employee) context.getBean("e2");
		System.out.println(emp2);
		System.out.println(emp4);
	}
}
