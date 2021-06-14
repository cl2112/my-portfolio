package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the values entered in the form.
    String senderName = request.getParameter("sender-name");
    String senderOrganization = request.getParameter("sender-organization");
    String senderMessage = request.getParameter("sender-message");

    // Create a string that describes the values received.
    String dataString = "You submitted: \nName: " + senderName + ", \nOrganization: " + 
      senderOrganization + ", \nMessage: " + senderMessage;

    // Print the values so you can see it in the server logs.
    System.out.println(dataString);

    // Write the value to the response so the user can see it.
    response.getWriter().println(dataString);
  }
}
