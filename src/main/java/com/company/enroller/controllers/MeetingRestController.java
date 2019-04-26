package com.company.enroller.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.enroller.model.Meeting;
import com.company.enroller.model.Participant;
import com.company.enroller.persistence.MeetingService;
import com.company.enroller.persistence.ParticipantService;

@RestController
@RequestMapping("/meetings")
public class MeetingRestController {

	@Autowired
	MeetingService meetingService;
	
	@Autowired
	ParticipantService participantService;

	//Lista wszystkich spotkań - pobieranie BASIC_1
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<?> getMeeting(){
		Collection<Meeting> meetings = meetingService.getAll();
		return new ResponseEntity<Collection<Meeting>>(meetings, HttpStatus.OK);
	}
	
	//Lista pojedyńczego spotkania - BASIC_2
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getOneMeeting(@PathVariable("id") long id){
		Meeting meeting = meetingService.findMeetingId(id);
		if (meeting == null){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
	}
		
	//Dodawanie spotkań - BASIC_3
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> addMeeting(@RequestBody Meeting meeting){
		Meeting findMeeting = meetingService.findMeetingId(meeting.getId());
		if (findMeeting != null){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		meetingService.addition(meeting);
		return new ResponseEntity<Meeting>(meeting, HttpStatus.CREATED);
		}	
			
	// Usuwanie spotkań - GOLD_1
			@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
			public ResponseEntity<?> deleteMeeting(@PathVariable("id") long id){
				Meeting meeting = meetingService.findMeetingId(id);
				if(meeting == null){
					return new ResponseEntity(HttpStatus.NOT_FOUND);
				}
				meetingService.toDelete(meeting);
				return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
			}
}	     
	