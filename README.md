# Wolt coding assignment
  ## Part1
- For the api, I've built a library which contain a JsonDeserializer based on Gson library, which deserialize the response in to a more managable object with the schema as follow: `json
{
 "monday" : [
	 {"open" : "32400",
	 "close" : 72000
	},
 ],
 ….
}
`
- Also in the library, there's an api to convert the object received from the schema into a `kotlin
LinkedHashMap<String, String>` with the key is day of the week and the value is it's opening time in String format that can be easily consume by any client
- There's one function in the api which receive raw string response and convert to string format as expected in the assignment
- Library is tested with all the happy case, and there're some unhappy case that we can discuss and do the test together :)
- I also Implemented a simple android application using kotlin, dagger 2, retrofit and google databinding library which will display the result from a plain-text `json`, or fetched a json provided by a link, or from 3 example that I have hosted and ready to test.

  ##  Part 2
- About the data format, in my opinion, it's pretty difficult for client side to consume the data, so I thought of the schema above, which is much easier to consume, client side doesn't need to care about the logic of open and close overnight, just need to fetch the key which is day of the week and the value, which is an array of opening time (if that day has multiple) and then display it.
- Also, I think my schema can scale up easier, for example, if the day is special day (Easter, Vaapu ...) we can add another field called description and put the it there, client only consume it the time open is empty ...
