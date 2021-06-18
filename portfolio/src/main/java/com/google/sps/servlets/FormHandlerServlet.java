package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@WebServlet("/posts")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the values entered in the form. Sanitize user input to remove HTML tags and JavaScript.
    String senderName = Jsoup.clean(
      request.getParameter("sender-name"), 
      Whitelist.none()
    );
    String senderOrganization = Jsoup.clean(
      request.getParameter("sender-organization"), 
      Whitelist.none()
    );
    String senderMessage = Jsoup.clean(
      request.getParameter("sender-message"), 
      Whitelist.none()
    );

    // Set up datastore object.
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Post");
    FullEntity postEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("sender-name", senderName)
            .set("sender-organization", senderOrganization)
            .set("sender-message", senderMessage)
            .build();
    datastore.put(postEntity);

    // Create a string that describes the values received.
    String dataString = "You submitted: \nName: " + senderName + ", \nOrganization: " + 
      senderOrganization + ", \nMessage: " + senderMessage;

    // Print the values so you can see it in the server logs.
    System.out.println(dataString);

    // Redirect to the homepage.
    response.sendRedirect("/index.html");
  }
}
