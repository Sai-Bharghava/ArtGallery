package com.sdp.artgallery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sdp.artgallery.model.Admin;
import com.sdp.artgallery.model.Customer;
import com.sdp.artgallery.repository.AdminRepository;
import com.sdp.artgallery.repository.CustomerRepository;
import com.sdp.artgallery.repository.SellerRepository;
import com.sdp.artgallery.service.AdminService;
import com.sdp.artgallery.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ClientController 
{
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CustomerService customerService;
	@GetMapping("/")
	public String showMainPage()
	{
        return "index"; 
    }
	
	 
	@GetMapping("/login")
	public ModelAndView showloginPage()
	{
       ModelAndView mv= new ModelAndView();
       mv.setViewName("login");
       return mv;
    }
	
	@GetMapping("/admindashboard")
	public ModelAndView admindashboard()
	{
       ModelAndView mv= new ModelAndView();
       mv.setViewName("admindashboard");
       return mv;
    }
	
	@GetMapping("/aboutus")
	public String aboutus()
	{
        return "aboutus"; 
    }
	@GetMapping("/customerhome")
	public String customerhome()
	{
        return "customerhome"; 
    }
	@GetMapping("/userhomepaintings")
	public String userhomepaintings()
	{
        return "userhomepaintings"; 
    }
	@GetMapping("/userhomephotography")
	public String userhomephotography()
	{
        return "userhomephotography"; 
    }
	@GetMapping("/userhomedrawings")
	public String userhomedrawings()
	{
        return "userhomedrawings"; 
    }
	
	
	@GetMapping("/registration")
	public ModelAndView registration()
	{
        ModelAndView mv= new ModelAndView();
        mv.setViewName("registration");
        return mv;
    }
	@GetMapping("/AbstractDrawing")
	public String abstractdrawing()
	{
        return "AbstractDrawing"; 
    }
	@GetMapping("/abstractpainting")
	public String abstractpainting()
	{
        return "abstractpainting"; 
    }
	@GetMapping("/realismpaintings")
	public String realismpaintings()
	{
        return "realismpaintings"; 
    }
	@GetMapping("/fineartdrawing")
	public String fineartdrawing()
	{
        return "fineartdrawing"; 
    }
	@GetMapping("/ImpressionismPaintings")
	public String ImpressionismPaintings()
	{
        return "ImpressionismPaintings"; 
    }
	@GetMapping("/documentaryphotography")
	public String documentaryphotography()
	{
        return "documentaryphotography"; 
    }
	@GetMapping("/grafittidrawings")
	public String grafittidrawings()
	{
        return "grafittidrawings"; 
    }
	@GetMapping("/adminlogin")
	public ModelAndView adminlogin()
	{
		ModelAndView mv= new ModelAndView();
		mv.setViewName("adminlogin");
        return mv;
    }
	@GetMapping("/abstractphotography")
	public String abstractphotography()
	{
        return "abstractphotography"; 
    }
	@GetMapping("/conceptualphotography")
	public String conceptualphotography()
	{
        return "conceptualphotography"; 
    }

	@GetMapping("/photography")
	public String photography()
	{
        return "photography"; 
    }
	@GetMapping("/drawings")
	public String drawings()
	{
        return "drawings"; 
    }
	@GetMapping("/paintings")
	public String paintings()
	{
        return "paintings"; 
    }
    @PostMapping("insertcustomer")
	public ModelAndView insertcustomer(HttpServletRequest request )
	{
    	String msg="null";
    	ModelAndView mv= new ModelAndView();
    	try
    	{
    		String name=request.getParameter("name");
    		String gender=request.getParameter("gender");
    		String email=request.getParameter("email");
    		String password=request.getParameter("password");
    		
    		
    		Customer customer=new Customer();
    		customer.setName(name);
    		customer.setGender(gender);
    		customer.setEmail(email);
    		customer.setPassword(password);
    		
    		
    		msg=customerService.addcustomer(customer);
    		mv.setViewName("registration");
    		mv.addObject("message", msg);
    		
    	}
    	catch(Exception e)
    	{
    		msg=e.getMessage();
    		mv.setViewName("registration");
    		mv.addObject("message", msg);
    	}
    	mv.setViewName("registration");
    	return mv;
	}
    @GetMapping("viewcustomers")
    public ModelAndView viewcustomers()
    {
    	List<Customer> customerlist= adminService.viewallcustomers();
    	
    	ModelAndView mv= new ModelAndView("viewcustomers");
    	mv.addObject("customerlist", customerlist);
    	return mv;
    }
    @GetMapping("deletecustomers")
    public ModelAndView deletecustomers()
    {
    	List<Customer> customerlist= adminService.viewallcustomers();
    	
    	ModelAndView mv= new ModelAndView("deletecustomers");
    	mv.addObject("customerlist", customerlist);
    	return mv;
    }
    @PostMapping("checkcustomerlogin")
    public ModelAndView checkcustomerlogin(HttpServletRequest request)
    {
    	ModelAndView mv= new ModelAndView();
    	String email= request.getParameter("email");
    	String password= request.getParameter("password");
    	Customer customer= customerService.checkcustomerlogin(email, password);
    	if(customer!=null)
    	{
    		mv.setViewName("customerhome");
    	}
    	else
    	{
    		mv.setViewName("login");
    		mv.addObject("message", "Login Failed");
    	}
    	return mv;
    	
    }
    @PostMapping("checkadminlogin")
    public ModelAndView checkadminlogin(HttpServletRequest request)
    {
    	ModelAndView mv= new ModelAndView();
    	String adminusername= request.getParameter("adminusername");
    	String adminpassword= request.getParameter("adminpassword");
    	Admin admin= adminService.checkadminlogin(adminusername, adminpassword);
    	  if(admin!=null)
  	    {
  	    	 long ccount=adminService.customercount();
  			 
  	      mv.setViewName("admindashboard");
  	      
  	      mv.addObject("ccount", ccount);
  	      
  	    }
  	    else
  	    {
  	      mv.setViewName("adminlogin");
  	      mv.addObject("message", "Login Failed");
  	    }
  	    
  	    return mv;
    	
    }
    
   @GetMapping("delete/{id}")
   public String deleteaction(@PathVariable("id") int cid)
   {
	   adminService.deletecustomer(cid);
	   return "redirect:/viewcustomers";
   }
   @GetMapping("deletecustomer")
   public ModelAndView deletecustomerdemo()
   {
	   ModelAndView mv= new ModelAndView();
	   mv.setViewName("deletecustomers");
	   List<Customer> customerlist= adminService.viewallcustomers();
	   mv.addObject("customerlist", customerlist);
   	return mv;
	   
   }
   @GetMapping("customerlogout")
	  public ModelAndView customerlogout()
	  {
		  ModelAndView mv= new ModelAndView();
		  mv.setViewName("customerlogin");
		  mv.addObject("message", "Logout Succesfull");
		  return mv;
	  }
}
