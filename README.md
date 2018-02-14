# cake-android-client-complete

Here is the second solution with complete revamp, I have used Android Architecture components, MVVM and Dagger 2 to make it clean and extendable.

<<<<<<< HEAD
**
NOTE: It contains all the fixes of solution # 1
**
=======
>>>>>>> 178f9a8e092453ee708732bff12c9e08ec70eaf5

# Libraries I might have used
As per the requirement Its a sensitive Project and i was instructed not to use third party libraries, But if i have got the chance to use 3rd party libraries. I might have used libraries mentioned below.

* Glide {for image loading and caching}
* GSON {For Json serlization/deserialization}
* Retrofit {for HTTP , client server communication}
* RxJava {for Observer/Subscription Pattern}



# Explanation
Its my second submission where i have revamped the architecture freely, Used Android Architecture Components like LiveData,ViewModel DataBinding etc, And I also used Dagger 2 for dependency injection.

I have created a network/NetworkManager class, Which use to enqueue HTTP Calls, and prevents to add already in progress Http Call and push call backs form where initiated.
I have Modified  network/ImageLoader class, Which is using LRU cache against URLs. So before loading any image it checks if it can be served from cache. Other wise invoke Network Manager to get the bytes against any Image.

Network Manager and Image loader are Singlton and injectable in any View through Dependency injection.

Below you can see project hierarchy.

* adapters
* base
* di
* enums
* interfaces
* models
* network
* repositories
* utils
* viewmodels
* views


