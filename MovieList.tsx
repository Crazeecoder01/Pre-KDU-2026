import type { Movie } from "../types/movie";
import { MovieCard } from "./MovieCard";

interface Props{
    movies: Movie[];
    onToggleWatched: (id: string) => void;
    onDelete: (id: string) => void;
}
export function MovieList({movies, onToggleWatched, onDelete}: Props){
    if (movies.length === 0) {
        return (
            <div className="empty-state">
                📽️ No movies in your watchlist yet. Add one above!
            </div>
        );
    }

    return(
        <ul className="movie-list">
            {
                movies.map(movie => (
                    <MovieCard
                    key={movie.id}
                    movie={movie}
                    onToggleWatched={onToggleWatched}
                    onDelete={onDelete}/>
                ))
            }
        </ul>
    )
}