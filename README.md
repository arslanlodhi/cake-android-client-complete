# cake-android-client-complete

Here is the second solution with complete revamp, I have used Android Architecture components, MVVM and Dagger 2 to make it clean and extendable.


# Libraries I might have used
As per the requirement Its a sensitive Project and i was instructed not to use third party libraries, But if i have got the chance to use 3rd party libraries. I might have used libraries mentioned below.

1. Glide {for image loading and caching}
2. GSON {For Json serlization/deserialization}
3. Retrofit {for HTTP , client server communication}
4. RxJava {for Observer/Subscription Pattern}


# Explaination
Its my second submission where i have revamed the architecture freely, Used Android Architecture Components like LiveData,ViewModel DataBinding etc, And I also used Dagger 2 for dependency injection. 

I have created a network/NetworkManager class, Which use to enqeue any HTTP Call, Prevents to add already inProgress Http Calls and push call backs form where intiated.

I have Modified a network/ImageLoaded class, Which is using LRU cache against URLs. So before loading any image it checks if it can be served from cache. Other wise invoke Network Manager to get the bytes against any Image.

my Network Manager and Image loader are singlton and injecable in any View though Dependency injection.

Below you can see project hierarchy.

adapters
base
di
enums
interfaces
models
network
repositories
utils
viewmodels
views
