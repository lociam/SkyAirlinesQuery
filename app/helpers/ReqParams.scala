package helpers

class ReqParams(xstartDate: String, xendDate: String, xorigin: String,
      xdestination: String, xroundTrip: Boolean, xadults: Integer,
      xchildren: Integer, xbabys: Integer) {
  
  //---------------------------------------------------------------------
  // Definitions
  //---------------------------------------------------------------------

  
  var startDate: String = xstartDate
  var endDate: String = xendDate
  var origin: String = xorigin
  var destination: String = xdestination
  var roundTrip: Boolean = xroundTrip
  var adults: Integer = xadults
  var children: Integer = xchildren
  var babys: Integer = xbabys
}