
def top_watched_movies():
    file_path = "Items.csv"
    movie_count = {}
  # NOtes: Add a csv validation (len constraint)  , instead of nested for loop, use method inside one loop to make it look cleaner
            # read about file reading in streams
    try:
        with open(file_path, 'r') as file:
            content = file.read()
            movies = content.strip().split(',')
            # print(movies)
           
            for movie in movies:
                if movie in movie_count:
                    movie_count[movie] += 1
                else:
                    movie_count[movie] = 1
        
        
        sorted_movies = sorted(movie_count.items(), key=lambda x: x[1], reverse=True)
        
        print("Top 3 Most Watched Movies:")
        for i, (movie, count) in enumerate(sorted_movies[:3]): 
            #enumerate takes iterable (list/string) and returns an iterator(tuple/pair)
            print(f"{i+1}.{movie}: {count} times")
            
    except FileNotFoundError:
        print('File not Found')

    #check for any other exception
    # avoid magic numbers, have global constants at the top
if __name__ == "__main__":
    # print("Entry point")
    top_watched_movies()