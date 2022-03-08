# GDSC(GOOGLE DEVELOPERS STUDENT CLUB)

The following code is part of the application: https://github.com/HeetGutka/Android-Study-Jams 

The application is a scheduling application which helps to book appointments at places of our daily commute.This project is a part of GDSC Organised Event.My task of the project was to construct the above code and patch rest of the modules into one complete application.


About the following Code :
It consist of only three Fragments each depicting different tasks. Two of those are used for fetching data from the backend being Firebase and the other fragment is used for dividing selected time period in slots of the provided tenure. The application is built using MVVM architecutre and Hilt has been used for dependency injection purpose. It consist of five packages named adapter, business, di, fragment, model.Each can be elaborated as follows :

Adapter : The adapter package consist of RecyclerViewAdapter along with its needed class <br />
Business : Consist of the business logic : Repository and ViewModel <br />
Di : Consist of dependency injection code <br />
Fragment : Consist of all the fragments visible to the User <br />
Model : Consist of the data model class <br />
