package controllers

import play.api._

import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import helpers._

class Application extends Controller {

  //---------------------------------------------------------------------
  // Definitions
  //---------------------------------------------------------------------
 
      
  //---------------------------------------------------------------------
  // Functions
  //---------------------------------------------------------------------
  
  /**
   * Default action for index.
   */
  def index = Action {
    Ok("Bienvenido al API para SkyAirlines CL.")
  }
  
  /**
   * Action which receives the flight request parameters and search for flights 
   * in the Skyairlines.cl page returning the results in a JSON response 
   */
  def getFlights(startDate: String, endDate: String, origin: String,
      destination: String, roundTrip: Boolean, adults: Integer,
      children: Integer, babys: Integer) = Action { implicit request =>
      
      var status = "00"  
        
      //We convert the origin and dest to the standard names
      var reqParams = new ReqParams(startDate, endDate, ParamsValidator.city_match(origin),
      ParamsValidator.city_match(destination), roundTrip, adults,
      children, babys)
      
      status = validateParams(reqParams)
      
      
    var list: List[Flight] = {List()}     
      
    var ins: SkyAirlinesClient = new SkyAirlinesClient()
    
    if(status == "00"){
      list = ins.getFlights(reqParams)
    }     
        
    val json = JsonWriters.flightsResponse(status, list)
    Ok(json)
  }
  
  
  /**
   * This method validates all the entry params and returns the corresponding status code
   * @params reqParams, all the params coming in the request
   * @return status code corresponding with the validation error, or 00 if no problem is found
   */
  def validateParams(params: ReqParams): String = {
    if(!ParamsValidator.validateDate(params.startDate)){
      "10"
    }
    else if(!ParamsValidator.validateRoundTrip(params.roundTrip, params.endDate)){
      "11"
    }
    else if(!ParamsValidator.validateNumberOfPassengers(params.adults, params.children, params.babys)){
      "12"
    }
    else if(!ParamsValidator.validateCity(params.origin) || !ParamsValidator.validateCity(params.destination)){
      "13"
    }
    else
    {
      "00"
    }
  }

}
