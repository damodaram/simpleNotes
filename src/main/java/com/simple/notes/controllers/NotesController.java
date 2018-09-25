package com.simple.notes.controllers;

import com.simple.notes.entities.Notes;
import com.simple.notes.entities.User;
import com.simple.notes.repository.contract.NoteRepository;
import com.simple.notes.repository.contract.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by damodaram.setti on 9/23/2018.
 */
@Controller
public class NotesController {
    @Autowired
    NoteRepository noteRepository;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(NotesController.class);

    @GetMapping("/invalidate")
    public String destroySession(HttpServletRequest request) {
        LOGGER.info("invalidate user session");
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/addNote")
    public String addNote(HttpServletRequest request, Model model) {
        LOGGER.info("get empty Note");
        if(!isUserExistInTheSession(request)){
            return "error";
        }
        model.addAttribute("note", new Notes());
        return "addNote";
    }

    @PostMapping("/addNote")
    public String addNote(HttpServletRequest request, @ModelAttribute("note") @Validated Notes note) {
        LOGGER.info("/addNote adding Note");
        if(!isUserExistInTheSession(request)){
            return "error";
        }
        note.setId(UUID.randomUUID().toString());
        note.setCreatedOn(new Date());
        note.setLastModifiedOn(new Date());
        noteRepository.save(note);
        return "redirect:/dashBoard";
    }

    @GetMapping("/dashBoard")
    public String dashBoard(Model model, HttpServletRequest request) {
        LOGGER.info("/dashBoard showing");
        if(!isUserExistInTheSession(request)){
            return "error";
        }
        List<Notes> notes = noteRepository.findAll();
        if(notes==null || notes.isEmpty()){
            return "redirect:/addNote";
        }
        model.addAttribute("notes",notes);
        return "dashboard";
    }

    @GetMapping("/deleteNote")
    public String deleteNote(HttpServletRequest request, Model model, @QueryParam("id") String id) {
        LOGGER.info("/deleting Note which has id "+id);
        if(!isUserExistInTheSession(request)){
            return "error";
        }
        noteRepository.delete(id);
        return "redirect:/dashBoard";
    }

    @GetMapping("/getNote")
    public String getNote(HttpServletRequest request, Model model, @QueryParam("id") String id) {
        LOGGER.info("/getting Note for "+id);
        if(!isUserExistInTheSession(request)){
            return "error";
        }
        model.addAttribute("note",noteRepository.find(id));
        return "updateNote";
    }

    @PostMapping("/updateNote")
    public String updateNote(HttpServletRequest request, @ModelAttribute("note") @Validated Notes note) {
        LOGGER.info("/updating Note which has id "+note);
        if(!isUserExistInTheSession(request)){
            return "error";
        }
        noteRepository.update(note.getTitle(),note.getDescription(),new Date(), note.getId());
        return "redirect:/dashBoard";
    }

    private boolean isUserExistInTheSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Object loginStatus = session.getAttribute("loginStatus");
        System.out.print("session var    "+String.valueOf(loginStatus));
        return loginStatus!=null;
    }
}
