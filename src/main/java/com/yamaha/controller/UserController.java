package com.yamaha.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yamaha.config.UseEmail;
import com.yamaha.entities.User;
import com.yamaha.service.UserService;

@Controller
public class UserController {
	
	
	@Autowired
	UserService service;
	
	 @RequestMapping(value = "/welcome", method = RequestMethod.GET)
	 public String welcome() {
		 return "welcome";
	 }
	 
	 @GetMapping(value="/register")
	 public String register() {
		 return "register";
	 }
	 
	 @PostMapping("/register")
	 public String registerSuccess(@ModelAttribute User user)throws AddressException, MessagingException, IOException {
		 user.setRoles("CUSTOMER");
		 User u=service.insertUser(user);
		 //sendEmail(u.getEmail());
		 return "login";
	 }
	 
	 

	@GetMapping(value="/login")
	 public String login() {
		 return "login";
	 }
	 
	 @PostMapping("/login")
	 public ModelAndView loginSuccess(@ModelAttribute User user,HttpServletRequest request) {
		 String username=user.getUsername();
		 User loggedUser=service.viewUser(username);
		 ModelAndView mv=new ModelAndView();
		 mv.addObject("User", loggedUser);
		if(loggedUser!=null) {
		 HttpSession hs=request.getSession();
		 hs.setAttribute("username",username );
		 hs.setAttribute("roles", loggedUser.getRoles());
		 mv.addObject("Username", username);
		 if(loggedUser.getRoles().equals("ADMIN")) {
			 mv.setViewName("adminDashboard");
		 }else {
			 mv.addObject("errorMsg","You are not a registered user");
			 mv.setViewName("custDashboard");
		 }
		}
		else {
			mv.addObject("Username","You are not logged in");
			mv.setViewName("login");
		}
		 return mv;
	 }
	 
	
	 @PostMapping("/delete")
	 public String deleteUser(@RequestParam String username) {
		 
		 service.deleteUser(username);
		 return "adminDashboard";
	 }
	 @ResponseBody
	 @PostMapping("/credit")
	 public ModelAndView creditUser(@RequestParam(name="username") String un,@RequestParam(name="amount") double amt) {
		 ModelAndView mv=new ModelAndView();
		 int i=service.credit(un, amt);
		 if(i==1) {
			 mv.addObject("credMsg","You are not a registered user");
		 }else {
			 mv.addObject("errorMsg","You are not a registered user");
		 }
		 mv.addObject("Username",un);
		 User loggedUser=service.viewUser(un);
		 mv.addObject("balance",loggedUser.getAmount());
		 mv.setViewName("custDashboard");
		 return mv;
	 }
	 @PostMapping("/debit")
	 public ModelAndView debitUser(@RequestParam(name="username") String un,@RequestParam(name="amount") double amt) {
		 ModelAndView mv=new ModelAndView();
		 int i=service.debit(un, amt);
		 
		 if(i==1) {
			 mv.addObject("debtMsg","You are not a registered user");
		 }else {
			 mv.addObject("errorMsg","You are not a registered user");
		 }
		 mv.addObject("Username",un);
		 User loggedUser=service.viewUser(un);
		 mv.addObject("balance",loggedUser.getAmount());
		 mv.setViewName("custDashboard");
		 return mv;
	 }
	 @PostMapping("/transfer")
	 public ModelAndView tranferUser(@RequestParam(name="username1") String un1,@RequestParam(name="username2") String un2,@RequestParam(name="amount") double amt) {
		 ModelAndView mv=new ModelAndView();
		 if(un1.equals(un2)) {
			 service.debit(un2, amt);
		 }
		 int i=service.tranfer(un1,un2,amt);
		 if(i==2) {
			 mv.addObject("transMsg","You are not a registered user");
		 }else {
			 mv.addObject("errorMsg","You are not a registered user");
		 }
		 mv.addObject("Username",un1);
		 User loggedUser=service.viewUser(un1);
		 mv.addObject("balance",loggedUser.getAmount());
		 mv.setViewName("custDashboard");
		 return mv;
	 }
	 
	 @GetMapping("/custDashboard")
	 public String showCust(HttpServletRequest request) {
		HttpSession hs=request.getSession(false);
		if(hs.getAttribute("username")!=null) {
			return "custDashboard";
		}
		else {
			return "redirect:/login";
		}
		 
	 }
	 
	 @GetMapping("/adminDashboard")
	 public String showAdmin(HttpServletRequest request) {
		HttpSession hs=request.getSession(false);
		if(hs.getAttribute("username")!=null) {
			return "adminDashboard";
		}
		else {
			return "redirect:/login";
		}
		 
	 }
	 
	 @GetMapping("/logout")
	 public String logoutUser(HttpServletRequest request) {
		 HttpSession hs=request.getSession(false);
		 if(hs!=null) {
			 hs.invalidate();
			 return "welcome";
		 }else {
			 return "login";
		 }
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
		/*
		 * @GetMapping("/all") public List<User> showAll(){ return service.showAll(); }
		 * 
		 * @PostMapping("/insert") public User saveUser(@RequestBody User user) { return
		 * service.insertUser(user); }
		 * 
		 * @GetMapping("/show/{username}") public User
		 * showOneAccount(@PathVariable("username") String username) { return
		 * service.viewUser(username); }
		 * 
		 * @PutMapping("/credit/{username}/{amt}") public double
		 * addMoney(@PathVariable("username") String username,@PathVariable("amt")
		 * double amt) { return service.credit(username, amt); }
		 * 
		 * @PutMapping("/debit/{username}/{amt}") public double
		 * removeMoney(@PathVariable("username") String username,@PathVariable("amt")
		 * double amt) { return service.debit(username, amt); }
		 * 
		 * @PutMapping("/transfer/{username1}/{username2}/{amt}") public double
		 * transferMoney(@PathVariable("username1") String
		 * username1,@PathVariable("username2") String username2,@PathVariable("amt")
		 * double amt) { return service.tranfer(username1, username2, amt); }
		 * 
		 * private void sendEmail(String emailname) throws AddressException,MessagingException,IOException{
			// TODO Auto-generated method stub
			 Properties props = new Properties();
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.starttls.enable", "true");
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.port", "587");
			   
			   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				      protected PasswordAuthentication getPasswordAuthentication() {
				         return new PasswordAuthentication("utkarsh51k@gmail.com", "shreyansh2000");
				      }
				   });
				   Message msg = new MimeMessage(session);
				   
				   msg.setFrom(new InternetAddress("utkarsh51k@gmail.com", false));
				   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailname));
				   msg.setSubject("Welcome from Utkarsh Bank");
				   msg.setContent("You are successfully registered", "text/html");
				   msg.setSentDate(new Date());

				   Transport.send(msg);
		}
		 */
}
