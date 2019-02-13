# Movies App Android
This repository contains a detailed sample project that implements MVVM architecture using Jetpack's ViewModel and LiveData. 
It also uses Dagger2 and RxJava.

![MovieApp](name-of-giphy.gif)

# Package Structure
- **activity:** Contains all activities of the app.
- **data:** Contains all network and local data repository.
- **di:** Dependency providing classes using Dagger2.
- **viewmodel:** View-Model classes which extends Jetpack's ViewModel.
- **network:** Web service calling classes and interface.
- **model:** Contains POJO classes.
- **utils:** Utility classes.

# MVVM Structure
Below is the description of how movie list feature is built with MVVM architecture-
- **View-** `MovieListActivity` is the view for showing movie list. It contains recycler view. It observes movie data.
- **Model-** `MovieRepository` is the data model for movies.
- **View-Model-** `MovieViewModel` extends Jetpack's `ViewModel` which manipulates data, coming from movie repository
and post the value which view observes.

# Dependency Injection

### Component
MoviesApplication initializes `AppComponent` as follows:
```java
AppComponent appComponent = DaggerAppComponent.builder().
                appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .movieModule(new MovieModule()).build();
```

### Modules
`AppComponent` builds modules i.e. `NetworkModule` which provides web service objects, `MovieModule` provides movie repository and view-model factory, and AppModule provides app context.

### ViewModel Factory

By default, ViewModel requires zero argument constructor.
ViewModel factory is used to provide a correct parameterized constructor of ViewModel. It is used as follows:

```java
@Inject
ViewModelFactory viewModelFactory;
    
// some code

MoviesViewModel moviesViewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel.class);
moviesViewModel.observeMoviesResponse().observe(this, this::consumeResponse);
```

# Unit Test

In order to test **LiveData**, it’s values need to be directly updated on the calling thread.

```java
@Test
public void testFetchAllMovies_testError() {
     MutableLiveData<ApiResponse> responseMutableLiveData = new MutableLiveData<>();
     Exception exception = new Exception();
     ApiResponse apiResponse = ApiResponse.error(exception);
     responseMutableLiveData.postValue(apiResponse);
     Assert.assertEquals(Status.ERROR, responseMutableLiveData.getValue().status);
     Assert.assertNull(responseMutableLiveData.getValue().data);
     Assert.assertNotNull(responseMutableLiveData.getValue().error);
}
```
