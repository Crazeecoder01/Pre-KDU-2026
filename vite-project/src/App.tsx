import { useState } from "react"
import useMovies from "./hooks/useMovies";
import { MovieForm } from "./components/MovieForm";
import { MovieList } from "./components/MovieList";
import './App.css';

function App(){
  const [search, setSearch] = useState("");
  const { movies, addMovie, removeMovie, toggleWatched, clearAll, showAll } = useMovies();

  const filteredMovies = movies.filter(movie =>
    movie.title.toLowerCase().includes(search.toLowerCase())
  );

    return(
      <main>
        <h1>
          🎬 Movie Watchlist
        </h1>
        
        <div className="app-layout">
          <div className="form-section">
            <MovieForm onAddMovie={addMovie}/>
          </div>

          <div className="list-section">
            <input
              className="search-bar"
              placeholder="🔍 Search movies..."
              value={search}
              onChange={e => setSearch(e.target.value)}
            />

            <MovieList
              movies={filteredMovies}
              onToggleWatched={toggleWatched}
              onDelete={removeMovie}
            />

            {movies.length > 0 && (
              <div className="clear-all-container">
                <button className="btn-danger" onClick={clearAll}>
                  🗑️ Clear All Movies
                </button>
              </div>
            )}
          </div>
        </div>
      </main>
    )
}

export default App;