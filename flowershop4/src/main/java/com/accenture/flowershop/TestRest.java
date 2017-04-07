package com.accenture.flowershop;

import java.net.URI;

public class TestRest {
  public static void main(String[] args) {


         /* ClientConfig config = new ClientConfig();
          Client client = ClientBuilder.newClient(config);
          WebTarget service = client.target(getBaseURI());

          Form form =new Form();
          form.param("user", "3");
          form.param("userShopCartFlowername", "orchid");
          form.param("userShopCartCount", "13");
          
          //Response response = service.path("rest").path("todos").path(todo.getId()).request(MediaType.APPLICATION_XML).put(Entity.entity(todo,MediaType.APPLICATION_XML),Response.class);         
          Response response = service.path("rest").path("hello").request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED),Response.class);
          System.out.println("Form response " + response.getStatus());*/
  }

  private static URI getBaseURI() {
    return null ;//UriBuilder.fromUri("http://localhost:8080/flowershop4").build();
  }
}