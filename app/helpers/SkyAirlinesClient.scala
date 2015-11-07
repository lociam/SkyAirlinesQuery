package helpers

import scalaj.http._

class SkyAirlinesClient {

  //---------------------------------------------------------------------
  // Definitions
  //---------------------------------------------------------------------
  
  val REQUEST_URL = "http://www.skyairline.cl/es/paso2.aspx"
  //val REQUEST_URL = "http://www.skyairline.cl/es/index.aspx"
  
  //---------------------------------------------------------------------
  // Functions
  //---------------------------------------------------------------------
   
  
  def request(params: ReqParams){
      
    var httpParams = Seq(
          "origen" -> params.origin,
          "idaOculto" -> params.origin,
          "destino" -> params.destination,
          "destinoOculto" -> params.destination,
          "fecha_ida" -> params.startDate.replace("-","/"),
          "fecha_vuel" -> params.endDate.replace("-","/"),
          "n_adulto" -> params.adults.toString(),
          "n_ninos" -> params.children.toString(),
          "n_infantes" -> params.babys.toString(),
          "opcion1" -> "2",
          "btn_buscar" -> "Buscar vuelos"
        )
        
    var httpHeaders = Seq(
          "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
          "Origin" -> "http://www.skyairline.cl",
          "User-Agent" -> "Scala",
          "Content-Type" -> "application/x-www-form-urlencoded",
          "Referer" -> "http://www.skyairline.cl/es/paso2.aspx",
          "Accept-Encoding" -> "gzip, deflate",
          "Accept-Language" -> "es-419,es;q=0.8",
          "Cookie" -> "TieneIdioma=paisRoot=CL&paisRootNombre=Chile&Idioma=ES"
          
          
        )
        
    var request: HttpRequest = Http(REQUEST_URL).headers(httpHeaders).postForm(httpParams)
    println(request)
    
    var response: HttpResponse[String] = 
      request.asString      
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
    
    request(params)
    
    list
  }
}