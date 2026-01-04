export interface Movie{
    id: string;
    title: string;
    description?:string;
    watched: boolean;
    rating?: number;
    theme?: string;
    releaseDate?: Date;
}