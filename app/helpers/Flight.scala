package helpers

import play.api.libs.json._
import play.api.libs.functional.syntax._

class Flight(norigin: String, ndestination: String) {
  
  //---------------------------------------------------------------------
  // Definitions
  //---------------------------------------------------------------------
    
  var origin: String = norigin
  var destination: String = ndestination
  

}