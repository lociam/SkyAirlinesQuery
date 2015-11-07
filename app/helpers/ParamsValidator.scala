package helpers

import java.util.Calendar
import java.util.Date
import java.text.SimpleDateFormat

object ParamsValidator {
  
  //---------------------------------------------------------------------
  // Definitions
  //---------------------------------------------------------------------
  
  /**
   * This function allows us to convert the usual city name to the standard name
   */
  def city_match(city_name: String): String = city_name match {
    case "Santiago de Chile" => "SCL"
    case "Antofagasta" => "ANF"
    case "Arica" => "ARI"
    case "Balmaceda" => "BBA"
    case "Barranquilla" => "BAQ"
    case "Bogota" => "BOG"
    case "Buenos Aires" => "EZE"
    case "Calama" => "CJC"
    case "Cali" => "CLO"
    case "Cartagena" => "CTG"
    case "Concepción" => "CCP"
    case "Copiapó" => "CPO"
    case "Cuzco" => "CUZ"
    case "Iquique" =>  "IQQ"
    case "Juliaca" => "JUL"
    case "La Paz " => "LPB"
    case "La Serena" =>  "LSC"
    case "Lima" => "LIM"
    case "Medellin" => "MDE"
    case "Osorno" => "ZOS"
    case "Puerto Montt" => "PMC"
    case "Punta Arenas" => "PUQ"
    case "San Andres" =>  "ADZ"
    case "Santa Marta" => "SMR"
    case "Sao Paulo" => "GRU"
    case "Tarapoto" => "TPP"
    case "Temuco" => "ZCO"
    case "Trujillo" => "TRU"
    case "Valdivia" => "ZAL"  
    case _ => ""
}
  
  /**
   * This list contains all the currently supported cities
   */
  var standard_cities: List[String] = { 
    List(
      "SCL","ANF", "ARI", "BBA", "BAQ", "BOG", "EZE","CJC","CLO","CTG","CCP",
      "CPO","CUZ","IQQ","JUL","LPB","LSC","LIM","MDE","ZOS","PMC","PUQ","ADZ",
      "SMR","SCL","GRU","TPP","ZCO","TRU","ZAL"
    )
  }

  //---------------------------------------------------------------------
  // Functions
  //---------------------------------------------------------------------
  
  
  /**
   * This method validates the date format (should be dd-mm-yyyy) and
   * that the date is not before today.
   * @param date, string with the date to be validated
   * @return true if the date format is valid, false otherwise
   */
  def validateDate(date: String): Boolean = {
    
    if (date.matches("^\\d{2}[-]\\d{2}[-]\\d{4}$")){
      var desired_date: Date = new SimpleDateFormat("dd-MM-yyyy").parse(date)
      !desired_date.before(Calendar.getInstance().getTime())
    }
    else
    {
      false
    }
  }
  
  /**
   * This method validates that the endDate is included and valid when is roundTrip.
   */
  def validateRoundTrip(roundTrip: Boolean, endDate: String): Boolean = {
    !roundTrip || (roundTrip && validateDate(endDate))
  }
  
  /**
   * This method validates that number of passengers have valid values
   */
  def validateNumberOfPassengers(adults: Integer, children: Integer, babys: Integer): Boolean = {
    return adults >= 1 && adults < 10 && children >= 0 && children < 10 && babys >= 0 && babys < 10 
  }
  
  /**
   * This method validates if the city is supported
   */
  def validateCity(city: String): Boolean = {
    return standard_cities.contains(city)
  }
 
}