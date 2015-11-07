# SkyAirlinesQuery

This project exposes a simple GET API to search flights in the SkyAirlines.cl page.

###URL: https://skyairlinesquery.herokuapp.com/getflights

###Entry Params (GET):
- startDate: Date of the first Flight with the format dd-mm-yyyy
- endDate: Date of the return flight with the format dd-mm-yyyy
- roundTrip: Boolean indicating if the trip is one way or round
- adults: How many adults will travel
- children: How many childs will travel
- babys: How many babys will travel
- origin: Start city name
- destination: Destination city name


###Response (JSON):

```json

{
  "status": "00",  //Response status
  "res_message": "Successful",   //Response message
  "flights": [                  //Array with all the encountered flights
    {
      "origin": "Bogota",            
      "destination": "Medellin"
    },
    {
      "origin": "Bogota",
      "destination": "Cali"
    },
    {
      "origin": "BOG",
      "destination": "SCL"
    }
  ]
}

```
