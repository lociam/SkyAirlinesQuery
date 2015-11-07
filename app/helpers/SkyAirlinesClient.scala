package helpers

import scalaj.http._

class SkyAirlinesClient {

  //---------------------------------------------------------------------
  // Definitions
  //---------------------------------------------------------------------
  
  val REQUEST_URL = "http://www.skyairlines.cl"
  
  //---------------------------------------------------------------------
  // Functions
  //---------------------------------------------------------------------
   
  
  def request(origin: String){
    
    val response: HttpResponse[String] = 
      Http(REQUEST_URL).postForm(Seq("test" -> origin, "test2" -> "b")).asString
      
      println(response)
  }
  
  /**
   * This method request the corresponding page using the REQUEST_URL and returns
   * the list of flights obtained
   * @param ReqParam object with all the params given in the request
   * @return list of Flight objects with the information received from the page 
   */
  def getFlights(params: ReqParams): List[Flight] = {
    var list: List[Flight] = {
          List(
              new Flight("Bogota", "Medellin"),
              new Flight("Bogota","Cali"),
              new Flight(params.origin, params.destination)
          )
    }
    
    request(params.origin)
    
    list
  }
}