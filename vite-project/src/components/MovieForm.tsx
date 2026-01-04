import { useState } from "react";

interface Props{
    onAddMovie: (
        title: string,
        description?: string,
        rating?: number,
        theme?: string,
        releaseDate?: Date
    ) => void;
}
export function MovieForm({onAddMovie}: Props){
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [rating, setRating] = useState<number | undefined>();
    const [theme, setTheme] = useState("");
    const [releaseDate, setReleaseDate] = useState("");

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        
        if (!title.trim()) return;
        
        onAddMovie(
            title,
            description || undefined,
            rating,
            theme || undefined,
            releaseDate ? new Date(releaseDate) : undefined
        );

        setTitle("");
        setDescription("");
        setRating(undefined);
        setTheme("");
        setReleaseDate("");
    };

    return (
        <form className="movie-form" onSubmit={handleSubmit}>
            <div className="form-grid">
                <div className="form-field">
                    <label>Movie Title *</label>
                    <input
                        required
                        value={title}
                        onChange={e => setTitle(e.target.value)}
                        placeholder="Enter movie title"
                    />
                </div>
                
                <div className="form-field">
                    <label>Rating</label>
                    <select
                        value={rating ?? ""}
                        onChange={e => setRating(Number(e.target.value) || undefined)}
                    >
                        <option value="">Select Rating</option>
                        <option value="1">⭐ 1</option>
                        <option value="2">⭐ 2</option>
                        <option value="3">⭐ 3</option>
                        <option value="4">⭐ 4</option>
                        <option value="5">⭐ 5</option>
                    </select>
                </div>
                
                <div className="form-field">
                    <label>Theme</label>
                    <input
                        value={theme}
                        onChange={e => setTheme(e.target.value)}
                        placeholder="Action, Drama, Comedy..."
                    />
                </div>
                
                <div className="form-field">
                    <label>Release Date</label>
                    <input
                        type="date"
                        value={releaseDate}
                        onChange={e => setReleaseDate(e.target.value)}
                    />
                </div>
            </div>

            <div className="form-field">
                <label>Description</label>
                <textarea
                    value={description}
                    onChange={e => setDescription(e.target.value)}
                    placeholder="Add a brief description..."
                />
            </div>
            
            <button className="btn-primary" type="submit">
                ➕ Add to Watchlist
            </button>
        </form>
    )
}