package com.flight.reser.controller;

import com.flight.reser.entity.Reservation;
import com.flight.reser.payload.ReservationDTO;
import com.flight.reser.payload.ReservationUpdateRequestDto;
import com.flight.reser.service.PdfService;
import com.flight.reser.service.ReservationService;
import com.flight.reser.util.EmailService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final PdfService pdfService;
    private final EmailService emailService;

    @Autowired
    public ReservationController(ReservationService reservationService, PdfService pdfService, EmailService emailService) {
        this.reservationService = reservationService;
        this.pdfService = pdfService;
        this.emailService = emailService;
    }

   // http://localhost:8080/api/reservations
    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        ReservationDTO reservation = reservationService.createReservation(reservationDTO);
        //return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
        return new ResponseEntity<>(reservation,HttpStatus.OK);
    }
    // http://localhost:8080/api/reservations/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable int id) {
        ReservationDTO reservation = reservationService.getReservation(id);
        if (reservation != null) {
            return ResponseEntity.ok(reservation);
        }
        return ResponseEntity.notFound().build();
    }
   // http://localhost:8080/api/reservations/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable int id, @RequestBody ReservationUpdateRequestDto reservationDTO) {
        ReservationDTO updatedReservation = reservationService.updateReservation(id, reservationDTO);
        if (updatedReservation != null) {
            return ResponseEntity.ok(updatedReservation);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

    // http://localhost:8080/api/reservations/{id}/download
    @GetMapping("/{id}/download")
    public ResponseEntity<?> downloadReservationPdf(@PathVariable int id) throws DocumentException {
        ReservationDTO reservation = reservationService.getReservation(id);
        byte[] pdfData = pdfService.generateFlightReservationPDF(reservation);
        ByteArrayResource resource = new ByteArrayResource(pdfData);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reservation_" + id + ".pdf");

        // Send the PDF document as an email attachment to the passenger
        try {
            String subject = "Your Flight Reservation Details";
            String text = "Dear " + reservation.getPassenger().getFirstName()+ " " +reservation.getPassenger().getLastName()+ ",\n\n Here are your flight reservation details. Please find the attached PDF for more information.";
            emailService.sendEmailWithAttachment(reservation.getPassenger().getEmail(), subject, text, "reservation_" + id + ".pdf", pdfData);
            return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
        } catch (MessagingException ex) {
            // handle the exception here
            return new ResponseEntity<>("Error in sending email: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(resource);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}