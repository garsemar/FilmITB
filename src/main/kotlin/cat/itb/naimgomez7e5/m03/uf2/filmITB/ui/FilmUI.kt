package cat.itb.naimgomez7e5.m03.uf2.filmITB.ui

import cat.itb.naimgomez7e5.m03.uf2.filmITB.model.Film
import cat.itb.naimgomez7e5.m03.uf2.filmITB.utils.isInValidRange
import java.util.*
import cat.itb.naimgomez7e5.m03.uf2.filmITB.utils.*

class FilmUI(val scan: Scanner, val appState : AppState) {
    /**
     * Print the films menu
     */
    fun showFilmMenu() : AppState
    {

        while (true) {

            println("Films:")
            println("1: Add film")
            println("2: Show films")
            println("3: Delete films")
            println("4: Watch films")
            println("5: View watched films")
            println("6: Add film to favorites")
            println("7: Show favorites")
            println("8: Show likes per film")
            println("0: Return to main menu")

            when (inputInt(scan)) {
                1 -> addFilm()
                2 -> showFilms()
                3 -> deleteFilms()
                4 -> watchFilms()
                5 -> viewWatchedFilms()
                6 -> addFavorites()
                7 -> showFavorites()
                8 -> showLikesFilm()
                0 -> return appState
                else -> {
                    return appState
                }
            }
        }
    }

    /**
     * Add a new film by picking attributes by terminal
     */
    private fun addFilm() {
        println("[Add film]")

        val title = inputString(scan, "Titel: ")
        val director = inputString(scan, "Nombre y apellido del director:")
        val genere = inputString(scan, "Genero de la pelicula:")
        val mainActor = inputString(scan, "Actor principal de la pelicula:")
        val resume = inputString(scan, "Resumen de la pelicula:")
        val ageRating = inputInt(scan, "Edad recomendada:")
        val duration = inputInt(scan, "Duracion de la pelicula:")

        appState.filmItb.addFilm(title, director,genere,mainActor,resume, ageRating, duration)
    }

    /**
     * Show all films by terminal
     */
    private fun displayFilms()
    {
        for(i in appState.filmItb.films.indices){
            val film = appState.filmItb.films[i]
            println("$i: ${film.title}")
        }
    }

    /**
     * Call the function that shows all the films
     */
    private fun showFilms() {
        displayFilms()
    }

    /**
     * Returns a Film with the selected film
     */
    private fun selectFilmFromMenu(msg : String) : Film
    {
        displayFilms()

        val maxRange = appState.filmItb.films.size-1
        while(true) {
            var selectedFilm = inputInt(scan, "$msg (Number from 0 to $maxRange):")
            while (!isInValidRange(0, maxRange, selectedFilm)) {
                selectedFilm = inputInt(scan, "$msg (Number from 0 to $maxRange):")
            }
            return appState.filmItb.films[selectedFilm]
        }
    }

    /**
     * Delete the selected film with the selectFilmFromMenu() function
     */
    private fun deleteFilms() {
        println("[Delete User]")

        val film = selectFilmFromMenu("Which film do you want to delete?", )
        appState.filmItb.deleteFilm(film)
        println("Successfully removed!")
    }

    /**
     * Adds the film selected with the function selectFilmFromMenu() to the watchedFilms list
     */
    private fun watchFilms() {
        println("[Watch films]")
        val film = appState.filmItb.addFilmToWatchedFilms(selectFilmFromMenu("Which movie do you want to watch?"))
        println("The movie has been viewed '${film.title}'")
    }

    /**
     * Displays all films in the watchedFilms list via the terminal
     */
    private fun viewWatchedFilms() {
        println("[Watched films]")
        val list = appState.filmItb.displayWatchedFilms()
        for(i in list.indices) {
            println("$i: ${list[i].title}")
        }
    }

    /**
     * Adds the selected film to the likedFilms list with the function selectFilmFromMenu()
     */
    private fun addFavorites() {
        println("[Add favorites]")
        val film = appState.filmItb.addFavorites(selectFilmFromMenu("Which movie do you want to watch?"))
        println("The movie interstellar ${film.title} has been added to favorites")
    }

    /**
     * Displays by terminal all films in the likedFilms list
     */
    private fun showFavorites() {
        println("[Show favorites]")
        val favorites = appState.filmItb.showFavorites()
        for(favorite in favorites)
        {
            println(favorite.title)
        }
    }

    /**
     * Displays the number of likes for each film by terminal
     */
    private fun showLikesFilm() {

        val likedFilms = appState.filmItb.likesPerFilm()
        for(liked in likedFilms)
        {
            println("${liked.title} \nLikes:${liked.likes}")
        }
    }
}