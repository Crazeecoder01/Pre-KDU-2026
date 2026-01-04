import { useState } from "react";
import type { Movie } from "../types/movie";

function useMovies(){
    const [movies, setMovies] = useState<Movie[]>([]);

    const addMovie = (
        title: string,
        description?:string,
        rating?: number,
        theme?: string,
        releaseDate?: Date
    )=>{
        setMovies(prev =>[
            ...prev,
            {
                id: crypto.randomUUID(),
                title,
                description,
                watched: false,
                rating,
                theme,
                releaseDate
            }
        ])
    }
    const removeMovie = (id: string)=>{
        setMovies(prev=> prev.filter(movie => movie.id != id));
    };
    const showAll = ()=>{
        return movies;
    }
    const clearAll = ()=>{
        setMovies([]);
    }
    const toggleWatched = (id: string)=>{
        setMovies(prev =>
        prev.map(movie =>
            movie.id === id
            ? { ...movie, watched: !movie.watched }
            : movie
        )
        );
    }
    return {
        movies,
        addMovie,
        removeMovie,
        showAll,
        clearAll,
        toggleWatched
    }
}

export default useMovies;