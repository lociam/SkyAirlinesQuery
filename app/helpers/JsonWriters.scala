package helpers

import play.api.libs.json._
import play.api.libs.functional.syntax._

object JsonWriters {
  
  //---------------------------------------------------------------------
  // Definitions
  //---------------------------------------------------------------------
    
  implicit val flightWrites = Writes[Flight] { f: Flight =>
     writeFlight(f);
  }

  //---------------------------------------------------------------------
  // Functions
  //---------------------------------------------------------------------
    
  /**
   * This permits to get the corresponding error message from one status code
   */
  def error_codes(code: String): String = code match {
    case "00" => "Successful"
    case "10" => "Invalid start date"
    case "11" => "Invalid end date"
    case "12" => "Invalid number of passengers"
    case "13" => "Invalid origin or destination city"
    case "98" => "Unknown error" 
    case "99" => "Timeout"
    case _ => "Uncaught error"  
  }
  
  /**
   * This function writes the flight part of the json. (It's called for every flight)
   */
  def writeFlight(f: Flight): JsValue = {
     Json.obj(
    	"origin" -> f.origin,
    	"destination" -> f.destination    	
     )
  }
  
  /**
   * This method creates the json object to be answered
   */
  def flightsResponse(resCode: String, list: List[Flight]): JsValue = {
     Json.obj(
         "status" -> resCode,
         "res_message" -> error_codes(resCode),
         "flights" -> Json.toJson(list)
     )
  }
}