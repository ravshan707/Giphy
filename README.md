# Giphy

 Dependencies utilized:
 1. Retrofit, RxJava, Gson - for setting up connection to the API and reactively consuming the Json response returned and and updating the UI accordingly.
 2. Realm - the local db used for caching the results. Added a local field 'query' to the Gif model in order to relate the stored gifs to the search word. Test: 1) search for gif. If results returned, clear. 2) turn off internet connection. 3) search for the same gif. Same results should be loaded from Realm.
 3. RecyclerView, Glide - used to output gifs as a list.
 4. Butterknife - used for easy referencing and accessing layout views by code. 
