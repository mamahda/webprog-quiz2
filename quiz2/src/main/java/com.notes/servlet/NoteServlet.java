package com.notes.servlet;

import com.notes.dao.NoteDAO;
import com.notes.model.Note;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class NoteServlet extends HttpServlet {
    private NoteDAO noteDAO;

    public void init() { noteDAO = new NoteDAO(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
switch (action) {
            case "/new": showNewForm(request, response); break;
            case "/insert": insertNote(request, response); break;
            case "/delete": deleteNote(request, response); break;
            case "/deleteAll": deleteAllNotes(request, response); break;
            case "/edit": showEditForm(request, response); break;
            case "/update": updateNote(request, response); break;
            case "/pin": togglePin(request, response); break;
            case "/search": searchNotes(request, response); break;
            case "/notes": listNotes(request, response); break;
            default: show404(request, response); break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void listNotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Note> listNotes = noteDAO.selectAllNotes();
        request.setAttribute("listNotes", listNotes);
        request.getRequestDispatcher("view.jsp").forward(request, response);
    }

    private void searchNotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Note> listNotes = noteDAO.searchNotes(keyword);
        request.setAttribute("listNotes", listNotes);
        request.getRequestDispatcher("view.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Note existingNote = noteDAO.selectNote(id);
        request.setAttribute("note", existingNote);
        request.getRequestDispatcher("edit.jsp").forward(request, response);
    }

    private void insertNote(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        
        Note newNote = new Note(title, content, false); 
        
        noteDAO.insertNote(newNote);
        response.sendRedirect("notes");
    }

    private void updateNote(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        
        Note note = new Note(id, title, content);
        
        noteDAO.updateNote(note);
        response.sendRedirect("notes");
    }

    private void deleteNote(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        noteDAO.deleteNote(id);
        response.sendRedirect("notes");
    }

    private void deleteAllNotes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        noteDAO.deleteAllNotes();
        response.sendRedirect("notes");
    }

    private void togglePin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        noteDAO.togglePin(id);
        response.sendRedirect("notes");
    }

    private void show404(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        request.getRequestDispatcher("404.jsp").forward(request, response);
    }
}