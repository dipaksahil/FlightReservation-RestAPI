package com.flight.reser.service;

import com.flight.reser.payload.FlightDTO;
import com.flight.reser.payload.PassengerDTO;
import com.flight.reser.payload.ReservationDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {
    Font mainHeadFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
    Font headFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
    public byte[] generateFlightReservationPDF(ReservationDTO reservation) throws DocumentException {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);
        document.open();

        // Main Heading for the document
        Paragraph mainHeading = new Paragraph("Flight Reservation Details", mainHeadFont);
        mainHeading.setAlignment(Element.ALIGN_CENTER); //aligning main heading to center
        document.add(mainHeading);

        // Space after the main heading
        document.add(new Paragraph("\n"));

        // Heading for the Flight Details
        Paragraph flightDetailsHeading = new Paragraph("Flight Details", headFont);
        flightDetailsHeading.setSpacingAfter(10f);
        document.add(flightDetailsHeading);

        PdfPTable flightTable = createFlightTable(reservation.getFlight());
        document.add(flightTable);

        // Adding some space after the flight details
        document.add(new Paragraph("\n"));

        // Heading for the Passenger Details
        Paragraph passengerDetailsHeading = new Paragraph("Passenger Details", headFont);
        passengerDetailsHeading.setSpacingAfter(10f);
        document.add(passengerDetailsHeading);
        //document.add(Chunk.NEWLINE);

        PdfPTable passengerTable = createPassengerTable(reservation.getPassenger());
        document.add(passengerTable);

        document.close();

        return out.toByteArray();
    }

    private PdfPTable createFlightTable(FlightDTO flightDTO) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1, 2});

        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase("Flight Number", headFont));
        table.addCell(hcell);
        table.addCell(flightDTO.getFlightNumber());

        hcell = new PdfPCell(new Phrase("Operating Airlines", headFont));
        table.addCell(hcell);
        table.addCell(flightDTO.getOperatingAirlines());

        hcell = new PdfPCell(new Phrase("Departure City", headFont));
        table.addCell(hcell);
        table.addCell(flightDTO.getDepartureCity());

        hcell = new PdfPCell(new Phrase("Arrival City", headFont));
        table.addCell(hcell);
        table.addCell(flightDTO.getArrivalCity());

        hcell = new PdfPCell(new Phrase("Date of Departure", headFont));
        table.addCell(hcell);
        table.addCell(flightDTO.getDateOfDeparture().toString());

        hcell = new PdfPCell(new Phrase("Estimated Departure Time", headFont));
        table.addCell(hcell);
        table.addCell(flightDTO.getEstimatedDepartureTime().toString());

        return table;
    }

    private PdfPTable createPassengerTable(PassengerDTO passengerDTO) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{1, 2});

        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase("First Name", headFont));
        table.addCell(hcell);
        table.addCell(passengerDTO.getFirstName());

        hcell = new PdfPCell(new Phrase("Last Name", headFont));
        table.addCell(hcell);
        table.addCell(passengerDTO.getLastName());

        hcell = new PdfPCell(new Phrase("Email", headFont));
        table.addCell(hcell);
        table.addCell(passengerDTO.getEmail());

        hcell = new PdfPCell(new Phrase("Phone", headFont));
        table.addCell(hcell);
        table.addCell(passengerDTO.getPhone());

        return table;
    }
}