import type { Movie } from "../types/movie";

interface Props {
  movie: Movie;
  onToggleWatched: (id: string) => void;
  onDelete: (id: string) => void;
}

export function MovieCard({movie, onToggleWatched, onDelete}: Props){
    return(
        <li className="movie-card">
            <div className="movie-info">
                <span className={`movie-title ${movie.watched ? "watched" : ""}`}>
                    {movie.title}
                </span>
                <div className="movie-meta">
                    {movie.rating && <span>⭐ {movie.rating}/5</span>}
                    {movie.theme && <span>🎭 {movie.theme}</span>}
                    {movie.releaseDate && (
                        <span>📅 {new Date(movie.releaseDate).toLocaleDateString()}</span>
                    )}
                </div>
                {movie.description && (
                    <p className="movie-description">
                        {movie.description}
                    </p>
                )}
            </div>

            <div className="movie-actions">
                <button className="btn-icon" onClick={() => onToggleWatched(movie.id)}>
                    {movie.watched ? "↩️ Unwatch" : "✅ Watched"}
                </button>

                <button className="btn-icon" aria-label="Delete movie" onClick={() => onDelete(movie.id)}>
                    🗑️ Delete
                </button>
            </div>
        </li>
    )
}