package com.servlets;

import java.io.IOException;


import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entities.Note;
import com.helper.FactoryProvider;

public class SaveNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveNoteServlet() {
		super();

	}


//if method is post  then doPost will execute and if ,,,if method is Get then doGet will execute
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		try {
			
			//title , content fetch
			
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			// will go to that constructor of note.java will below parameter 
			Note note=new Note(title,content,new Date());
			//System.out.println(note.getId() + " : " + 	note.getTitle());
			//Hibernate:save()
			Session s=FactoryProvider.geFactory().openSession();
			
			Transaction tx=s.beginTransaction();
			s.save(note);
			tx.commit();
			s.close();
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
		
			out.println("<h1 style='text-align:center;'>Note is added succesfully</h1>");
			out.println("<h1 style='text-align:center;'><a href='all_notes.jsp'>View All Notes</a></h1>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
