import type { Movie } from "../types/movie";

export function filterMovies(movies: Movie[], query: string): Movie[]{
    return movies.filter(movie => movie.title.toLowerCase().includes(query.toLowerCase()));
}