# Giphy

 Dependencies utilized:
 1. Retrofit, RxJava, Gson - used to set up an asynchronous connection to the API and to reactively consume  and process the Json response.
 2. Realm - used as the local db for caching the previous results from calls. Added a local field 'query' to the Gif model in order to relate the stored gifs to the search word. Test: 1) search for gif. If results returned, clear. 2) turn off internet connection. 3) search for the same gif. Same results should be loaded from Realm.
 3. RecyclerView, Glide - used to output gifs as a scrollable listview.
 4. Butterknife - used for easy referencing and accessing layout views by code. 
