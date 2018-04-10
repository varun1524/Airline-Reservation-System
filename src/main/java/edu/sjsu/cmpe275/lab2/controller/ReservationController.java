package edu.sjsu.cmpe275.lab2.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.sjsu.cmpe275.lab2.service.ReservationService;
import edu.sjsu.cmpe275.lab2.view.ReservationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PostMapping
    public ResponseEntity makeReservation(@RequestParam Map<String, String> params){
        ResponseEntity responseEntity = null;
        try{
            responseEntity = reservationService.makeReservation(params);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return responseEntity;
    }

    @JsonView({ReservationView.summary.class})
    @GetMapping
    public ResponseEntity searchForReservation(@RequestParam Map<String, String> params){
        ResponseEntity responseEntity = null;
        try{
            responseEntity = reservationService.searchForReservation(params);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return responseEntity;
    }

    @JsonView({ReservationView.summary.class})
    @GetMapping(path = "/{reservationNumber}")
    public ResponseEntity fetchReservationById(@PathVariable String reservationNumber){
        ResponseEntity responseEntity = null;
        try{
            responseEntity = reservationService.findReservationsByID(reservationNumber);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return responseEntity;
    }

    @DeleteMapping(path = "/{reservationNumber}")
    public ResponseEntity deleteReservation(@PathVariable("reservationNumber") String reservationNumber){
        ResponseEntity responseEntity = null;
        try{
            responseEntity = reservationService.cancelReservation(reservationNumber);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return responseEntity;
    }
}
